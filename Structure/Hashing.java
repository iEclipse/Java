//Hashing Methods
public class Hashing {
	
	// First Hash Function
	public int hashInt(int value) {
		return (2 * value + 4) % 13;
	}
	// Second Hash Function
	public int doubleHashInt(int value) {
		return 11 - (value % 11);
	}
	// Test Method
	public static void main(String[] args) {
		Hashing h = new Hashing();
		int[] hashTable = new int[13];
		int[] array = {11, 12, 26, 23, 1, 37, 40, 4, 8, 14, 16};
		int hash;
		for (int i = 0; i < array.length; i++) {
			boolean hashed = false;
			hash = h.hashInt(array[i]);
			if (hashTable[hash] == 0) {
				hashed = !hashed;
				hashTable[hash] = array[i];
			}
			while (!hashed) {
				hash = (hash + h.doubleHashInt(array[i])) % hashTable.length;
				if (hashTable[hash] == 0) {
					hashed = !hashed;
					hashTable[hash] = array[i];
				}
			}
		}
		System.out.println("Values to be Hashed\n=======================================================");
		for (int i : array)
			if (i == 0)
				System.out.print("[  ]");
			else
				System.out.print(i + "   ");

		System.out.println("\n\nHashTable\n=======================================================");
		for (int i : hashTable)
			if (i == 0)
				System.out.print("[  ]");
			else
				System.out.printf("[%2d]", i);
	}
}
