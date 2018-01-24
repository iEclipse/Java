package cpp.cs.cs241.prog_assgmnt_5;

import java.util.ArrayList;
import java.util.HashMap;

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
 * This class contains nearly all of the dialogue used in the program.
 * 
 * @author Andrew Trang
 *
 */
public class Dialogue {

	/**
	 * This field represents the names of each cross street.
	 */
	private String[] crossStreet = { "", "" };
	/**
	 * This field represents the direction of the last instruction.
	 */
	private Direction prev;
	/**
	 * This field represents the total distance of similar adjacent directions.
	 */
	private int distance = 0;

	/**
	 * This method prints the route taken in terms of location.
	 * 
	 * @param route
	 *            Obtained from using the path algorithm
	 */
	public void getRoute(ArrayList<Location> route) {
		String path = "\nRouting Path: \n";
		path += "--------------------------------------\n";

		for (int i = route.size() - 1; i >= 0; --i)
			path += route.get(i) + " > ";
		path = path.substring(0, path.length() - 3);
		System.out.println(path);
	}

	/**
	 * This method allows for printing the directions used for navigation.
	 * 
	 * @param route
	 *            Obtained from using the path algorithm
	 * @param start
	 *            The starting location
	 * @param destination
	 *            The desired destination
	 * @return List of directions
	 */
	public String getDirections(ArrayList<Location> route, Location start, Location destination) {
		String path = "--------------------------------------\n";
		path += "\nStarting Location: " + getLocation(start) + ".\n";
		Location current, next;

		for (int i = route.size() - 1; i >= 0; --i) {
			current = route.get(i);
			if (i != 0)
				next = route.get(i - 1);
			else
				next = null;
			path += (next(current, next));
		}
		path += ("Arrive at: " + getLocation(destination) + ".\n");
		path += "\n--------------------------------------";
		return path;
	}

	/**
	 * This is a helper method for the getDirections method.
	 * 
	 * @param before
	 *            Previous location
	 * @param after
	 *            Next location
	 * @return Current line of instruction
	 */
	private String next(Location before, Location after) {
		String text = "";

		if (crossStreet[0] == null) {
			getStreet(before);
		}

		for (int i = 0; i < before.dir.size(); ++i) {
			Instruction next = before.dir.get(i);
			if (next.location == after) {
				if (prev == next.direction)
					distance += before.dir.get(i).distance;
				else if (distance > 0) {
					text += "Continue " + getTurn(prev) + " for " + distance + " mile(s)." + "\n";
					distance = 0;
				}
				if (prev == null)
					text += "Begin driving " + getTurn(next.direction) + " for " + next.distance + " mile(s).\n";
				else if (prev != next.direction) {
					text += "Turn " + getTurn(next.direction) + " on " + getStreet(after) + "\n";
					text += "Drive " + getTurn(next.direction) + " for " + next.distance + " mile(s)." + "\n";
				}
				prev = next.direction;
			}
		}
		distance = 0;
		return text;
	}

	/**
	 * This method converts the enum to readable text.
	 * 
	 * @param location
	 *            Desired location
	 * @return Full location name
	 */
	private String getLocation(Location location) {
		switch (location) {
		case BURGER_SHOT:
			return "BurgerShot";
		case CITY_HALL:
			return "Los Santos City Hall";
		case CLUCKIN_BELL:
			return "Cluckin'Bell";
		case COUNTRY_CLUB:
			return "Richman Country Club";
		case FORUM:
			return "Los Santos Forum";
		case GYM:
			return "Los Santos Gym";
		case HOSPITAL:
			return "All Saints General Hospital";
		case LIBRARY:
			return "Los Santos Public Library";
		case PIMIENTOS:
			return "Pimiento's";
		case STADIUM:
			return "Los Santos Saints' Stadium";
		case THEATRE:
			return "Centennial Theatre";
		case VINEWOOD:
			return "Vinewood Boulevard";
		default:
			return null;
		}
	}

	/**
	 * THis method obtains the cross streets of a location.
	 * 
	 * @param location
	 *            Desired location
	 * @return Cross streets
	 */
	private String getStreet(Location location) {
		switch (location) {
		case BURGER_SHOT:
			return checkStreet("2nd St.", "General Ave.");
		case CITY_HALL:
			return checkStreet("1st St.", "Centennial Ave.");
		case CLUCKIN_BELL:
			return checkStreet("4th St.", "General Ave.");
		case COUNTRY_CLUB:
			return checkStreet("4th St.", "Centennial Ave.");
		case FORUM:
			return checkStreet("4th St.", "Main Ave.");
		case GYM:
			return checkStreet("3rd St.", "General Ave.");
		case HOSPITAL:
			return checkStreet("3rd St.", "Centennial Ave.");
		case LIBRARY:
			return checkStreet("1st St.", "Main Ave.");
		case PIMIENTOS:
			return checkStreet("4th St.", "Food Alley.");
		case STADIUM:
			return checkStreet("2nd St.", "Main Ave.");
		case THEATRE:
			return checkStreet("2nd St.", "Centennial Ave.");
		case VINEWOOD:
			return checkStreet("3rd St.", "Main Ave.");
		default:
			return null;
		}
	}

