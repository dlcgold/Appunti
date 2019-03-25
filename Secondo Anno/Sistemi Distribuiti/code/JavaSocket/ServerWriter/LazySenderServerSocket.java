package serverWriter;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class LazySenderServerSocket {

	final static String message = "This is a not so short text to test the reading capabilities of clients."
			+ " If they are not so smart, they will catch only part of it.";
	final static int chunck = 9; // number of bytes sent every time

	public static void main(String[] args) {
		try {
			Socket clientSocket;
			ServerSocket listenSocket;

			listenSocket = new ServerSocket(53535);
			System.out.println("Running Server: " + "Host=" + listenSocket.getInetAddress() + " Port="
					+ listenSocket.getLocalPort());

			while (true) {
				clientSocket = listenSocket.accept();

				PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
				System.out.println("message length: " + message.length());

				int i;
				for (i = 0; i < message.length() - chunck; i += chunck) {
					System.out.println(message.substring(i, i + chunck));
					Thread.sleep(1000);
					out.write(message.substring(i, i + chunck));
					out.flush();
				}
				System.out.println(message.substring(i, message.length()));
				out.write(message.substring(i, message.length()));
				out.flush();
				clientSocket.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
