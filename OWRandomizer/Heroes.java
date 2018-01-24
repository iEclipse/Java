import java.io.File;
import java.util.ArrayList;

public class Heroes {
	ArrayList<ClassType> heroList = new ArrayList<>();
	ArrayList<File> images = new ArrayList<>();
	int offSize;
	int defSize;
	int tankSize;
	int supSize;

	public Heroes() {
		heroList.addAll(getOffenseHeroes());
		heroList.addAll(getDefenseHeroes());
		heroList.addAll(getTankHeroes());
		heroList.addAll(getSupportHeroes());
		System.out.println("Locating Files\n===============");
		for (int i = 0; i < heroList.size(); i++) {
			String path = heroList.get(i).setImage() + ((HeroName) heroList.get(i)).name + ".png";
			images.add(new File(path));
			System.out.println("File: " + path + (images.get(images.size() - 1).exists() ? " - Exists" : " - Unavailable"));
		}
	}

	public ArrayList<ClassType> getOffenseHeroes() {
		ArrayList<ClassType> offense = new ArrayList<>();
		offense.add(new Offense("Doomfist"));
		offense.add(new Offense("Genji"));
		offense.add(new Offense("McCree"));
		offense.add(new Offense("Pharah"));
		offense.add(new Offense("Reaper"));
		offense.add(new Offense("Soldier76"));
		offense.add(new Offense("Sombra"));
		offense.add(new Offense("Tracer"));
		offSize = offense.size();
		return offense;
	}

	public ArrayList<ClassType> getDefenseHeroes() {
		ArrayList<ClassType> defense = new ArrayList<>();
		defense.add(new Defense("Bastion"));
		defense.add(new Defense("Hanzo"));
		defense.add(new Defense("Junkrat"));
		defense.add(new Defense("Mei"));
		defense.add(new Defense("Torbjörn"));
		defense.add(new Defense("Widowmaker"));
		defSize = defense.size();
		return defense;
	}

	public ArrayList<ClassType> getTankHeroes() {
		ArrayList<ClassType> tank = new ArrayList<>();
		tank.add(new Tank("DVa"));
		tank.add(new Tank("Orisa"));
		tank.add(new Tank("Reinhardt"));
		tank.add(new Tank("Roadhog"));
		tank.add(new Tank("Winston"));
		tank.add(new Tank("Zarya"));
		tankSize = tank.size();
		return tank;
	}

	public ArrayList<ClassType> getSupportHeroes() {
		ArrayList<ClassType> support = new ArrayList<>();
		support.add(new Support("Ana"));
		support.add(new Support("Lúcio"));
		support.add(new Support("Mercy"));
		support.add(new Support("Moira"));
		support.add(new Support("Symmetra"));
		support.add(new Support("Zenyatta"));
		supSize = support.size();
		return support;
	}
}
