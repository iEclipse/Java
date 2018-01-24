import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.zip.CRC32;
import javax.xml.bind.DatatypeConverter;

public class Ex2Client {
	private static Socket socket;

	public static void main(String[] args) {
		try {
			byte[] b = new byte[100]; // Array of received bytes
			byte[] crc32; // 4 Bytes of CRC32
			socket = new Socket("18.221.102.182", 38102);
			DataInputStream in = new DataInputStream(socket.getInputStream());
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			CRC32 c = new CRC32();

			System.out.print("Connected to server.\nReceived bytes:");

			for (int i = 0; i < 100; i++) {
				if (i % 10 == 0)
					System.out.print("\n   ");
				b[i] = (byte) (in.read() * 16 + in.read()); // Combines and stores as 100 bytes
				System.out.printf("%02X", b[i]); // Prints received bytes
			}
			System.out.println();
			c.update(b); // Generates CRC32
			System.out.printf("Generated CRC32: %08X.\n", c.getValue()); // Prints CRC32 in hex
			crc32 = DatatypeConverter.parseHexBinary((Long.toHexString(c.getValue()))); // Converts CRC32 to byte array
			for (int i = 0; i < crc32.length; i++) {
				out.writeByte(crc32[i]); // Sends 4 Bytes of CRC32 to server, avoids overflow errors
				out.flush();
			}
			if (in.readByte() == 1)
				System.out.println("Response good.");
			else
				System.out.println("Response bad.");
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			try {
				socket.close(); // Disconnects after completion
				System.out.println("Disconnected from server.");
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}
