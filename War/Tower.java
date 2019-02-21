
public class Tower {
	int hp;
	double maxHP;
	boolean team;
	public Tower(boolean isPlayer, int hp){
		team = isPlayer;
		this.hp = hp;
		maxHP = hp;
	}
}
