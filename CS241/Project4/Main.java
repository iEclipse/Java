package cpp.cs.cs241.prog_assgmnt_4;

/**
 * CS 241: Data Structures and Algorithms II
 * Professor: Edwin Rodríguez
 *
 * Programming Assignment #5
 *
 * Red Black Tree
 *
 * @author Andrew Trang
 */

public class Main {

	/**
	 * This method tests the RBTree.
	 * 
	 * @param args
	 *            = Unused Arguments
	 */
	public static void main(String[] args) {
		/**
		 * This field instantiates the RBTree
		 */
		RBTree<Integer, Integer> r = new RBTree<Integer, Integer>();

		System.out.println("Remove: " + r.remove(38) + "\n");

		System.out.println(r.toPrettyString() + "\n");

		System.out.println("Remove: " + r.remove(5) + "\n");

		r.add(5, 5);
		System.out.println("Remove: " + r.remove(5) + "\n");
		r.add(4, 4);
		r.add(6, 6);
		System.out.println("Remove: " + r.remove(4) + "\n");
		r.add(10, 10);
		System.out.println("Remove: " + r.remove(6) + "\n");
		r.add(20, 20);
		System.out.println("Remove: " + r.remove(10) + "\n");
		r.add(30, 30);
		r.add(32, 32);
		r.add(35, 35);
		r.add(38, 38);
		r.add(41, 41);

		System.out.println("Remove: " + r.remove(38) + "\n");
		System.out.println("Remove: " + r.remove(41) + "\n");
		System.out.println("Remove: " + r.remove(35) + "\n");

		System.out.println("Add: 5, 4, 6, 10, 20, 30, 32, 35, 38, 41\n");
		r.add(5, 5);
		r.add(4, 4);
		r.add(6, 6);
		r.add(10, 10);
		r.add(20, 20);
		r.add(30, 30);
		r.add(32, 32);
		r.add(35, 35);
		r.add(38, 38);
		r.add(41, 41);

		System.out.println(r.toPrettyString() + "\n");

		System.out.println("Lookup:");
		System.out.println(r.lookup(5));
		System.out.println(r.lookup(4));
		System.out.println(r.lookup(6));
		System.out.println(r.lookup(10));
		System.out.println(r.lookup(20));
		System.out.println(r.lookup(30));
		System.out.println(r.lookup(32));
		System.out.println(r.lookup(35));
		System.out.println(r.lookup(38));
		System.out.println(r.lookup(41) + "\n");
	}
}
