import java.util.Scanner;

	/* Caesar Shift Project
	 * Inputs: plain text, shift value
	 * Outputs: ciphertext, decrypted text
	 * 
	 * Function/Purpose
	 * ===================
	 * Takes the necessary inputs and converts them into ciphertext.
	 * Prints the ciphertext, decrypts the newly encrypted string,
	 * and prints the reverted text. */

public class Project1 {

	static Scanner sc = new Scanner(System.in);
	static char[] text;
	static int shift;
	
	// Program cycle
	public static void main(String[] args) {
		while (true){
			getString();
			checkStatus();
			getShiftIndex();
			encrypt();
			printText();
			decrypt();
			printText();
			System.out.println();
		}
	}
	
	// Polls the user for a string to encrypt and converts to a char array 
	// Variable Used: text
	static void getString(){
		System.out.print("Enter text (Leave empty and press enter to quit): ");
		text = sc.nextLine().toCharArray();
	}
	
	// Polls the user for a shift index and reduces to necessary value
	// Variable Used: shift
	static void getShiftIndex(){
		System.out.print("Enter shift value: ");
		shift = sc.nextInt() % 26;
		sc.nextLine();
	}
	
	// Checks to see if user wants to exit the program
	static void checkStatus(){
		if (text.length == 0){
			System.out.println("Terminated..");
			System.exit(0);
		}
	}
	
	// Encrypts message based on shift value
	static void encrypt(){
		for (int i = 0; i < text.length; i++){
			if (text[i] != ' ' && text[i] >= 'a' && text[i] <= 'z'){
				if (text[i] + shift > 'z' && shift > 0)
					text[i]-=26;
				else if (text[i] + shift < 'a' && shift < 0)
					text[i]+=26;
				text[i] += shift;}
		}
	}
	
	// Reverses the encryption method
	static void decrypt(){
		for (int i = 0; i < text.length; i++){
			if (text[i] != ' ' && text[i] >= 'a' && text[i] <= 'z'){
				if (text[i] - shift < 'a' && shift > 0)
					text[i]+=26;
				else if (text[i] - shift > 'z' && shift < 0)
					text[i]-=26;
				text[i] -= shift;}
		}
	}
	
	//Prints the array of characters
	static void printText(){
		for (int i = 0; i < text.length; i++)
			System.out.print(text[i]);
		System.out.println();
	}
}