	/**
	 * This method checks for a difference in cross streets
	 * 
	 * @param x
	 *            First street
	 * @param y
	 *            Second street
	 * @return The cross street that is different
	 */
	private String checkStreet(String x, String y) {
		String street = crossStreet[0].equals(x) ? x : y;

		crossStreet[0] = x;
		crossStreet[1] = y;
		return street;
	}

	/**
	 * This method obtains the text representation of a direction.
	 * 
	 * @param direction
	 *            Current direction
	 * @return Text representation
	 */
	private String getTurn(Direction direction) {
		switch (direction) {
		case NORTH:
			return "North";
		case SOUTH:
			return "South";
		case EAST:
			return "East";
		case WEST:
			return "West";
		case NORTHEAST:
			return "Northeast";
		case NORTHWEST:
			return "Northwest";
		case SOUTHEAST:
			return "Southeast";
		case SOUTHWEST:
			return "Southwest";
		default:
			return null;
		}
	}

	/**
	 * This method prints out the introduction UI text.
	 */
	public void intro() {
		System.out.println("\nGPS Navigation System");
		System.out.println("========================\n");
		System.out.println("\t(1) PTP Navigation");
		System.out.println("\t(2) Find Destination");
		System.out.println("\t(3) End Program\n");
	}

	/**
	 * This method prints the command message.
	 */
	public void getCommand() {
		System.out.print("Enter a command: ");
	}

	/**
	 * This method states that an empty input has been entered.
	 */
	public void emptyInput() {
		System.out.println("Error: You did not input a command.\n");
		System.out.println("--------------------------------------\n");
	}

	/**
	 * This method states that an an invalid command has been entered.
	 */
	public void unreadable() {
		System.out.println("Error: Invalid input.");
	}

	/**
	 * This method prints out the PTP dialogue.
	 * 
	 * @param msg
	 *            Type of input used
	 */
	public void ptp(int msg) {
		if (msg == 0) {
			System.out.print("\nEnter a destination: ");
		} else if (msg == 1) {
			System.out.println("\n--------------------------------------\n");
			System.out.println(
					"Keywords: Library, Recreation, Landmark, Sports, Arts, Hospital,\n\t  Health&Care, Fast-food, Restaurant, Fitness, Dining");
			System.out.print("\nSelect a destination: ");
		}
	}

	/**
	 * This method prints out a variation of the PTP dialogue.
	 * 
	 * @param hm
	 *            Hashmap of valid locations
	 */
	public void ptp(HashMap<String, Location> hm) {
		Location[] locations = new Location[hm.values().size()];
		locations = hm.values().toArray(locations);

		System.out.println("\n--------------------------------------\n");
		System.out.println("Previously Saved Locations:\n");

		for (int i = 0; i < hm.values().size(); ++i)
			System.out.println(getLocation(locations[i]));

		System.out.print("\nEnter a starting location: ");
	}

	/**
	 * This method displays search results to the keyword list.
	 * 
	 * @param result List of possible results
	 */
	public void displaySearchResults(ArrayList<Location> result) {
		int index = 0;

		System.out.println("\n--------------------------------------\n");
		System.out.println("Search Results:\n");

		for (Location i : result)
			System.out.println("(" + index++ + ") " + getLocation(i) + "");
		System.out.println("(" + index + ") Find Closest Location");
	}

	/**
	 * This method prints out the prompt for user input.
	 */
	public void promptLocation() {
		System.out.print("\nSelect a location: ");
	}

	/**
	 * This method prints out the nearest location obtained using the search.
	 * 
	 * @param nearest Nearest location
	 */
	public void closestFound(Location nearest) {
		System.out.println("\nNearest Location: " + getLocation(nearest));
	}

	/**
	 * This method prints out the prompt for user input.
	 * 
	 * @param value
	 *            Value of the input entered.
	 */
	public void promptNavigate(int value) {
		if (value == 0) {
			System.out.println("\nNavigation Options:\n");
			System.out.println("\t(1) Navigate");
			System.out.println("\t(2) List Directions\n");
			System.out.print("Navigate or List Directions?: ");
		} else if (value == 1) {
			System.out.println("\nBeginning Navigation..");
			System.out.println("Note: Press 'enter' to view the next instruction.\n");
		} else if (value == 2)
			System.out.println("\nDisplaying Directions..");
	}

	/**
	 * Prints out the exit message.
	 */
	public void exit() {
		System.out.println("Program terminated.\n");
		System.out.println("--------------------------------------\n");
	}
}
