
public class Support extends HeroName implements ClassType {

	public Support(String name) {
		this.name = name;
	}

	@Override
	public String setType() {
		return "Support";
	}

	@Override
	public String setImage() {
		return "IMG/Support/";
	}

}
