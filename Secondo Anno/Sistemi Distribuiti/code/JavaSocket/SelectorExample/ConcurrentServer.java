package SelectorExample;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.Iterator;
import java.util.Set;

public class ConcurrentServer {

	public static void main(String[] args) throws IOException {
		Selector selector = Selector.open();
		ServerSocketChannel server = ServerSocketChannel.open();
		server.bind(new InetSocketAddress("localhost", 53535));
		// set the channel in non blocking mode
		server.configureBlocking(false);
		// register the channel with the selector or the accept operation
		server.register(selector, SelectionKey.OP_ACCEPT);

		// Infinite server loop
		while (true) {
			// Waiting for events
			selector.select();
			// Get keys
			Set<SelectionKey> keys = selector.selectedKeys();
			Iterator<SelectionKey> i = keys.iterator();

			// For each keys...
			while (i.hasNext()) {
				SelectionKey key = (SelectionKey) i.next();

				// Remove the current key
				i.remove();

				if (key.isAcceptable()) // a client required a connection
					acceptClientRequest(selector, server);

				if (key.isReadable()) // ready to read
					readClientBytes(key);
			}
		}
	}

	private static void acceptClientRequest(Selector selector, ServerSocketChannel server) throws IOException {
		// get client socket channel
		SocketChannel client = server.accept();
		// Non Blocking I/O
		client.configureBlocking(false);
		// recording to the selector (reading)
		client.register(selector, SelectionKey.OP_READ);
		return;
	}

	private static void readClientBytes(SelectionKey key) throws IOException {
		SocketChannel client = (SocketChannel) key.channel();

		// Read byte coming from the client
		int BUFFER_SIZE = 256;
		ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
		try {
			if (client.read(buffer) == -1) {
				client.close();
				return;
			}
		} catch (Exception e) {
			// client is no longer active
			e.printStackTrace();
			client.close();
			return;
		}

		// Show bytes on the console
		buffer.flip(); // set the limit to the current position and then set the position to zero 
		Charset charset = Charset.forName("UTF-8");
		CharsetDecoder decoder = charset.newDecoder();
		CharBuffer charBuffer = decoder.decode(buffer);
		int port = client.socket().getPort();
		System.out.println(port + ": " + charBuffer.toString());
		return;

	}
}
