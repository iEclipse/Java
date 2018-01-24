import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class WebServer {

	static Socket socket;

	public static void main(String[] args) throws Exception {

		class SocketThreads implements Runnable {

			// IPV4 Packet
			byte[] ipv4;

			// Socket
			Socket s;

			// Assigns Socket
			public SocketThreads(Socket s) {
				this.s = s;
			}

			// Runs SocketThreads
			public void run() {
				try {
					// Ouput Data Object
					DataOutputStream pw = new DataOutputStream(s.getOutputStream());
					PrintWriter out = new PrintWriter(s.getOutputStream());

					// Input Data Object
					DataInputStream in = new DataInputStream(s.getInputStream());

					// Initializes Array
					ipv4 = new byte[20];

					// Initializes Text
					String text;

					// Arguments for HTTP Request
					String[] textArgs;

					// Reads TCP IPV4 Header
					for (int i = 0; i < 20; i++) {
						ipv4[i] = in.readByte();
					}

					// Converts Data into String
					text = new String(ipv4);

					// Gets HTTP Form Request
					textArgs = text.split(" ");

					// If Proper Request
					if (textArgs[0].equals("GET") && textArgs[2].contains("HTTP")) {

						// Attempts to Grab File
						File f = new File("www" + textArgs[1]);

						// If File Path Exists
						if (f.exists()) {

							// File Reader
							Scanner sc = new Scanner(f);

							// Send OK Message
							out.print("HTTP/1.1 200 OK\r\nContent-type: text/html\r\nContent-length" + f.length()
									+ "\r\n\r\n");

							// Get File Data and Sends It
							while (sc.hasNext()) {
								out.print(sc.next());
								out.flush();
							}

							// Closes Scanner Object
							sc.close();

							// Otherwise Send This
						} else {

							// Send Error404 Message
							out.print(
									"HTTP/1.1 404 Not Found\r\nContent-type: text/html\r\nContent-length 126\r\n\r\n");

							// Error HTML
							String errorHTML = "<html>\r\n" + "<head>\r\n" + "<title>Not Found</title>\r\n"
									+ "</head>\r\n" + "<body>\r\n"
									+ "Sorry, the object you requested was not found.\r\n" + "</body>\r\n" + "<html>";

							// Sends Error HTML
							out.print(errorHTML);
						}

						// Closes Writing Object
						out.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		// Starts ServerSocket in a new Thread
		try (ServerSocket serverSocket = new ServerSocket(8080)) {
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
