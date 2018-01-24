import java.util.ArrayList;
import java.util.Random;

public class Board implements Comparable<Board> {
	int n = -1;
	int heuristic;
	Queen[] current;
	ArrayList<Board> neighbors;

	// Generates Random Board
	public Board(int n) {
		Random rn = new Random();
		this.n = n;
		current = new Queen[n];

		for (int i = 0; i < n; i++)
			current[i] = new Queen(rn.nextInt(n), i);
		getHeuristic();
	}

	// Copies Board
	public Board(Queen[] q) {
		n = q.length;
		current = new Queen[n];
		for (int i = 0; i < n; i++) {
			current[i] = new Queen(q[i].pos);
		}
		getHeuristic();
	}
	
	// Gets All Possible Queen Movement Positions
	public ArrayList<Board> getNeighbors(Queen[] q) {
		ArrayList<Board> b = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			int ogPos = q[i].pos[0];
			q[i].pos[0] = 0;
			while (q[i].pos[0] < n-1) {
				q[i].pos[0]++;
				b.add(new Board(q));
			}
			q[i].pos[0] = ogPos;
		}
		return b;
	}

	// Gets the Heuristic for the Current Queen Positions
	public void getHeuristic() {
		heuristic = 0;
		for (int i = 0; i < n - 1; i++)
			for (int j = i + 1; j < n; j++)
				if (current[i].isAttacking(current[j])) {
					heuristic++;
				}
	}

	// Prints Board
	public void print() {
		int[][] board = new int[n][n];
		for (int i = 0; i < n; i++) {
			board[current[i].pos[0]][current[i].pos[1]] = 1;
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++)
				System.out.print(board[j][i]);
			System.out.println();
		}
		System.out.println();
	}

	@Override
	public int compareTo(Board b) {
		if (b.heuristic > heuristic)
			return -1;
		else if (b.heuristic < heuristic)
			return 1;
		return 0;
	}

}
