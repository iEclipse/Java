package cpp.cs.cs241.prog_assgmnt_4;

/**
 * CS 241: Data Structures and Algorithms II
 * Professor: Edwin Rodríguez
 *
 * Programming Assignment #4
 *
 * Red Black Tree
 *
 */

public interface Tree<K extends Comparable<K>, V> {
	  public void add(K key, V value);
	  public V remove(K key);
	  public V lookup(K key);
	  public String toPrettyString();
	}