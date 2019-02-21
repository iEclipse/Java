import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Trie t = new Trie();

		// Add Words to Dictionary
		t.addWord("a");
		t.addWord("are");
		t.addWord("art");
		t.addWord("area");
		t.addWord("artist");

		// Print ALL Words
		System.out.println("Dictionary:\n=================");
		for (int i = 0; i < t.letters.length; i++) {
			t.print(t.letters[i], new String());
		}

		// Loop Prompt for Prefix
		while (true) {
			System.out.print("\nStarts With: ");
			t.complete(sc.nextLine());
		}
	}
}
