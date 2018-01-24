package cpp.cs.cs241.prog_assgmnt_5;

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
 * This enum represents the directions for an instruction.
 * 
 * @author Andrew Trang
 *
 */
public enum Direction {
	NORTH(0), SOUTH(1), EAST(2), WEST(3), NORTHWEST(4), NORTHEAST(5), SOUTHWEST(6), SOUTHEAST(7);

	/**
	 * This field represents the value of a direction.
	 */
	private final int value;

	/**
	 * This method is an initializing constructor for the enum.
	 * 
	 * @param value
	 */
	private Direction(int value) {
		this.value = value;
	}

	/**
	 * This method returns the value of a direction.
	 * 
	 * @return Value of direction.
	 */
	public int get() {
		return value;
	}
}
