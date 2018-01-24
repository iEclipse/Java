public class Decryption {

	// Finds positions
	// Removes offsets
	// Decrypts code

	int[] positions;
	char[] message;

	// Key, charPos, Code, Offset

	protected char[] decrypt(char[] input1, char[] input2, char[] input3, int input4) {
		decryptPositions(input2, input4);
		message = decryptCode(input3, input1);
		return message;
	}
	private void decryptPositions(char[] input1, int input2) {
		positions = new int[input1.length];
		for (int i = 0; i < input1.length; i++)
			positions[i] = (int) (input1[i] - input2);
	}
	private char[] decryptCode(char[] input1, char[] input2) {
		char[] message = new char[positions.length];
		for (int i = 0; i < positions.length; i++) {
			message[i] = (char) (input1[positions[i]] - input2[i]);
		}
		return message;
	}
}
