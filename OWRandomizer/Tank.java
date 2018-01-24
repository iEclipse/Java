
public class Tank extends HeroName implements ClassType {

	public Tank(String name) {
		this.name = name;
	}

	@Override
	public String setType() {
		return "Tank";
	}

	@Override
	public String setImage() {
		return "IMG/Tank/";
	}

}
