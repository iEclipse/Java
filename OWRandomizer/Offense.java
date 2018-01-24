
public class Offense extends HeroName implements ClassType {

	public Offense(String name) {
		this.name = name;
	}

	@Override
	public String setType() {
		return "Offense";
	}

	@Override
	public String setImage() {
		return "IMG/Offense/";
	}

}
