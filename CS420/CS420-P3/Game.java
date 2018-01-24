import java.util.Scanner;

public class Game {
	public static void main(String[] args) {
		ABSearch ab = new ABSearch();
		Scanner sc = new Scanner(System.in);
		State s;
		boolean turn = true;

		// Decide Who Goes First
		System.out.print("Who Goes First?\nPlayer = '0', Opponent = '1': ");
		String input = sc.nextLine();

		while (!input.equals("0") && !input.equals("1")) {
			System.out.println("Error: Invalid Input\n");
			System.out.print("Who Goes First?\nPlayer = '0', Opponent = '1': ");
			input = sc.nextLine();
		}
		// Set Player as E1
		if (input.equals("0")) {
			s = new State(true);
			turn = true;
			System.out.println("Player Goes First.");

			// Set Opponent as E1
		} else if (input.equals("1")) {
			s = new State(false);
			turn = false;
			System.out.println("Opponent Goes First.");

			// Doesn't Happen
		} else
			s = null;

		System.out.print("Enter Delay (Seconds): ");
		input = sc.nextLine();

		try {
			// Timeout in Milliseconds (Currently Unused)
			int delayMS = Integer.parseInt(input) * 1000;
		} catch (Exception e) {
			System.out.println("Invalid Number. Restart the program and try again.");
		}

		// Print Empty Board
		System.out.println("\n" + s.toString());

		while (!ab.terminalTest(s)) {
			// Player Turn
			if (turn) {
				long start = System.currentTimeMillis(); // Unused Timer Variable
				// s = ab.abSearch(s); Doesn't Work
				// if (System.currentTimeMillis() - start > delayMS)
				// End Search
				String nextMove = "A1";

				// Check which Entity is the Player
				if (s.e1.name.equals("Player"))
					s.e1.moves.put(s.e1.moves.size() + 1, nextMove);
				else
					s.e2.moves.put(s.e2.moves.size() + 1, nextMove);
				// Opponent Turn
			} else {
				System.out.print("Enter Opponent Move: ");
				input = sc.nextLine().toUpperCase();
				while (input.length() != 2 || input.charAt(0) > 'H' || input.charAt(0) < 'A' || input.charAt(1) < '1'
						|| input.charAt(1) > '9' || s.e1.moves.containsValue(input) || s.e2.moves.containsValue(input)
						|| s.e1.moves.containsValue(input)) {
					System.out.println("Error: Invalid Input\n");
					System.out.print("Enter Opponent Move: ");
					input = sc.nextLine().toUpperCase();
				}
				// Check which Entity is the Opponent
				if (s.e1.name.equals("Opponent"))
					s.e1.moves.put(s.e1.moves.size() + 1, input);
				else
					s.e2.moves.put(s.e2.moves.size() + 1, input);
			}
			turn = !turn;

			// Print Board Info for the Turn
			System.out.println("\n" + s.toString());
			System.out.println(s.getMoves());
		}
		System.out.println(s.e1.name + " Winstate: " + s.e1.checkWin());
		System.out.println(s.e2.name + " Winstate: " + s.e2.checkWin());
		sc.close();
	}
}
