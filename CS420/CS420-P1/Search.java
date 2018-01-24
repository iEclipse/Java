import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Search {
	void search(int[] board, int h) {
		try {
			Queue<Node> q = new LinkedList<Node>();
			q.add(new Node(new State(board))); // Adds Root
			boolean debug = false;

			int searchCost = 1;
			long start = System.nanoTime();

			while (!q.isEmpty()) {
				Node temp = q.poll();

				if (!temp.cur.isGoal()) {
					ArrayList<State> testNeighbors = temp.cur.getNeighbors();
					ArrayList<Node> neighbors = new ArrayList<Node>();

					for (int i = 0; i < testNeighbors.size(); i++) {
						Node visited;
						if (h == 0) // Out of Place
							visited = new Node(temp, testNeighbors.get(i), temp.cost + 1, testNeighbors.get(i).h1);
						else // Manhattan
							visited = new Node(temp, testNeighbors.get(i), temp.cost + 1, testNeighbors.get(i).h2);

						if (!repeating(visited)) { // Add visited Nodes
							neighbors.add(visited);
						}
					}

					if (neighbors.isEmpty())
						continue;

					Node lowN = neighbors.get(0);

					for (int i = 0; i < neighbors.size(); i++)
						if (lowN.f > neighbors.get(i).f)
							lowN = neighbors.get(i);

					int lowV = lowN.f;

					for (int i = 0; i < neighbors.size(); i++) { // Search more
						if (neighbors.get(i).f == lowV) {
							q.add(neighbors.get(i));
						}
					}
					searchCost++;

				} else {
					Stack<Node> path = new Stack<Node>(); // Path of Solution
					path.push(temp);
					temp = temp.parent;

					while (temp.parent != null) {
						path.push(temp);
						temp = temp.parent;
					}
					path.push(temp);

					int size = path.size();
					for (int i = 0; i < size; i++) {
						temp = path.pop();
						if (debug == true) {
							System.out.println("Step " + i + ":");
							temp.cur.print();
						}
					}

					System.out.println("Cost: " + temp.cost);
					System.out.println("Nodes Checked: " + searchCost);
					System.out.println("Elapsed Time: " + (System.nanoTime() - start) + "ns");
					return;
				}
			}
		} catch (Error e) {
			System.out.println("Out of Memory.");
		}
	}

	boolean repeating(Node n) { // Check for Repeating Nodes
		Node temp = n;
		while (n.parent != null) {
			if (n.parent.cur.equals(temp.cur)) {
				return true;
			}
			n = n.parent;
		}
		return false;
	}
}
