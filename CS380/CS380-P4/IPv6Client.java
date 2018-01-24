import java.io.OutputStream;
import java.net.Socket;

import java.io.DataInputStream;

public class IPv6Client {
	public static void main(String[] args) {
		try (Socket socket = new Socket("18.221.102.182", 38004)) {
			System.out.println("Connected to Server.");

			DataInputStream is = new DataInputStream(socket.getInputStream());
			OutputStream os = socket.getOutputStream();

			// IP Version Set to 6
			int version = 6;

			// Don't Implement
			int trafficClass = 0;

			// Don't Implement
			int flowLabel = 0;

			// Length of Data in Bytes, Doubles Every Time
			int payloadLength = 2;

			// Header length (40 bytes)
			int length = 40;

			// Next Header Set to UDP Protocol Value
			int nextHeader = 17;

			// Hop Limit Set to 20
			int hopLimit = 20;

			// Array for each field of IPv6
			byte[] packet;

			// Destination Address

			byte[] destAddressv4 = socket.getInetAddress().getAddress();

			byte[] destAddressv6 = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, (byte) 255, (byte) 255, destAddressv4[0],
					destAddressv4[1], destAddressv4[2], destAddressv4[3] };

			// Source Address
			byte[] srcAddress = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, (byte) (255), (byte) (255), 127, 0, 0, 1 };

			for (int i = 0; i < 12; i++) {
				int totalLength = length + payloadLength;
				packet = new byte[totalLength];

				// Version
				packet[0] = (byte) ((version << 4 & 0xFF));

				// Skip Traffic/Flow [Index 0-3]

				// Split Payload Length into Two Bytes
				packet[4] = (byte) (payloadLength >>> 8);
				packet[5] = (byte) payloadLength;

				// Next Header
				packet[6] = (byte) nextHeader;

				// Hop Limit
				packet[7] = (byte) hopLimit;

				// Source Address [16 Bytes]
				for (int j = 0; j < srcAddress.length; j++)
					packet[8 + j] = srcAddress[j];

				// Destination Address [16 Bytes]
				for (int j = 0; j < destAddressv6.length; j++)
					packet[24 + j] = destAddressv6[j];

				for (int j = 40; j < packet.length; j++) {
					packet[j] = 0;
				}

				// Send Packet to Server
				os.write(packet);
				os.flush();

				System.out.println("data length: " + payloadLength);
				
				// Server Response
				for (int j = 0; j < 4; j++) {
					System.out.print(Integer.toHexString(is.readByte()).replaceAll("ff", "").toUpperCase());
				}
				System.out.println("\n");
				// Increase Payload Length
				payloadLength *= 2;
			}
			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Disconnected from Server.");
	}
}