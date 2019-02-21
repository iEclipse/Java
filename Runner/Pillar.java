import java.util.ArrayList;
import java.util.Random;

// Setting for Pillars
public class Pillar {
	// List of All pillars
	static ArrayList<int[]> pillars = new ArrayList<int[]>();
	Random rn = new Random();

	// Generates a New Pillar
	public void genPillar() {
		pillars.add(new int[] { 500, rn.nextInt(300) + 150, 100 });
	}
}
