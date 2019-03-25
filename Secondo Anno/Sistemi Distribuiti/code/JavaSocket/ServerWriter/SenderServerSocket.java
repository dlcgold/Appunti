package serverWriter;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SenderServerSocket {

	final static String message = "This is a not so short text to test the reading capabilities of clients."
	 + " If they are not so smart, they will catch only part of it.";

	public static void main(String[] args) {
		try {
			Socket clientSocket;
			ServerSocket listenSocket;

			listenSocket = new ServerSocket(53535);
			System.out.println("Running Server: " + "Host=" + listenSocket.getInetAddress() + " Port="
					+ listenSocket.getLocalPort());

			while (true) {
				clientSocket = listenSocket.accept();
				System.out.println("Connected to client at port: " + clientSocket.getPort());
				
				PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
				System.out.println("Writing message: ");
				System.out.println(message);
				System.out.println("Message length: " + message.length());
				out.write(message);
				out.flush();
				clientSocket.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
