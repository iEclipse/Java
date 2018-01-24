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
 * Set data structure using lists
 * 
 * @param <V>
 *            List type
 */
public class ListSet<V> implements Set<V> {
	/**
	 * This field represents the {@link set} using a list.
	 */
	List<V> set = new ArrayList<V>();

	/**
	 * This method adds a {@code value} to the {@link set}. The action only
	 * takes place if the {@code value} is not in the set.
	 */
	@Override
	public void add(V value) {
		if (!set.contains(value))
			set.add(value);
	}

	/**
	 * This method removes the specified {@code value} from the set. If it does
	 * not exist, nothing happens.
	 */
	@Override
	public void remove(V value) {
		set.remove(value);
	}

	/**
	 * This method returns true if the {@code value} is found within the set.
	 * Otherwise it returns false.
	 */
	public boolean contains(V value) {
		return set.contains(value);
	}

	/**
	 * This method returns the size of the set.
	 */
	@Override
	public int size() {
		return set.size();
	}

	/**
	 * This method returns true if the set is empty. Otherwise it returns false.
	 */
	@Override
	public boolean isEmpty() {
		return set.isEmpty();
	}

}
