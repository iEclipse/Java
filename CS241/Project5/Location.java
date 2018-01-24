package cpp.cs.cs241.prog_assgmnt_5;

import java.util.ArrayList;

/**
 * CS 241: Data Structures and Algorithms II
 * Professor: Edwin Rodríguez
 *
 * Programming Assignment #5
 *
 * Description: GPS/Graphs/Maps
 *
 */

/**
 * This enum represents the possible locations for the program.
 * 
 * @author Andrew Trang
 *
 */
public enum Location {
	LIBRARY(0), STADIUM(1), VINEWOOD(2), FORUM(3), CITY_HALL(4), THEATRE(5), HOSPITAL(6), COUNTRY_CLUB(7), BURGER_SHOT(
			8), GYM(9), CLUCKIN_BELL(10), PIMIENTOS(11);

	/**
	 * This field represents the connections for a location.
	 */
	ArrayList<Instruction> dir = new ArrayList<Instruction>();

	/**
	 * This field represents the value of a location.
	 */
	private final int value;

	/**
	 * This method is an initializing constructor for the enum.
	 * 
	 * @param value
	 *            Value of a location
	 */
	private Location(int value) {
		this.value = value;
	}

	/**
	 * This method obtains the value of the location.
	 * 
	 * @return Value of the location
	 */
	public int get() {
		return value;
	}
}