
public class Node {
	State cur;
	Node parent;
	int cost;
	int h;
	int f;

	public Node(State s) { // Node
		cur = s;
		parent = null;
		cost = 0;
		h = 0;
		f = 0;
	}

	public Node(Node prev, State s, int c, int h) {
		parent = prev;
		cur = s;
		cost = c;
		this.h = h;
		this.f = cost + h;
	}
}
