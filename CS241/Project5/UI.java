package cpp.cs.cs241.prog_assgmnt_5;

import java.util.ArrayList;
import java.util.Scanner;

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
 * This class deals with the communication between the user and the program.
 * 
 * @author Andrew Trang
 *
 */
public class UI {
	/**
	 * This field represents the state of the program.
	 */
	public static boolean running = true;
	/**
	 * This field represents the object that does the calculations.
	 */
	GPS map = new GPS();
	/**
	 * This field represents the object that manages the dialogue.
	 */
	private Dialogue d = new Dialogue();
	/**
	 * This field represents the user input.
	 */
	private Scanner sc = new Scanner(System.in);

	/**
	 * This method represents the program's inner loop.
	 */
	public void console() {
		while (running) {
			d.intro();
			d.getCommand();
			processCommand(sc.nextLine().toLowerCase());
		}
	}

	/**
	 * This method handles the main program commands.
	 * 
	 * @param input
	 *            User input
	 */
	private void processCommand(String input) {
		if (input.length() == 0)
			d.emptyInput();
		else if (input.contains("1") || input.contains("ptp") || input.contains("nav")) {
			ptp();
		} else if (input.contains("2") || input.contains("find") || input.contains("destination")) {
			nav();
		} else if (input.contains("3") || input.contains("end")) {
			running = false;
			d.exit();
		} else
			d.unreadable();
	}

	/**
	 * This method searches for a location provided an input
	 * 
	 * @param input
	 *            User input
	 * @param index
	 *            Value of which dialogue to use
	 * @return Location Obtained using the user input
	 */
	private Location processCommandPTP(String input, int index) {
		switch (input.toLowerCase()) {
		case "recreation":
			return specifyLocation(map.recreation);
		case "landmark":
			return specifyLocation(map.landmark);
		case "sports":
			return specifyLocation(map.sports);
		case "health&care":
			return specifyLocation(map.healthcare);
		case "dining":
			return specifyLocation(map.dining);
		case "fast-food":
			return specifyLocation(map.fastfood);
		case "restaurant":
			return specifyLocation(map.restaurant);
		case "library":
			return map.hm.get("los santos public library");
		case "fitness":
			return map.hm.get("los santos gym");
		case "arts":
			return map.hm.get("centennial theatre");
		case "hospital":
			return map.hm.get("all saints general hospital");
		case "los santos public library":
		case "los santos saints' stadium":
		case "vinewood boulevard":
		case "los santos forum":
		case "los santos city hall":
		case "centennial theatre":
		case "all saints general hospital":
		case "richman country club":
		case "burgershot":
		case "los santos gym":
		case "cluckin'bell":
		case "pimiento's":
			return map.hm.get(input);
		default:
			d.unreadable();

			if (index < 2)
				d.ptp(index);
			else
				d.ptp(map.hm);
			return processCommandPTP(sc.nextLine().toLowerCase(), index);
		}
	}

	/**
	 * This method prompts the user to specify a location given the search
	 * results.
	 * 
	 * @return Location Obtained using the user input
	 */
	private Location specifyLocation(ArrayList<Location> location) {
		int selection = -1;

		while (selection < 0 || selection > location.size()) {
			d.displaySearchResults(location);
			d.promptLocation();
			try {
				selection = sc.nextInt();
				sc.nextLine();
			} catch (Exception e) {
				sc.nextLine();
			}
		}
		if (selection == location.size()) {
			d.closestFound(location.get(map.getClosest(location)));
			return location.get(map.getClosest(location));
		}
		return location.get(selection);
	}

	/**
	 * This method handles the PTP function.
	 */
	private void ptp() {
		Location start, end;

		d.ptp(map.hm);
		start = processCommandPTP(sc.nextLine().toLowerCase(), 2);
		d.ptp(0);
		end = processCommandPTP(sc.nextLine().toLowerCase(), 0);
		navOpt(map.navigate(start, end));
	}

	/**
	 * This method handles the quick navigation function.
	 */
	private void nav() {
		Location end;

		d.ptp(1);
		end = processCommandPTP(sc.nextLine().toLowerCase(), 1);
		navOpt(map.navigate(Location.CITY_HALL, end));
	}

	/**
	 * This method allows the user to specify to list the directions or to do
	 * step-by-step navigation
	 * 
	 * @param route
	 *            Route taken from the starting point to the destination
	 */
	private void navOpt(ArrayList<Location> route) {
		d.promptNavigate(0);

		switch (sc.nextLine()) {
		case "1":
			String[] stepByStep = d.getDirections(route, route.get(route.size() - 1), route.get(0)).split("\n");
			d.promptNavigate(1);
			System.out.println(stepByStep[0]);
			for (int i = 1; i < stepByStep.length - 2; ++i) {
				System.out.print(stepByStep[i]);
				sc.nextLine();
			}
			System.out.println("\n" + stepByStep[0]);
			break;
		case "2":
			d.promptNavigate(2);
			d.getRoute(route);
			System.out.println(d.getDirections(route, route.get(route.size() - 1), route.get(0)));
			break;
		default:
			d.unreadable();
			navOpt(route);
		}
	}
}
