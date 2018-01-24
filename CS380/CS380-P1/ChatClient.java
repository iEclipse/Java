
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public final class ChatClient {
	private static Socket socket;

	public static void main(String args[]) {

		class Output implements Runnable {
			@Override
			public void run() {
				while (true) {
					try {
						BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

						String sMsg = null;
						if ((sMsg = in.readLine()) != null) { // From Server
							System.out.println(sMsg);
							if (sMsg.equals("Server> Name in use."))
								System.exit(0);
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			}
		}

		try {
			socket = new Socket("18.221.102.182", 38001);
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

			Thread t = new Thread(new Output());
			t.start();

			while (true) {
				String msg = read.readLine();
				if (msg.equals("exit")) // Exit Condition
					System.exit(0);
				out.print(msg + "\r\n"); // To Server
				out.flush();
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
