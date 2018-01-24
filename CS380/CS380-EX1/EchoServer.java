
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public final class EchoServer {

	static Socket socket;

	public static void main(String[] args) throws Exception {

		class SocketThreads implements Runnable {
			Socket s;

			public SocketThreads(Socket s) {
				this.s = s;
			}

			public void run() {
				try {
					System.out.printf("Client connected: %s%n", s.getInetAddress().getHostAddress());

					PrintWriter out = new PrintWriter(s.getOutputStream(), true);
					BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
					while (true) {
						String msg = in.readLine(); // From Client
						out.print("Server> " + msg + "\r\n"); // To Client
						out.flush();
					}
				} catch (SocketException e) {
					System.out.printf("Client disconnected: %s%n", socket.getInetAddress().getHostAddress());
				} catch (IOException e2) {
					e2.printStackTrace();
				}
			}
		}

		try (ServerSocket serverSocket = new ServerSocket(22222)) {
			while (true) {
				socket = serverSocket.accept();
				Thread t = new Thread(new SocketThreads(socket));
				t.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				socket.close();
			} catch (Exception e) {
			}
		}
	}
}
