import java.util.ArrayList;

public class State {

	// Player or Opponent
	Entity e1;
	Entity e2;
	boolean full;

	// Decides Order of Turns
	public State(boolean mode) {
		if (mode) {
			// Mode 0 - Player Goes First
			e1 = new Entity("Player");
			e2 = new Entity("Opponent");
		} else {
			// Mode 1 - Opponent Goes First
			e1 = new Entity("Opponent");
			e2 = new Entity("Player");
		}
	}

	// Copy Constructor
	public State(State s, int i, int j, String name) {
		e1 = new Entity(s.e1);
		e2 = new Entity(s.e2);
		full = s.full;
		if (e1.name.equals(name))
			e1.moves.put(e1.moves.size() + 1, (char) ('A' + 2) + "" + (char) ('0' + 2));
		else
			e2.moves.put(e1.moves.size() + 1, (char) ('A' + 2) + "" + (char) ('0' + 2));
	}

	// Gets Successors of State, Given Name of Entity
	ArrayList<State> getSuccessors(Entity e) {
		ArrayList<State> successors = new ArrayList<>();
		int[][] board = new int[8][8];

		for (int i = 1; i <= e1.moves.size(); ++i) {
			// Check Row
			board[e1.moves.get(i).charAt(0) - 'A'][e1.moves.get(i).charAt(1) - '1'] = 1;
		}
		for (int i = 1; i <= e2.moves.size(); ++i) {
			// Check Row
			board[e2.moves.get(i).charAt(0) - 'A'][e2.moves.get(i).charAt(1) - '1'] = 1;
		}

		for (int i = 0; i < board.length; ++i)
			for (int j = 0; j < board[i].length; ++j)
				if (board[i][j] == 0) {
					// Add Successor
					successors.add(new State(this, i, j, e.name));
				}
		return successors;
	}

	@Override
	// Generates String Representation of the Board
	public String toString() {
		// Numbers on Board
		String output = "  1 2 3 4 5 6 7 8\n";

		for (int y = 1; y < 9; ++y) {
			// Letters on Board
			output += (char) ('@' + y);
			for (int x = 1; x < 9; ++x)
				// Prints e1 Move
				if (e1.moves.containsValue((char) ('@' + y) + "" + x))
					output += " X";
				// Prints e2 Move
				else if (e2.moves.containsValue((char) ('@' + y) + "" + x))
					output += " O";
				// Prints Unmarked Tile
				else
					output += " -";
			// Print Next Row
			output += "\n";
		}
		return output;
	}

	// Generates String Representation of the Moveset
	public String getMoves() {
		String output = e1.name + " vs. " + e2.name + "\n";

		int size = e1.moves.size() > e2.moves.size() ? e1.moves.size(): e2.moves.size();
		
		// Empty Board Notification
		if (e1.moves.size() == 0 && e2.moves.size() == 0)
			return "Error: The Board is Empty";
		for (int i = 1; i <= size; ++i) {
			// Prints Turn Information
			output += "   " + i + ". ";
			// Prints e1 Move
			output += e1.moves.get(i);
			// Prints e2 Move
			if (i <= e2.moves.size())
				output += ", " + e2.moves.get(i);
			// Prints Next Line
			output += "\n";
		}
		return output;
	}
}
