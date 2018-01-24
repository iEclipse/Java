import java.io.*;
import java.util.Scanner;

public class FileHandler extends Main {
	public void write(String file) {
		try {
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(Main.v);
			oos.close();
		} catch (IOException e) {
			System.out.println("Error: Cannot save with that file name.\n");
			Main.input = "error";
		}
	}
	public void read(String file){
		try {
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);
			Main.v = (Variable) ois.readObject();
			ois.close();
		} catch (ClassNotFoundException e) {
			System.out.println("Error: File does not exist or is not encrypted.\n");
			Main.input = "error";
		} catch (IOException e) {
			System.out.println("Error: File does not exist or is not encrypted.\n");
			Main.input = "error";
		}
	}
	public String readText(String file) {
		try {
			File f = new File(file);
			Scanner s = new Scanner(f);
			String message = "";
			while (s.hasNextLine())
				message += s.nextLine();
			s.close();
			return message;
		} catch (IOException e) {
			System.out.println("Error: File does not exist.\n");
			Main.input = "error";
		}
		return "";
	}
}
