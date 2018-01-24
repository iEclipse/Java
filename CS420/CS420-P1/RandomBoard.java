import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class RandomBoard {
	Scanner sc = new Scanner(System.in);
	Random rn = new Random();
	int[] board;
	int h1, h2;
	boolean debug = false;
	String debugText = "Debug Info\n=============\n\nGenerated Board:";

	public RandomBoard() {
		generate();
		calcH();
	}

	public int[] generate() { // Generates Random Board as Integer Array
		ArrayList<Integer> values = new ArrayList<Integer>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8));
		board = new int[9];
		for (int i = 0; i < board.length; i++) {
			board[i] = values.remove(rn.nextInt(values.size()));
			if (i % 3 == 0)
				debugText += "\n";
			debugText += (board[i] + " ");
		}
		return board;
	}

	public int[] generate(String[] values) { // Generates Board Given an Integer Array
		board = new int[9];
		for (int i = 0; i < board.length; i++) {
			board[i] = Integer.parseInt(values[i]);
			if (i % 3 == 0)
				debugText += "\n";
			debugText += (board[i] + " ");
		}
		return board;
	}

	public void calcH() { // Calculates Heuristics
		h1 = h2 = 0;
		for (int i = 0; i < board.length; i++) {
			if (board[i] != i) // How Many #'s are Incorrectly Located
				h1++;
		}

		for (int y = 0, i = 0; y < 3; y++) { // Manhattan Distance
			for (int x = 0; x < 3; x++) {
				int val = (board[i++] - 1);

				if (val != -1) {
					h2 += Math.abs(val / 3 - (y)) + Math.abs(val % 3 - (x));
				}
			}
		}
		debugText += "\n\nHeuristic 1: " + h1 + "\n" + "Heuristic 2: " + h2;
	}

	public boolean checkSolvable() { // Checks to See if Board is Solvable
		int inversions = 0;
		for (int i = 0; i < board.length - 1; i++) {
			for (int j = i + 1; j < board.length; j++)
				if (board[i] > board[j])
					inversions++;
			if (board[i] == 0 && i % 2 == 1)
				inversions++;
		}
		debugText += "\nInversions: " + inversions + "\nSolvable: " + (inversions % 2 == 0);
		return (inversions % 2 == 0);
	}
}
