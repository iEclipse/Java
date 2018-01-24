import java.util.*;
public class UI {
	Scanner s = new Scanner(System.in);
	Grid g = new Grid();
	AbstractCard[][] val;
	int[] pos = new int[4];

	public void intro() {
		System.out.println("=========================================="
				+ "\n       Welcome to the Memory game.\n  Win by matching every card. Good luck!"
				+ "\n=========================================="
				+ "\n      Enter 'x' to quit at any time.");
		g.initialize();
	}
	public void drawGrid(int cards) {
		val = g.getGrid();
		System.out.println();
		for (int i = 0; i < val.length; ++i) {
			System.out.print("          ");
			for (int j = 0; j < val[i].length; ++j) {
				if (val[i][j].getFlipped())
					System.out.print("[ " + val[i][j].getType() + " ]");
				else if (i * 4 + j + 1 < 10)
					System.out.print("[00" + (i * 4 + j + 1) + "]");
				else
					System.out.print("[0" + (i * 4 + j + 1) + "]");
			}
			System.out.println("");
		}
		System.out.println("\nTotal Cards flipped: " + cards
				+ "\n------------------------------------------");
	}
	public void selectCard1() {
		try {
			System.out.print("Select a card (1): ");
			int card = s.nextInt();
			if (card > 0 && card < 17) {
				pos[0] = (card - 1) / 4;
				pos[1] = (card - 1) % 4;
				if (val[pos[0]][pos[1]].getFlipped()) {
					System.out.print("This card has already been revealed. \n");
					selectCard1();
				}
				val[pos[0]][pos[1]].setFlipped(true);
			}
			else {
				System.out.print("Invalid Input. ");
				s.nextLine();
				selectCard1();
			}
		} catch (InputMismatchException e) {
			System.out.print("Invalid Input. ");
			s.nextLine();
			selectCard1();
		}
	}
	public void selectCard2() {
		try {
			System.out.print("Select a card (2): ");
			int card = s.nextInt();
			if (card > 0 && card < 17) {
				pos[2] = (card - 1) / 4;
				pos[3] = (card - 1) % 4;
				if (val[pos[2]][pos[3]].getFlipped()) {
					System.out.print("This card has already been revealed. \n");
					selectCard2();
				}
				val[pos[2]][pos[3]].setFlipped(true);
			}
			else {
				System.out.print("Invalid Input. ");
				s.nextLine();
				selectCard2();
			}
		} catch (InputMismatchException e) {
			System.out.print("Invalid input. ");
			s.nextLine();
			selectCard2();
		}
	}
	public boolean checkCard() {
		if (val[pos[0]][pos[1]].equals(val[pos[2]][pos[3]])) {
			System.out.println("You have matched two cards.");
			return true;
		}
		else {
			val[pos[0]][pos[1]].setFlipped(false);
			val[pos[2]][pos[3]].setFlipped(false);
			System.out.println("The cards you've selected don't match.");
			return false;
		}
	}
	public void end(int val) {
		System.out.println("Congratulations! You have completed the Memory game."
				+ "\nTurns taken: " + (val/2));
	}
}