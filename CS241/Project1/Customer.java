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

/**
 * This class represents the customer or party of the restaurant.
 */
public class Customer implements Comparable<Customer> {
	/**
	 * The name of the party
	 */
	String name;
	/**
	 * The status of the party
	 */
	String status;

	/**
	 * Constructor for the Customer class.
	 * 
	 * @param name
	 *            Name of the party
	 * @param status
	 *            Status of the party
	 */
	public Customer(String name, String status) {
		this.name = name;
		this.status = status;
	}

	/**
	 * Returns the priority of the party.
	 * @return Integer value of the priority
	 */
	public int getPriority() {
		switch (status) {
		case "vip":
			return 0;
		case "adv":
			return 1;
		case "snr":
			return 2;
		case "vet":
			return 3;
		case "grp":
			return 4;
		case "kid":
			return 5;
		default:
			return 6;
		}
	}

	/**
	 * Compares two customers.
	 */
	@Override
	public int compareTo(Customer x) {
		if (getPriority() > x.getPriority())
			return 1;
		return 0;
	}
}
