
public class Queen {
	int[] pos;

	// Sets Queen Position
	public Queen(int x, int y) {
		pos = new int[] { x, y };
	}

	// Sets Queen Position
	public Queen(int[] pos) {
		this.pos = new int[] { pos[0], pos[1] };
	}

	// Checks to See if Queen is in Attacking Position
	boolean isAttacking(Queen q) {
		if (q.pos[0] == pos[0] || q.pos[1] == pos[1] || Math.abs(q.pos[0] - pos[0]) == Math.abs(q.pos[1] - pos[1]))
			return true;
		return false;
	}
}
