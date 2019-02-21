public class Item implements Comparable<Item> {
	String value;

	//Default Value
	public Item() {
		value= new String();
	}

	// Assigns Value
	public Item(String s) {
		value = s;
	}

	//Compares Two Items
	public int compareTo(Item x) {
		int length = x.value.length() < value.length() ? x.value.length()
				: value.length();
		for (int i = 0; i < length; i++)
			if (value.charAt(0) > x.value.charAt(0))
				return 1;
			else if (value.charAt(0) < x.value.charAt(0))
				return -1;
		return 1;
	}

	//Returns Value of a Character (Ignores Case)
	public int getChar(int index) {
		return value.toLowerCase().charAt(index);
	}

	//Returns String Instead of Memory Address
	@Override
	public String toString() {
		return value;
	}
}
