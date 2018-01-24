import java.security.SecureRandom;
public class Encryption {

	// Creates key
	// Generates random character positions
	// Encrypts characters
	// Fills extra positions
	// Offsets positions

	SecureRandom random = new SecureRandom();
	char[] key;
	int[] positions;
	char[] encryptedPos;
	char[] code;
	int codeLength;
	int index;

	// Message, Offset

	protected char[] encrypt(char[] input1, int input2) {
		codeLength = random.nextInt(Character.MAX_VALUE);
		generateKey(input1);
		generatePositions(input1);
		encryptMsg(input1);
		generateCode(input1);
		encryptPositions(input2);
		return code;
	}
	private void generateKey(char[] input1) {
		key = new char[input1.length];
		for (int i = 0; i < input1.length; i++)
			key[i] = (char)random.nextInt(Character.MAX_VALUE);
	}
	private void generatePositions(char[] input1) {
		positions = new int[input1.length];
		for (int i = 0; i < input1.length; i++)
			positions[i] = -1;
		for (int i = 0; i < input1.length; i++) {
			while (!checkPosition(input1))
				index = random.nextInt(codeLength);
			positions[i] = index;
		}
	}
	private boolean checkPosition(char[] input1) {
		for (int i = 0; i < input1.length; i++)
			if (positions[i] == index)
				return false;
		return true;
	}
	private void encryptMsg(char[] input1) {
		for (int i = 0; i < input1.length; i++)
			input1[i] += key[i];
	}
	private void generateCode(char[] input1) {
		code = new char[codeLength];
		for (int i = 0; i < code.length; i++)
			for (int j = 0; j < input1.length; j++)
				if (i == positions[j])
					code[i] = (char) input1[j];
				else if (code[i] == 0)
					code[i] = (char) random.nextInt();
	}
	private void encryptPositions(int input2) {
		encryptedPos = new char[positions.length];
		for (int i = 0; i < positions.length; i++)
			encryptedPos[i] = (char) (positions[i] + input2);
	}
	protected char[] getKey() {
		return key;
	}
	protected char[] getPos() {
		return encryptedPos;
	}
	protected char[] getCode() {
		return code;
	}
}