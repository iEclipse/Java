/**
 * CS 241: Data Structures and Algorithms II
 * Professor: Edwin Rodríguez
 *
 * Programming Assignment #3
 *
 * Sets and Maps
 *
 * @author Andrew Trang
 */

package cpp.cs.cs241.prog_assgmnt_3;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a Map data structure using Lists.
 *
 * @param <K>
 *            The key of the {@link Map}.
 * @param <V>
 *            The value of the {@link Map}.
 */
public class ListMap<K, V> implements Map<K, V> {
	/**
	 * This field contains the keys of the Map.
	 */
	List<Data> map = new ArrayList<Data>();

	/**
	 * This method adds a value to the map using the specified key. Duplicate
	 * keys will not be saved. Any values mapped using the same key will replace
	 * the old value.
	 * 
	 * @param key
	 *            The key to search.
	 * @param value
	 *            The value to store.
	 */
	@Override
	public void add(K key, V value) {
		for (int i = 0; i < map.size(); ++i)
			if (key == map.get(i).key) {
				if (value != map.get(i).value)
					map.get(i).value = value;
				return;
			}
		map.add(new Data(key, value));
	}

	/**
	 * This method removes a value from the map using the specified key. If
	 * found, it will return the value associated with the key. Otherwise it
	 * returns null.
	 * 
	 * @param key
	 *            The key to search.
	 */
	@Override
	public V remove(K key) {
		for (int i = 0; i < map.size(); ++i)
			if (key == map.get(i).key)
				return map.remove(i).value;
		return null;
	}

	/**
	 * This method returns the value associated with the provided key. Returns
	 * null if not found.
	 * 
	 * @param key
	 *            The key to search.
	 */
	@Override
	public V get(K key) {
		for (int i = 0; i < map.size(); ++i)
			if (key == map.get(i).key)
				return map.get(i).value;
		return null;
	}

	/**
	 * This method returns the size of the map.
	 */
	@Override
	public int size() {
		return map.size();
	}

	/**
	 * This method returns if the map is empty or not.
	 */
	@Override
	public boolean isEmpty() {
		return map.isEmpty(); // Return if map is empty
	}

	/**
	 * This class represents the object being stored. It contains a key and a
	 * value pair used in the {@link Map} class.
	 */
	class Data {
		K key;
		V value;

		Data(K key, V value) {
			this.key = key;
			this.value = value;
		}
	}
}
