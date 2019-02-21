import java.util.ArrayList;

public class Trie {
	Node[] letters = new Node[26];

	public Trie() {
		// 0 - A
		// 1 - B
		// 2 - C
		// 3 - D
		// 4 - E
		// 5 - F
		// 6 - G

		// 7 - H
		// 8 - I
		// 9 - J
		// 10 - K
		// 11 - L
		// 12 - M

		// 13 - N
		// 14 - O
		// 15 - P
		// 16 - Q
		// 17 - R
		// 18 - S

		// 19 - T
		// 20 - U
		// 21 - V
		// 22 - W
		// 23 - X
		// 24 - Y
		// 25 - Z

		// Head Node for All Letters
		for (int i = 0; i < letters.length; i++)
			letters[i] = new Node((char) ('a' + i));
	}

	// Adds a Word to the Dictionary
	// Returns Success/Failure of Adding
	boolean addWord(String s) {

		// Changes Word to LowerCase and Removes All Whitespaces
		s = s.toLowerCase().replaceAll("\\s", "");

		// Verifies Word Begins with a Letter
		for (int i = 0; i < s.length(); i++)
			if (s.charAt(i) < 'a' || s.charAt(i) > 'z')
				return false;

		s += ".";

		// Sets Starting Character of Word
		Node current = letters[s.charAt(0) - 'a'];

		// Iterates Through Each Letter to Add the Subsequent Characters
		for (int i = 1; i < s.length(); i++) {

			Node temp = current.get(s.charAt(i)); // Node of the Subsequent Letter
			Node newNode; // New Node of Subsequent Letter if Non-Existing

			// If the Trie Doesn't Contain the Subsequent Letter, Add it, then Point to it
			if (temp == null) {
				newNode = new Node(s.charAt(i));
				current.child.add(newNode);
				current = newNode;

				// Set the Pointer to the Subsequent Node
			} else
				current = temp;
		}
		return true;
	}

	// Prints Words Using the Starting Node and the Current String Prefix
	void print(Node n, String s) {

		// Adds the Next Node's Value to the String
		s += n.value;

		// For Each Letter
		for (int i = 0; i < n.child.size(); i++) {

			// Recursively Call Itself to Find the Last Letter of All Existing Words
			if (n.child.get(i).child.isEmpty()) {
				s += n.child.get(i).value;
				System.out.println(s.substring(0, s.length() - 1));
				s = s.substring(0, s.length() - 1);

				// Print the Word
			} else
				print(n.child.get(i), s);
		}
	}

	// Searches for Possible Outcomes to A Partially Written Word
	void complete(String s) {

		// Changes Word to LowerCase and Removes All Whitespaces
		s = s.toLowerCase().replaceAll("\\s", "");

		// Character Validation (Alpha Characters Only)
		for (int i = 0; i < s.length(); i++)
			if (s.charAt(i) < 'a' || s.charAt(i) > 'z') {
				System.out.println("Invalid Input");
				return;
			}

		// Text Header
		System.out.println("\nAuto-Complete Outcomes:\n============================");

		// Check if Any Words Exist for Trie Head
		if (letters[s.charAt(0) - 'a'].child.isEmpty()) {
			System.out.println("None");
			return;
		}

		// Locate the Furthest Node in the Trie Matching the String
		Node current = letters[s.charAt(0) - 'a'];

		for (int i = 1; i < s.length(); i++) {
			Node temp = current.get(s.charAt(i));
			if (current.get(s.charAt(i)) != null) {
				current = temp;
			} else {
				System.out.println("None");
				return;
			}
		}

		// Prints the Words that Can Auto-Complete the String
		print(current, s.substring(0, s.length() - 1));
	}

	// Node Class
	class Node {

		ArrayList<Node> child;// List of Children
		char value; // Value of Node

		// Constructor
		Node(char value) {
			child = new ArrayList<Node>();
			this.value = value;
		}

		// Returns if Node has a Child with a Specific Value
		boolean contains(char value) {
			for (char i = 0; i < child.size(); ++i)
				if (child.get(i).value == value)
					return true;
			return false;
		}

		// Returns the Node with the Value
		Node get(char value) {
			for (char i = 0; i < child.size(); ++i)
				if (child.get(i).value == value)
					return child.get(i);
			return null;
		}
	}
}
