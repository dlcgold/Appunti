package serverWriter;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class ReceiverClientSocket {

	public static void main(String[] args) {
		Socket socket; // my socket
		InetAddress serverAddress; // the server address
		int serverPort; // the server port
		DataInputStream in; // the source of stream of bytes
		byte[] byteReceived = new byte[1000]; // the temporary buffer
		String messageString = ""; // the text to be displayed

		try { // connect to the server
			serverAddress = InetAddress.getByName(args[0]);
			serverPort = Integer.parseInt(args[1]);
			socket = new Socket(serverAddress, serverPort);
			System.out.println("Connected to server: " + socket.getPort()); // the server port

			in = new DataInputStream(socket.getInputStream()); // the stream to
																// read from

			// The following code shows in detail how to read from a TCP socket
			System.out.println("Ready to read from the socket");
			int bytesRead = 0; // the number of bytes read while (true) {
			bytesRead = in.read(byteReceived);
			messageString += new String(byteReceived, 0, bytesRead);
			System.out.println("Received: " + messageString);
			System.out.println("I am done!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
