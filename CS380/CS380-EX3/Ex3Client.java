import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Ex3Client {
	public static void main(String[] args) {
		try {
			Socket s = new Socket("18.221.102.182", 38103);
			DataOutputStream dos = new DataOutputStream(s.getOutputStream());
			DataInputStream dis = new DataInputStream(s.getInputStream());
			byte[] bytes = new byte[dis.read()];

			for (int i = 0; i < bytes.length; i++) {
				bytes[i] = dis.readByte();
			}

			short checksum = checksum(bytes);

			dos.write(checksum >> 8);
			dos.flush();
			dos.write(checksum);
			dos.flush();

			System.out.println(dis.read());
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static short checksum(byte[] b) {
		int sum = 0;

		for (int i = 0; i < b.length - 1; i += 2) {
			byte upper = b[i];
			byte lower = b[i + 1];

			// Add both
			sum += ((upper << 8 & 0xFF00) + (lower & 0xFF));
			if ((sum & 0xFFFF0000) > 0) {
				sum &= 0xFFFF;
				sum++;
			}
		}

		if (b.length % 2 == 1) {
			// Upper Bytes
			sum += b[b.length - 1] << 8 & 0xFF00;

			// Overflow Check
			if ((sum & 0xFFFF0000) > 0) {
				sum &= 0xFFFF;
				sum++;
			}
		}
		return (short) ~(sum & 0xFFFF);
	}
}
