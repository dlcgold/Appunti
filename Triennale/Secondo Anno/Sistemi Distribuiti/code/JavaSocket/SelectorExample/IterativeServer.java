package SelectorExample;

import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class IterativeServer {

	public static void main(String[] args) {
		ServerSocket listenSocket;
		Socket clientSocket;
		byte[] byteReceived = new byte[1000];
		String messageString = "";

		try {
			listenSocket = new ServerSocket(53535);
			System.out.println("*** Running Server: " + "Host=" + listenSocket.getInetAddress() + " Port="
					+ listenSocket.getLocalPort());

			while (true) {
				clientSocket = listenSocket.accept();

				DataInputStream in = new DataInputStream(clientSocket.getInputStream());
				int bytesRead = 0;

				while (true) {
					bytesRead = in.read(byteReceived);
					if (bytesRead == -1)
						break; // no more bytes
					messageString += new String(byteReceived, 0, bytesRead);
					System.out.println("Received: " + messageString);
				}

				clientSocket.close();
				messageString = ""; // clear the string for the next client
				System.out.println("*** Avaialble to the next client.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
