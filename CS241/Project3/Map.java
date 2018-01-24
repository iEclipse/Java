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
 * @param <K>
 *            Key
 * @param <V>
 *            Value
 */
public interface Map<K, V> {
	public void add(K key, V value);

	public V remove(K key);

	public V get(K key);

	public int size();

	public boolean isEmpty();
}