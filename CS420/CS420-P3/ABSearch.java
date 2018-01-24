import java.util.ArrayList;

public class ABSearch {
	State abSearch(State s) {
		int v = maxValue(s, Integer.MIN_VALUE, Integer.MAX_VALUE);
		return null;
		// return successorState;
	}

	//Somewhere I have to return the index of successors, don't know where
	
	// Max Value Function
	int maxValue(State s, int alpha, int beta) {
		ArrayList<State> successors = s.getSuccessors(s.e1);
		int v;
		if (terminalTest(s))
			return utility(s);

		v = Integer.MIN_VALUE;
		for (int i = 0; i < successors.size(); ++i) {
			v = Math.max(v, minValue(s, alpha, beta));
			if (v >= beta)
				return v;
			alpha = Math.max(alpha, v);
		}
		return v;
	}

	// Min Value Function
	int minValue(State s, int alpha, int beta) {
		int v;
		ArrayList<State> successors = s.getSuccessors(s.e2);
		if (terminalTest(s))
			return utility(s);

		v = Integer.MAX_VALUE;
		for (int i = 0; i < successors.size(); ++i) {
			v = Math.min(v, maxValue(s, alpha, beta));
			if (v <= alpha)
				return v;
			beta = Math.min(beta, v);
		}
		return v;
	}

	// Terminal Test
	boolean terminalTest(State s) {
		if (s.e1.checkWin() || s.e2.checkWin() || s.full)
			return true;
		return false;
	}

	// Utility/Heuristic Function
	int utility(State s) {
		return s.e1.eval() - s.e2.eval();
	}
}
