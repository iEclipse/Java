import java.util.Random;

public class Platform {
	Random rn;
	int[] pos;
	int type;
	int spd;
	boolean direction;
	boolean touched = false;

	public Platform(int height) {
		rn = new Random();
		type = rn.nextInt(20) > 1 ? 0 : 1;
		pos = new int[] { rn.nextInt(400), height };
		direction = rn.nextInt(2) == 0 ? false : true;
		spd = rn.nextInt(5) + 1;
	}

	public void movePlatform() {
		if (!touched) {
			if (direction)
				pos[0] += spd;
			else
				pos[0] -= spd;
		}
		if (pos[0] < -15 || pos[0] > 450)
			direction = !direction;
	}
}
