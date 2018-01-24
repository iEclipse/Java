/**
 * CS 241: Data Structures and Algorithms II
 * Professor: Edwin Rodríguez
 *
 * Programming Assignment #1
 *
 * Description: Simply implement the given List interface in Java as an Array List.
 *
 * Author: Andrew Trang
 */

package cpp.cs.cs241.prog_assgnmnt_1;

/**
 * This class implements the List interface to create an ArrayList data
 * structure.
 */
public class ArrayList<K extends Comparable<K>, V> implements List<K, V> {

	/**
	 * This field contains the elements in the arraylist.
	 */
	Object[] elements;

	/**
	 * This field represents the size of the arraylist.
	 */
	int size;

	/**
	 * The default constructor for the {@link ArrayList} class. Initially, the
	 * {@link #elements} array is empty with {@link #size} set to 0.
	 */
	public ArrayList() {
		elements = new Object[0];
		size = 0;
	}

	/**
	 * Constructor that passes {@link #size} as an argument.
	 * 
	 * @param size
	 *            - desired arraylist size
	 */
	public ArrayList(int size) {
		elements = new Object[size];
		this.size = size;
	}

	/**
	 * Resizes the arraylist and copies the data from the old arraylist to the
	 * new one.
	 */
	private void resize() {
		Object[] temp;
		if (elements.length == 0)
			temp = new Object[1];
		else
			temp = new Object[elements.length * 2];
		for (int i = 0; i < elements.length; i++)
			temp[i] = elements[i];
		elements = temp;
	}

	/**
	 * Adds an element into the arraylist given a key (data), and a value
	 * (index).
	 * 
	 * @param key
	 *            - the object being stored
	 * @param value
	 *            - the desired index of the array
	 */
	public boolean add(K key, V value) {
		try {
			if (size == elements.length)
				resize();
			elements[(Integer) value] = key;
			size++;
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Removes an element from the arraylist. Returns null if key is not found
	 * or an exception is thrown.
	 * 
	 * @param key
	 *            - the desired object to remove
	 */
	@SuppressWarnings("unchecked")
	public V remove(K key) {
		Object value;
		try {
			for (int i = 0; i < elements.length; i++)
				if (elements[i] == key) {
					value = elements[i];
					for (int j = i; i < elements.length - 1; i++)
						elements[j] = elements[j + 1];
					size--;
					return (V) value;
				}
			return null;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Removes an element from the arraylist. Returns null if the specified
	 * index is not valid.
	 * 
	 * @param n
	 *            - the desired index of the array
	 */
	@SuppressWarnings("unchecked")
	public V remove(int n) {
		Object value;
		try {
			value = elements[n];
			for (int i = n; i < elements.length - 1; i++)
				elements[i] = elements[i + 1];
			size--;
			return (V) value;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Removes the last element from the arraylist.
	 */
	public V remove() {
		return remove(elements.length - 1);
	}

	/**
	 * Looks for an element in the array given a search value. Returns null if
	 * the element is not found.
	 * 
	 * @param key
	 *            - element to search for in the arraylist
	 */
	@SuppressWarnings("unchecked")
	public V lookup(K key) {
		try {
			for (int i = 0; i < elements.length; i++)
				if (elements[i] == key)
					return (V) elements[i];
			return null;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Returns the size of the arraylist.
	 */
	public int size() {
		return size;
	}

	/**
	 * Gets the element in the arraylist given an index.
	 * 
	 * @param n
	 *            - the desired index of the arraylist
	 */
	@SuppressWarnings("unchecked")
	public V get(int n) {
		try {
			return (V) elements[n];
		} catch (Exception e) {
			return null;
		}
	}
}
