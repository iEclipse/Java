//Test Sequence of Linked Lists

public class Main {
	public static void main(String[] args) {

		// Creates one of each type of Linked LIst
		SNH snh = new SNH();
		SH sh = new SH();
		DNH dnh = new DNH();
		DH dh = new DH();

		// Tests the add method for integers
		snh.add(0);
		sh.add(1);
		dnh.add(2);
		dh.add(3);

		// Tests the add method for Strings
		snh.add("a");
		sh.add("b");
		dnh.add("c");
		dh.add("d");

		// Tests the add method for floats
		snh.add(9.0);
		sh.add(10.0);
		dnh.add(11.0);
		dh.add(12.0);

		// Prints out the set
		System.out.println(snh.toString());
		System.out.println(sh.toString());
		System.out.println(dnh.toString());
		System.out.println(dh.toString());

		// Removes the first item in the set
		snh.remove(0);
		sh.remove(1);
		dnh.remove(2);
		dh.remove(3);

		// Prints the resulting set
		System.out.println("\n" + snh.toString());
		System.out.println(sh.toString());
		System.out.println(dnh.toString());
		System.out.println(dh.toString());
	}
}
