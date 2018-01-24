
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public final class EchoClient {
	private static Socket socket;

	public static void main(String args[]) {
		try {
			socket = new Socket("localhost", 22222);
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

			while (true) {
				System.out.print("Client> ");
				String msg = read.readLine();
				if (msg.equals("exit")) // Exit Condition
					System.exit(0);
				out.print(msg + "\r\n"); // To Server
				out.flush();
				String sMsg = null;
				if ((sMsg = in.readLine()) != null) // From Server
					System.out.println(sMsg);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			try {
				socket.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}
