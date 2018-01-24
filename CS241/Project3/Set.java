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

/**
 * Map data structure interface
 *
 * @param <V>
 *            Value
 */
public interface Set<V> {
	public void add(V value);

	public void remove(V value);

	public boolean contains(V value);

	public int size();

	public boolean isEmpty();
}