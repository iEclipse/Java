import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		RandomBoard b = new RandomBoard();
		Search s = new Search();

		if (b.debug)
			System.out.println(b.debugText + "\n");

		if (b.checkSolvable()) {
			System.out.println("SOLVING USING H1");
			s.search(b.board, 0);
			System.out.println("\nSOLVING USING H2");
			s.search(b.board, 1);
		} else
			System.out.println("Unsolvable");

		while (true) {
			try {
				b.debugText = "\n=============\n\nManually Generated Board:";
				System.out.print("\nExample Input: \"1 4 6 7 0 3 2 8 5\"\nManually Enter Puzzle: ");
				b.board = b.generate(sc.nextLine().split(" "));
				b.calcH();
				if (b.checkSolvable()) {
					System.out.println("\nSOLVING USING H1");
					s.search(b.board, 0);
					System.out.println("\nSOLVING USING H2");
					s.search(b.board, 1);
				}
				if (b.debug)
					System.out.println(b.debugText);
			} catch (Exception e) {
				System.out.println("Invalid input. Try again.");
			}
		}
	}

}
