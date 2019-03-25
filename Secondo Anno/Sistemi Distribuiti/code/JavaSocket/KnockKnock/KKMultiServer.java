package KnockKnock;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class KKMultiServer {

	public static class KKMultiServerThread extends Thread {
		private Socket socket = null;

		public KKMultiServerThread(Socket socket) {
			super("KKMultiServerThread");
			this.socket = socket;
		}

		public void run() {
			try {
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String inputLine, outputLine;
				KnockKnockProtocol kkp = new KnockKnockProtocol();
				outputLine = kkp.processInput(null);
				out.println(outputLine);
				while ((inputLine = in.readLine()) != null) {
					outputLine = kkp.processInput(inputLine);
					out.println(outputLine);
					if (outputLine.equals("Bye"))
						break;
				}
				out.close();
				in.close();
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		boolean listening = true;

		try {
			serverSocket = new ServerSocket(4444);

			while (listening)
				new KKMultiServerThread(serverSocket.accept()).start();
			serverSocket.close();
		} catch (IOException e) {
			System.err.println("Could not listen on port: 4444.");
			System.exit(-1);
		}

	}

}
