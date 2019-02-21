import java.util.Random;


public class Key {
	Random rn = new Random();
	double[] pos = new double[2];
	public Key(){
		pos[0] = rn.nextInt(8);
		pos[1] = 0;
	}
}
