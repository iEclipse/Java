import java.io.File;
import java.util.Scanner;

//Manages all functions dealing with files
public class FileHandler {
	HashCode hc = new HashCode();
	File directory;
	File[] files;

	// Creates variables given the argument from main class
	// and returns whether or not the folder exists
	public boolean getFiles(String folder) {
		directory = new File(folder);
		files = directory.listFiles();
		if (!directory.exists()) {
			System.out.println("Folder does not exist.");
			return false;
		}
		else if (folder.length() < 1) {
			System.out.println("Directory not specified.");
			return false;
		}
		System.out.println("Directory Found: " + directory.getAbsolutePath());
		return true;
	}
	// Lists all keys and their values from each file
	// Directly hashes each value as it prints
	public void displayFiles(boolean debug) throws Exception {
		for (int i = 0; i < files.length; i++) {
			Scanner input = new Scanner(files[i]);
			if (debug)
				System.out.println("\nFilename: " + files[i].getName());
			String key;
			Double value;
			while (input.hasNext())
			{
				key = "";
				while (!input.hasNextDouble())
					key += input.next() + " ";
				key = key.substring(0, key.length() - 1);
				value = new Double(input.next());
				if (debug)
					System.out.printf("Key: %-20sValue: " + value + "\n", key);
				hc.store(key, value);
			}
			input.close();
		}
		if (debug)
			System.out.println();
	}
}
