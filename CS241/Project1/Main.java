
/**
 * CS 241: Data Structures and Algorithms II
 * Professor: Edwin Rodríguez
 *
 * Programming Assignment #2
 *
 * Description: Implement a heap from the heap interface & create a table-sasignment application.
 *
 * @Author: Andrew Trang
 */
package cpp.cs.cs241.prog_assgmnt_1;

import java.util.Scanner;

/**
 * This class is the center of the restaurant seating application. All
 * interactions are handled by this class.
 */
public class Main {
	/**
	 * This field collects input from the user.
	 */
	static Scanner sc = new Scanner(System.in);
	/**
	 * This field creates a heap of the Customer type.
	 */
	static NodeHeap<Customer> heap = new NodeHeap<Customer>("min");
	/**
	 * This field represents the status of the program.
	 */
	static boolean running = true;

	/**
	 * Main method of the application. Allows user to interact with the program.
	 * 
	 * @param args
	 *            Not required/implemented
	 */
	public static void main(String[] args) {
		System.out.println("Restaurant Seating Software" + "\n================================"
				+ "\n(Type '?' for a list of commands | 'x' to quit)");
		while (running) {
			System.out.print("\nEnter a command: ");
			commandProcess(sc.nextLine());
		}
	}

	/**
	 * Gets user input and performs the user-defined action.
	 * 
	 * @param text
	 *            User input
	 */
	public static void commandProcess(String text) {
		String[] input = text.split(" ");
		input[0].toLowerCase();
		if (input.length == 3)
			input[2].toLowerCase();
		switch (input[0]) {
		case "?":
			System.out.println("\n\nCommand List\n-------------------------------------------"
					+ "\nNote: Omit the brackets when entering a command."
					+ "\n\n[Next] - Lists the name of the next party in line."
					+ "\n\n[Seat] [Name] [Status] - Adds a party to the waitlist."
					+ "\n(You do not need to enter a status. Default: Rank 7)" + "\n\nStatus Types\n------------------"
					+ "\n   [VIP] - Highest priority seating." + "\n   [ADV] - Customers who called in advance."
					+ "\n   [SNR] - Senior citizens." + "\n   [VET] - Veterans of the army."
					+ "\n   [GRP] - Large groups." + "\n   [KID] - Parties with children."
					+ "\n   [N/A] - Default priority.\n");
			break;
		case "x":
			running = false;
		case "next":
			try {
				System.out.println("System: " + heap.remove().name + "'s party is ready to be seated.");
			} catch (NullPointerException e) {
				System.out.println("System: " + "No one is in line.");
			}
			break;
		case "seat":
			if (input.length < 2)
				System.out.println("System: " + "You did not specify a name.");
			else if (input.length == 2) {
				heap.add(new Customer(input[1], "default"));
				System.out.println("System: Successfully added to the waitlist.");
			} else if (input.length == 3) {
				heap.add(new Customer(input[1], input[2]));
				System.out.println("System: Successfully added to the waitlist.");
			} else
				System.out.println("System: " + "Invalid argument passed.");
			break;
		default:
			System.out.println("System: '" + input[0] + "'" + " is not a valid command.");
		}
	}
}
