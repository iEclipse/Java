import java.util.Timer;
import java.util.TimerTask;

public class Character {
	Timer t;
	int hp;
	double maxHP;
	int att;
	int attCD;
	double[] pos;
	double[] spd;
	boolean team;
	boolean cd;

	public Character(int hp, int att, int attCD, double[] spd, boolean isPlayer) {
		t = new Timer();
		this.hp = hp;
		maxHP = hp;
		this.att = att;
		this.attCD = attCD;
		this.spd = spd;
		this.team = isPlayer;
		this.cd = false;
		if (isPlayer) {
			pos = new double[] { 10, 230 };
			this.spd = spd;
		} else {
			pos = new double[] { 545, 230 };
			this.spd[0] = -spd[0];
			this.spd[1] = spd[1];
		}
	}

	public void startCD() {
		t.schedule(new endCD(), attCD);
	}

	public class endCD extends TimerTask {
		@Override
		public void run() {
			cd = false;
		}
	}
}
