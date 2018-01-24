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
 * This class represents the connections between locations.
 * 
 * @author Andrew Trang
 *
 */
class Instruction {
	/**
	 * This field represents a related location
	 */
	Location location;
	/**
	 * This field represents the direction to reach the related location.
	 */
	Direction direction;
	/**
	 * This field represents the distance between the current location and the
	 * related location.
	 */
	int distance;

	/**
	 * Constructor to create an instruction for a location.
	 * 
	 * @param location
	 *            Related location
	 * @param direction
	 *            Direction of the related location
	 * @param distance
	 *            Distance between the current location and the related location
	 */
	Instruction(Location location, Direction direction, int distance) {
		this.location = location;
		this.direction = direction;
		this.distance = distance;
	}
}