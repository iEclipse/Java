import java.io.*;
import java.util.Scanner;

public class Main {

	static Scanner sc = new Scanner(System.in);
	static FileHandler fh = new FileHandler();
	static Encryption e = new Encryption();
	static Decryption d = new Decryption();
	static UI ui = new UI();
	static Variable v = new Variable();
	static String message;
	static String input;
	static char[] convertedMessage;
	static int offset = 9927;

	public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException {
		menu();
	}

	private static void menu() throws FileNotFoundException, IOException, ClassNotFoundException {
		while (true) {
			ui.intro();
			ui.menu();
			switch (sc.nextLine()) {
				case "1" :
					encrypt();
					break;
				case "2" :
					decrypt();
					break;
				case "3" :
					ui.notes();
					break;
				case "4" :
					ui.quit();
					System.exit(0);
				default :
					ui.error1();
			}
		}
	}

	private static void encrypt() throws FileNotFoundException, IOException {
		ui.encryptOpt();
		switch (sc.nextLine()) {
			case "1" :
				message = "";
				input = "";
				ui.getMessage();
				while (!input.contains("/end")) {
					input = sc.nextLine() + "\n";
					message += input;
				}
				message = message.replace("/end", "");
				convertedMessage = message.toCharArray();
				convertedMessage = e.encrypt(convertedMessage, offset);
				v.assign(e.getKey(), e.getPos(), e.getCode());
				ui.blank();
				ui.getFileName();
				fh.write(sc.nextLine());
				ui.write();
				break;
			case "2" :
				message = "";
				input = "";
				ui.getFileName();
				input = sc.nextLine();
				message = fh.readText(input);
				convertedMessage = message.toCharArray();
				convertedMessage = e.encrypt(convertedMessage, offset);
				v.assign(e.getKey(), e.getPos(), e.getCode());
				if (input == "error")
					break;
				fh.write(input);
				ui.write();
				break;
			case "3" :
				ui.blank();
				break;
			default :
				ui.error1();
		}
	}
	private static void decrypt() throws FileNotFoundException, IOException, ClassNotFoundException {
		message = "";
		input = "";
		ui.decryptOpt();
		fh.read(sc.nextLine());
		if (input != "error"){
		convertedMessage = d.decrypt(v.a, v.b, v.c, offset);
		ui.beginMessage();
		System.out.print(String.valueOf(convertedMessage));
		ui.endMessage();
		}
	}
}