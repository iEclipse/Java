import java.io.OutputStream;
import java.net.Socket;
import java.util.Random;
import java.io.DataInputStream;

public class UdpClient {
	public static void main(String[] args) {
		try (Socket socket = new Socket("18.221.102.182", 38005)) {
			System.out.println("Connected to Server.");

			DataInputStream is = new DataInputStream(socket.getInputStream());
			OutputStream os = socket.getOutputStream();
			long average = 0;

			// get the dest address of server
			byte[] destAddress = socket.getInetAddress().getAddress();
			// Personal IP address (can use local host)
			byte[] srcAddress = { 127, 0, 0, 1 };

			// IPv4 always uses version 4
			int version = 4;
			// Minimum Value for this field is 5( can be variable in size but 5lines * 32
			// bits = 160 bits or 20 bytes)
			byte hlen = 5;
			// Start at 2 increment by x2 each time
			int dataLength = 4;
			// length of header (20 bytes)
			int length = 20;
			// Array for each field of IPv4
			byte[] packet;

			int port = 0;

			for (int i = 0; i < 13; i++) {
				// Total length without data is 20 bytes, but we add data length of 2 bytes so
				// total for i = 0 is 22 bytes
				int udpLength = 8;
				int totalLength = length + dataLength;
				if (i > 0)
					totalLength += udpLength;
				packet = new byte[totalLength];

				// version = 4 + hlen = 5 minimum
				// Here we are Orig two 4 bits together to make 1 byte
				packet[0] = (byte) ((version << 4 & 0xFF) + (hlen & 0xFF));

				// TOS(Do not Implement)
				packet[1] = 0;

				// Length split total length into two bytes to send
				byte lUpper = (byte) (totalLength >>> 8);
				byte lLower = (byte) (totalLength);
				packet[2] = lUpper;
				packet[3] = lLower;

				// Ident(Do not Implement)
				packet[4] = 0;
				packet[5] = 0;

				// Flags(Implement assuming no fragmentation) - 010 = 0100 0000 = 0x40
				packet[6] = 0x40;

				// Offset(Do not Implement)
				packet[7] = 0;

				// TTL(Implement assuming every packet has a TTL of 50)
				packet[8] = 50;

				// Protocol UDP = 17
				packet[9] = 17;

				// Source Address(Implement with an IP address of your choice)
				for (int j = 0; j < srcAddress.length; j++) {
					// 12 to 15
					packet[12 + j] = srcAddress[j];
				}

				// Destination address
				for (int k = 0; k < destAddress.length; k++) {
					// 16 to 19
					packet[16 + k] = destAddress[k];
				}

				// CheckSum
				short checkSum = checksum(packet);
				int checkSumUpper = checkSum >> 8 & 0xFF;
				int checkSumLower = checkSum & 0xFF;
				packet[10] = (byte) checkSumUpper;
				packet[11] = (byte) checkSumLower;

				if (i == 0) {
					long n = Long.decode("0xDEADBEEF");
					packet[20] = (byte) (n >> 24);
					packet[21] = (byte) (n >> 16);
					packet[22] = (byte) (n >> 8);
					packet[23] = (byte) n;
				} else {
					// Source Port
					packet[20] = 0;
					packet[21] = 0;

					// Destination Port
					packet[22] = (byte) (port >> 8 & 0xFFFF);
					packet[23] = (byte) (port & 0xFF);

					// Length
					packet[24] = (byte) (dataLength >> 8 & 0xFF);
					packet[25] = (byte) (dataLength & 0xFF);

					// Random Data
					Random rn = new Random();

					byte[] random = new byte[dataLength];
					rn.nextBytes(random);

					for (int j = 28; j < packet.length; j++)
						packet[j] = random[j - 28];

					// Pseudo Header
					byte[] pseudo = new byte[20 + dataLength];

					// Src Address 0-3
					for (int j = 0; j < srcAddress.length; j++) {
						pseudo[j] = srcAddress[j];
					}

					// Destination address 4-7
					for (int k = 0; k < destAddress.length; k++) {
						pseudo[4 + k] = destAddress[k];
					}

					// Zeroes 8

					// Protocol 9
					pseudo[9] = 17;

					// UDP Length 10
					pseudo[10] = packet[24];
					pseudo[11] = packet[25];

					// UDP Header 11-End
					for (int j = 12; j < pseudo.length; j++) {
						pseudo[j] = packet[j + 8];
					}

					short UDPCheckSum = checksum(pseudo);
					int UDPCheckSumUpper = UDPCheckSum >> 8 & 0xFF;
					int UDPCheckSumLower = UDPCheckSum & 0xFF;

					packet[26] = (byte) UDPCheckSumUpper;
					packet[27] = (byte) UDPCheckSumLower;

				}

				// Send to packet server

				if (i > 0)
					System.out.println("Sending packet with " + dataLength + " bytes of data");
				os.write(packet);

				if (i == 0) {
					// Server response

					System.out.print("Handshake Response: ");
					for (int j = 0; j < 4; j++)
						System.out.print(Integer.toHexString(is.readByte()).replaceAll("ff", "").toUpperCase());
					System.out.println();

					port = ((is.read() << 8 & 0xFFFF) + (is.read() & 0xFF));
					System.out.println("Port number received: " + port + "\n");

					dataLength = 2;
				}

				else {
					System.out.print("Response: ");
					long start = System.currentTimeMillis();
					for (int j = 0; j < 4; j++)
						System.out.print(Integer.toHexString(is.readByte()).replaceAll("ff", "").toUpperCase());
					long end = System.currentTimeMillis();
					System.out.println("\nRTT: " + (end - start) + "ms\n");
					average += (end - start);
					dataLength *= 2;
				}
			}

			System.out.printf("Average RTT: %.2f\n", average / 12.0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Disconnected from Server.");
	}

	private static short checksum(byte[] b) {
		// Initialize Sum
		int sum = 0;
		// int i = 0;
		// Loop through bytes
		for (int i = 0; i < b.length - 1; i = i + 2) {
			// take upper shift 4 bits AND wit
			byte upper = b[i];
			byte lower = b[i + 1];
			// upper = (byte)(upper << 8 & 0xFF00);
			// lower = (byte)(lower & 0xFF);
			int result = ((upper << 8 & 0xFF00) + (lower & 0x00FF));
			// sum = sum + ((firstHalf << 8 & 0xFF00) + (secondHalf & 0xFF));
			// add to sum
			sum = sum + (result);
			// check to make sure no overflow
			if ((sum & 0xFFFF0000) > 0) {
				sum &= 0xFFFF;
				sum++;
			}
		}
		// For Odd
		if (b.length % 2 == 1) {
			// add odd bit to sum
			sum = sum + ((b[b.length - 1] << 8) & 0xFF00);

			// Check overflow
			if ((sum & 0xFFFF0000) > 0) {
				sum &= 0xFFFF;
				sum++;
			}
		}
		// return sum
		return (short) ~(sum & 0xFFFF);
	}
}