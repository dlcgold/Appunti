package SelectorExample;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class SenderClient {
	private Socket socket;
	private Scanner scanner;

	private SenderClient(InetAddress serverAddress, int serverPort) throws Exception {
		this.socket = new Socket(serverAddress, serverPort);
		this.scanner = new Scanner(System.in);
	}

	private void start() throws IOException, InterruptedException {
		String input;
		PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
		while (true) {
			input = scanner.nextLine();
			if (input.contentEquals("exit"))
					break;
			out.print(input);
			out.flush();
		}
		System.out.println("Client terminate.");
		socket.close();
	}

	public static void main(String[] args) throws Exception {
		SenderClient client = new SenderClient(InetAddress.getByName(args[0]),
				Integer.parseInt(args[1]));

		System.out.println("Connected to: " + client.socket.getInetAddress());
		client.start();

	}

}
