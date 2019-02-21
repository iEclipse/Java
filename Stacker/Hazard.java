import java.util.Random;

public class Hazard {
	Random rn = new Random();
	int[] size;
	int[] pos;
	int[] vel;

	public Hazard(int camera) {
		size = new int[] { 10, 10 };
		vel = new int[] { 0, 0 };
		switch (rn.nextInt(3)) {
		case 0:
			pos = new int[] { 0, camera - 260 - rn.nextInt(200) };
			break;
		case 1:
			pos = new int[] { rn.nextInt(480), camera - 460 };
			break;
		case 2:
			pos = new int[] { 480, camera - 260 - rn.nextInt(200) };
			break;
		}
	}

	public void moveHazard(int[] playerPos, int[] playerSize) {
		if (pos[0] < playerPos[0] && vel[0] < 7) {
			vel[0]++;
		} else if (pos[0] > playerPos[0] && vel[0] > -7) {
			vel[0]--;
		}
		if (pos[1] < playerPos[1] - playerSize[1] && vel[1] < 7 || vel[1] == 0 && pos[1] > playerPos[1] - playerSize[1]) {
			vel[1]++;
		}
		pos[0] += vel[0];
		pos[1] += vel[1];
	}
}
