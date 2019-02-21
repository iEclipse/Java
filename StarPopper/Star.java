import java.util.Random;

public class Star {
	int[] pos; // Position of Star
	int size; // Radius of Star

	// Constructor
	public Star() {
		Random rn = new Random();

		// Randomize Starting Position
		switch (rn.nextInt(4)) {

		// Left
		case 0:
			pos = new int[] { 0, rn.nextInt(460) };
			break;

		// Right
		case 1:
			pos = new int[] { 485, rn.nextInt(460) };
			break;

		// Top
		case 2:
			pos = new int[] { rn.nextInt(485), 0 };
			break;

		// Bottom
		case 3:
			pos = new int[] { rn.nextInt(485), 460 };
			break;
		}
		
		//Starting Star Size
		size = 5;
	}
}
