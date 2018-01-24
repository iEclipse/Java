//Performs all hashing related functions
public class HashCode {
	int size = 37;
	LinkedList[] array = new LinkedList[size];
	int collisions = 0;
	int names;
	int index;
	// Initializes each linked list in the array
	public HashCode() {
		for (int i = 0; i < size; i++)
			array[i] = new LinkedList();
	}
	// Uses Polynomial hashing method
	public int hashCode(String key) {
		int hash = 0;
		for (int i = 0; i < key.length(); i++)
			hash = 7 * hash + key.charAt(i);
		return hash;
	}
	// Double hashing
	public int hashCode2(String key) {
		int temp = 7 - (hashCode(key) % 7);
		return (index + temp) % size;
	}
	// Calls the hashCode method for each key and stores the data
	public void store(String name, double value) {
		index = Math.abs((hashCode(name)) % size);
		while (array[index].name != null && !array[index].name.equals(name)) {
			collisions++;
			index = hashCode2(name);
		}
		if (array[index].name == null) {
			array[index].name = name;
			array[index].addElement(value);
			names++;
		}
		else if (array[index].name.equals(name))
			array[index].addElement(value);
	}
}
