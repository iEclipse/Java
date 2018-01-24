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

import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

/**
 * This class contains the methods to test the implementation of sets and maps.
 */
public class Test {
	/**
	 * This method calls the tests.
	 * 
	 * @param args
	 *            Unnecessary input
	 */
	public static void main(String[] args) {
		listSetTest();
		hashSetTest();
		listMapTest();
		hashMapTest();
		lookupTest();
	}

	/**
	 * This method tests the time it takes to run the add operation on a set
	 * using the default HashSet library.
	 */
	public static void hashSetTest() {
		HashSet<Integer> hs = new HashSet<Integer>();
		Random rn = new Random();
		long start = System.currentTimeMillis();
		for (int i = 0; i < 1000000; ++i)
			hs.add(rn.nextInt(1000000));
		long end = System.currentTimeMillis();
		System.out.println("HashSet Add: " + (end - start) + "ms");
		start = System.currentTimeMillis();
	}

	/**
	 * This method tests the time it takes to run the add operation on a set
	 * using lists.
	 */
	public static void listSetTest() {
		ListSet<Integer> ls = new ListSet<Integer>();
		Random rn = new Random();
		long start = System.currentTimeMillis();
		for (int i = 0; i < 1000000; ++i)
			ls.add(rn.nextInt(1000000));
		long end = System.currentTimeMillis();
		System.out.println("ListSet Add: " + (end - start) + "ms");
	}

	/**
	 * This method tests the time it takes to run the add operation on a map
	 * using lists.
	 */
	public static void listMapTest() {
		ListMap<Integer, Object> lm = new ListMap<Integer, Object>();
		long start = System.currentTimeMillis();
		for (int i = 0; i < 1000000; ++i) {
			lm.add(i, null);
		}
		long end = System.currentTimeMillis();
		System.out.println("ListMap Add: " + (end - start) + "ms");
	}

	/**
	 * This method tests the time it takes to run the add operation on a map
	 * using the default HashMpa library.
	 */
	public static void hashMapTest() {
		HashMap<Integer, Object> hm = new HashMap<Integer, Object>();
		long start = System.currentTimeMillis();
		for (int i = 0; i < 1000000; ++i) {
			hm.put(i, null);
		}
		long end = System.currentTimeMillis();
		System.out.println("HashMap Add: " + (end - start) + "ms");
	}

	/**
	 * This method tests the time it takes to run every implementation of set
	 * and map's lookup function.
	 */
	public static void lookupTest() {
		ListSet<Integer> ls = new ListSet<Integer>();
		HashSet<Integer> hs = new HashSet<Integer>();
		ListMap<Integer, Object> lm = new ListMap<Integer, Object>();
		HashMap<Integer, Object> hm = new HashMap<Integer, Object>();
		Random rn = new Random();
		for (int i = 0; i < 1000000; ++i)
			ls.add(rn.nextInt(1000000));
		for (int i = 0; i < 1000000; ++i)
			hs.add(rn.nextInt(1000000));
		for (int i = 0; i < 1000000; ++i)
			lm.add(i, rn.nextInt(1000000));
		for (int i = 0; i < 1000000; ++i)
			hm.put(i, rn.nextInt(1000000));
		long start = System.currentTimeMillis();
		for (int i = 0; i < 1000000; ++i)
			ls.contains(i);
		long end = System.currentTimeMillis();
		System.out.println("ListSet Lookup: " + (end - start) + "ms");
		start = System.currentTimeMillis();
		for (int i = 0; i < 1000000; ++i)
			hs.contains(i);
		end = System.currentTimeMillis();
		System.out.println("HashSet Lookup: " + (end - start) + "ms");
		start = System.currentTimeMillis();
		for (int i = 0; i < 1000000; ++i)
			lm.get(i);
		end = System.currentTimeMillis();
		System.out.println("ListMap Lookup: " + (end - start) + "ms");
		start = System.currentTimeMillis();
		for (int i = 0; i < 1000000; ++i)
			hm.get(i);
		end = System.currentTimeMillis();
		System.out.println("HashMap Lookup: " + (end - start) + "ms");
	}
}
