public class AbstractCard {
	private boolean flipped = false;
	private char type;

	public void setFlipped(boolean val) {
		this.flipped = val;
	}
	public boolean getFlipped() {
		return flipped;
	}
	public void setType(char val) {
		this.type = val;
	}
	public char getType() {
		return type;
	}
	public boolean equals(AbstractCard b) {
		if (type == b.getType())
			return true;
		return false;
	}
}
