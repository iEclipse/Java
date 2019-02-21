import java.util.PriorityQueue;

public class Main {

	public static void main(String[] args) {
		PriorityQueue<Item> test = new PriorityQueue<Item>();

		// Adds Items to Priority Queue
		test.add(new Item("B"));
		test.add(new Item("A"));
		test.add(new Item("Bb"));
		test.add(new Item("Aa"));

		// Prints Priority Queue Values
		System.out.println(test);

		// Removes All Values to Demonstrate Actual Queue Order
		for (int i = 0, j = test.size(); i < j; i++)
			System.out.print(test.remove() + " ");
	}
}
