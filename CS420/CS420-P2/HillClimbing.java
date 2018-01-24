public class HillClimbing {
	int steps;
	
	//Attempts to Solve Using Steepest Hill Climbing Algorithm
	public Board solve(Board b) {
		steps = 0;
		Board next = null;
		while (true) {
			next = null;
			b.neighbors = b.getNeighbors(b.current);
			for (int i = 0; i < b.neighbors.size(); i++) {
				if (b.neighbors.get(i).heuristic < b.heuristic) {
					next = b.neighbors.get(i);
				}
			}
			if (next == null)
				return b;
			b = next;
			steps++;
		}
	}
}