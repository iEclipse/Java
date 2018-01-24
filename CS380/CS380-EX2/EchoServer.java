import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Random;
import java.util.zip.CRC32;

import javax.xml.bind.DatatypeConverter;

public final class EchoServer {

	static Socket socket;

	public static void main(String[] args) throws Exception {

		class SocketThreads implements Runnable {
			Random rn = new Random();
			Socket s;
			int cur;

			public SocketThreads(Socket s) {
				this.s = s;
			}

			public void run() {
				try {
					DataOutputStream out = new DataOutputStream(s.getOutputStream());
					DataInputStream in = new DataInputStream(s.getInputStream());
					System.out.printf("Client connected: %s%n", s.getInetAddress().getHostAddress());
					byte[] crc32 = new byte[4];
					int[] sentData = new int[200];
					byte[] crcNew = new byte[100];
					CRC32 c = new CRC32();

					System.out.println("\nSending Bytes:");
					for (int i = 1; i <= 200; i++) { // Sends 200 bytes
						cur = rn.nextInt(0x10); // Generates random bytes to send
						sentData[i - 1] = cur;
						System.out.printf("%X", cur); // Prints sent bytes
						out.write(cur); // Sends a byte
						out.flush();
						if (i % 20 == 0) // Prints new line every 20 values
							System.out.println();
					}
					System.out.println();

					for (int i = 0; i < 4; i++) {
						crc32[i] = in.readByte(); // Reads CRC32 byte by byte
					}
					System.out.println("Received CRC32: " + DatatypeConverter.printHexBinary(crc32)); // Displays
																										// Received
																										// CRC32

					for (int i = 0; i < 100; i++) {
						crcNew[i] = (byte) (sentData[2 * i] * 16 + sentData[2 * i + 1]); // Tests for matching CRC32
					}
					c.update(crcNew);
					System.out.printf("Server Generated CRC32: %08X\n", c.getValue()); // Prints server generated CRC32
																						// in hex

					if (Long.toHexString(c.getValue()).toUpperCase().equals(DatatypeConverter.printHexBinary(crc32)))
						out.writeByte(1); // Sends response to client if matching
					else {
						out.writeByte(0); // Sends response to client if not matching
					}
					out.flush();
					System.out.printf("Client disconnected: %s%n", socket.getInetAddress().getHostAddress());
				} catch (SocketException e) {
					System.out.printf("Client disconnected: %s%n", socket.getInetAddress().getHostAddress());
				} catch (IOException e2) {
					e2.printStackTrace();
				}
			}
		}

		try (ServerSocket serverSocket = new ServerSocket(38102)) {
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
