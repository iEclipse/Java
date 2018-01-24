import java.util.ArrayList;
import java.util.Arrays;

public class State {
	int h1;
	int h2;
	int[] goal = { 0, 1, 2, 3, 4, 5, 6, 7, 8 }; // Goal Board
	int[] board; // Current Board

	public State(int[] board) {
		this.board = board;
		setH();
	}

	void setH() { // Set Heuristics
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
	}

	int[] copy(int[] b) { // Copy Board
		int[] board = new int[b.length];
		for (int i = 0; i < b.length; i++)
			board[i] = b[i];
		return board;
	}

	int getBlank() { // Get Location of 0
		for (int i = 0; i < board.length; i++)
			if (board[i] == 0)
				return i;
		return -1;
	}

	ArrayList<State> getNeighbors() { // Get List of Neighbors
		ArrayList<State> neighbors = new ArrayList<State>();
		int blank = getBlank();

		if (blank % 3 != 0) // Left
			swap(blank - 1, blank, neighbors);
		if (blank < 6) // Down
			swap(blank + 3, blank, neighbors);
		if (blank > 2) // Up
			swap(blank - 3, blank, neighbors);
		if (blank % 3 < 2) // Right
			swap(blank + 1, blank, neighbors);

		return neighbors;
	}

	void swap(int a, int b, ArrayList<State> s) { // Swaps Values on Board
		int[] copy = copy(board);
		int temp = copy[a];
		copy[a] = copy[b];
		copy[b] = temp;
		s.add(new State(copy));
	}

	boolean equals(State s) { // Checks to See if Equal
		return Arrays.equals(board, s.board);
	}

	boolean isGoal() { // Checks to See if Solved
		return Arrays.equals(board, goal);
	}

	void print() { // Prints Board
		for (int i = 0; i < board.length; i++) {
			if (i % 3 == 0 && i != 0)
				System.out.println();
			System.out.print(board[i] + " ");
		}
		System.out.println("\n");
	}

}
