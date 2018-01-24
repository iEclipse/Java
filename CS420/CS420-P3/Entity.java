import java.util.HashMap;

public class Entity {

	// Stores Moves for the Entity Format: <Index, Position[xy]>
	HashMap<Integer, String> moves;
	// Name of the Entity
	String name;

	public Entity(String name) {
		this.name = name;
		moves = new HashMap<>();
	}

	public Entity(Entity e) {
		name = e.name;
		moves = new HashMap<>();
		for (int i = 1; i <= moves.size(); ++i)
			moves.put(i, moves.get(i));
	}

	// Also Implement Draw

	public boolean checkWin() {
		// Stores Moves
		boolean[] statusX = new boolean[8];
		boolean[] statusY = new boolean[8];

		for (int i = 1; i <= moves.size(); ++i) {
			// Check Row
			statusY[moves.get(i).charAt(0) - 'A'] = true;
			// Check Column
			statusX[moves.get(i).charAt(1) - '1'] = true;
		}

		// Check for Win
		for (int i = 0; i < 5; ++i)
			if ((statusX[i] && statusX[i + 1] && statusX[i + 2] && statusX[i + 3])
					|| (statusY[i] && statusY[i + 1] && statusY[i + 2] && statusY[i + 3]))
				return true;
		return false;
	}

	// Evaluation Function
	public int eval() {
		boolean[] statusX = new boolean[8];
		boolean[] statusY = new boolean[8];

		int val = -1;
		if (checkWin())
			return 1000000;

		for (int i = 1; i <= moves.size(); ++i) {
			// Check Row
			statusY[moves.get(i).charAt(0) - 'A'] = true;
			// Check Column
			statusX[moves.get(i).charAt(1) - '1'] = true;
		}

		for (int i = 0; i < 8; ++i)
			if (statusX[i] && statusX[i + 1])
				val += 5;
		for (int i = 0; i < 8; ++i)
			if (statusY[i] && statusY[i + 1])
				val += 5;
		return val;
	}

}
