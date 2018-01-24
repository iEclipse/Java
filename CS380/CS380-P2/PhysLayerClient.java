import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class PhysLayerClient {
	private static Socket socket;

	public static void main(String[] args) {
		try {
			byte[] bytes = new byte[320]; // Array of received bytes 32 bytes as 256 bits, but 4B/5B encoding translates
											// into 320 bits
			int[] sendBack = new int[32]; // Sends these bytes back
			socket = new Socket("18.221.102.182", 38002);
			DataInputStream in = new DataInputStream(socket.getInputStream());
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			double avg = 0; // Requires double for accurate calculations

			System.out.print("Connected to server.");

			for (int i = 0; i < 64; i++)
				avg += in.read(); // Receives Preamble
			avg /= 64; // Sets baseline as the average of 64 unsigned integers
			System.out.printf("\nBaseline established from preamble: %.2f\n", avg); // Prints Baseline

			for (int i = 0; i < bytes.length; i++) {
				if (in.read() > avg) { // Receives bytes as bits and assigns value 0 or 1
					bytes[i] = 1;
				} else
					bytes[i] = 0;
			}

			sendBack = translate(bytes); // Uses translate function to decode message

			System.out.print("Received 32 bytes: ");
			for (int i = 0; i < sendBack.length; i++) {
				System.out.print(Integer.toHexString(sendBack[i]).toUpperCase()); // Prints decoded message
				out.write(sendBack[i]); // Sends bytes back to server
				out.flush();
			}

			if (in.readByte() == 1)
				System.out.println("\nResponse good.");
			else
				System.out.println("\nResponse bad.");
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

	static int[] translate(byte[] bytes) {
		byte[] nrzi = bytes;
		int[] b4 = new int[32];
		String b5 = "" + bytes[0];

		for (int i = 1; i < bytes.length; i++) { // Performs NRZI decoding
			if (nrzi[i] == nrzi[i - 1])
				b5 += "0";
			else
				b5 += "1";
		}

		for (int i = 0; i < nrzi.length / 5; i++) { // Performs 4B/5B decoding
			switch (b5.substring(5 * i, 5 * i + 5)) {
			case "11110":
				nrzi[4 * i] = 0;
				nrzi[4 * i + 1] = 0;
				nrzi[4 * i + 2] = 0;
				nrzi[4 * i + 3] = 0;
				break;
			case "01001":
				nrzi[4 * i] = 0;
				nrzi[4 * i + 1] = 0;
				nrzi[4 * i + 2] = 0;
				nrzi[4 * i + 3] = 1;
				break;
			case "10100":
				nrzi[4 * i] = 0;
				nrzi[4 * i + 1] = 0;
				nrzi[4 * i + 2] = 1;
				nrzi[4 * i + 3] = 0;
				break;
			case "10101":
				nrzi[4 * i] = 0;
				nrzi[4 * i + 1] = 0;
				nrzi[4 * i + 2] = 1;
				nrzi[4 * i + 3] = 1;
				break;
			case "01010":
				nrzi[4 * i] = 0;
				nrzi[4 * i + 1] = 1;
				nrzi[4 * i + 2] = 0;
				nrzi[4 * i + 3] = 0;
				break;
			case "01011":
				nrzi[4 * i] = 0;
				nrzi[4 * i + 1] = 1;
				nrzi[4 * i + 2] = 0;
				nrzi[4 * i + 3] = 1;
				break;
			case "01110":
				nrzi[4 * i] = 0;
				nrzi[4 * i + 1] = 1;
				nrzi[4 * i + 2] = 1;
				nrzi[4 * i + 3] = 0;
				break;
			case "01111":
				nrzi[4 * i] = 0;
				nrzi[4 * i + 1] = 1;
				nrzi[4 * i + 2] = 1;
				nrzi[4 * i + 3] = 1;
				break;
			case "10010":
				nrzi[4 * i] = 1;
				nrzi[4 * i + 1] = 0;
				nrzi[4 * i + 2] = 0;
				nrzi[4 * i + 3] = 0;
				break;
			case "10011":
				nrzi[4 * i] = 1;
				nrzi[4 * i + 1] = 0;
				nrzi[4 * i + 2] = 0;
				nrzi[4 * i + 3] = 1;
				break;
			case "10110":
				nrzi[4 * i] = 1;
				nrzi[4 * i + 1] = 0;
				nrzi[4 * i + 2] = 1;
				nrzi[4 * i + 3] = 0;
				break;
			case "10111":
				nrzi[4 * i] = 1;
				nrzi[4 * i + 1] = 0;
				nrzi[4 * i + 2] = 1;
				nrzi[4 * i + 3] = 1;
				break;
			case "11010":
				nrzi[4 * i] = 1;
				nrzi[4 * i + 1] = 1;
				nrzi[4 * i + 2] = 0;
				nrzi[4 * i + 3] = 0;
				break;
			case "11011":
				nrzi[4 * i] = 1;
				nrzi[4 * i + 1] = 1;
				nrzi[4 * i + 2] = 0;
				nrzi[4 * i + 3] = 1;
				break;
			case "11100":
				nrzi[4 * i] = 1;
				nrzi[4 * i + 1] = 1;
				nrzi[4 * i + 2] = 1;
				nrzi[4 * i + 3] = 0;
				break;
			case "11101":
				nrzi[4 * i] = 1;
				nrzi[4 * i + 1] = 1;
				nrzi[4 * i + 2] = 1;
				nrzi[4 * i + 3] = 1;
				break;
			default:
				System.out.println("ERROR: " + b5);
			}
		}
		for (int i = 0; i < b4.length; i++) { //Converts the 256 bits back to 32 bytes
			b4[i] = nrzi[8 * i] * 128 + nrzi[8 * i + 1] * 64 + nrzi[8 * i + 2] * 32 + nrzi[8 * i + 3] * 16
					+ nrzi[8 * i + 4] * 8 + nrzi[8 * i + 5] * 4 + nrzi[8 * i + 6] * 2 + nrzi[8 * i + 7] * 1;
		}
		return b4; //Returns bytes
	}
}
