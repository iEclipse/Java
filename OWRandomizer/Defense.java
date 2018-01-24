
public class Defense extends HeroName implements ClassType {

	public Defense(String name) {
		this.name = name;
	}

	@Override
	public String setType() {
		return "Defense";
	}

	@Override
	public String setImage() {
		return "IMG/Defense/";
	}

}
