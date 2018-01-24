
public class Edge implements Comparable<Edge> {
	int ymin;
	int ymax;
	float xval;
	double slope;

	@Override
	public int compareTo(Edge e) {
		if (e.xval > xval)
			return -1;
		else if (e.xval < xval)
			return 1;
		else
			return 0;
	}
}
