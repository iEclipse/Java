public class Player {
	int[] size, pos;
	double[] vel;

	public Player() {
		size = new int[] { 20, 20 };
		pos = new int[] { 240- size[0]/2, 460 };
		vel = new double[] { 0, 0 };
	}
}
