/*
 * Project 2
 * ===========
 * 
 * Singly Linked List w/Dummy Head
 * Includes: Nested Node class,
 * 			 Hard-coded test cases for each method
 */

public class Project2 {
	// Tests each individual case through the testLoop method below
	static int test = 1;
	public static void main(String[] args) {
		case1();
		case2();
		case3();
		case4();
		case5();
	}
	// Case 1: A and B are equal but distinct sets, for example, A = {1, 2, 3}, B = {2, 1, 3}
	public static void case1() {
		LinkedList test1 = new LinkedList();
		LinkedList test2 = new LinkedList();
		test1.addElement(1);
		test1.addElement(2);
		test1.addElement(3);
		test2.addElement(2);
		test2.addElement(1);
		test2.addElement(3);
		testLoop(test1, test2);
	}
	// Case 2: A and B are such that they have different sizes but one is a subset of the other, for example, A = {1}, B = {1, 2}
	public static void case2() {
		LinkedList test1 = new LinkedList();
		LinkedList test2 = new LinkedList();
		test1.addElement(1);
		test2.addElement(1);
		test2.addElement(2);
		testLoop(test1, test2);
	}
	// Case 3: A and B are non-empty and different in size but have common elements, for example, A = {1, 2, 3}, B = {2, 3, 4, 5}
	public static void case3() {
		LinkedList test1 = new LinkedList();
		LinkedList test2 = new LinkedList();
		test1.addElement(1);
		test1.addElement(2);
		test1.addElement(3);
		test2.addElement(2);
		test2.addElement(3);
		test2.addElement(4);
		test2.addElement(5);
		testLoop(test1, test2);
	}
	// Case 4: they are non-empty with nothing in common, for example, A = {1}, B = {2, 3}
	public static void case4() {
		LinkedList test1 = new LinkedList();
		LinkedList test2 = new LinkedList();
		test1.addElement(1);
		test2.addElement(2);
		test2.addElement(3);
		testLoop(test1, test2);
	}
	// Case 5: one is non-empty and the other empty
	public static void case5() {
		LinkedList test1 = new LinkedList();
		LinkedList test2 = new LinkedList();
		test2.addElement(1);
		testLoop(test1, test2);
	}
	// Tests all methods of the LinkedList class
	// Although the add method is unnecessary
	// due to it being used in the methods above,
	// this method displays the truth value.
	public static void testLoop(LinkedList test1, LinkedList test2) {
		System.out.println("CASE " + test + "\n================");
		System.out.println("L1 Contents: " + test1.toString());
		System.out.println("L2 Contents: " + test2.toString());
		System.out.println("L2 Contains '1': " + test2.contain(1));
		System.out.println("L1 Remove '1': " + test1.remove(1));
		System.out.println("L1 Remove '1': " + test1.remove(1));
		System.out.println("L1 Add '1': " + test1.addElement(1));
		System.out.println("L1 Add '1': " + test1.addElement(1));
		test1.remove(1); // Reverts the changes done from the addElement method
		System.out.println("L1 Size: " + test1.size() + ", " + test1.toString());
		System.out.println("Subset: " + test1.subsetOf(test2));
		System.out.println("Equal: " + test1.isEqual(test2));
		System.out.println("Union: " + test1.union(test2));
		System.out.println("Intersection: " + test1.intersection(test2));
		System.out.println("Complement: " + test1.complement(test2) + "\n");
		test++;
	}
}