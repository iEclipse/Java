package cpp.cs.cs241.prog_assgmnt_5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;

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
 * This class does the calculations for the path-finding algorithm.
 * 
 * @author Andrew Trang
 *
 */
public class GPS extends Map {

	/**
	 * This method navigates to the closest location.
	 * 
	 * @param list
	 *            List of possible locations
	 * @return Closest location.
	 */
	public int navigate(ArrayList<Location> list) {
		return getClosest(list);
	}

	/**
	 * This method navigates to a destination from City Hall.
	 * 
	 * @param destination
	 *            Desired location
	 * @return List of directions to take.
	 */
	public ArrayList<Location> navigate(Location destination) {
		return getRoute(calculateDistance(Location.CITY_HALL), destination);
	}

	/**
	 * This method navigates to a destination from a starting point.
	 * 
	 * @param start
	 *            Starting location
	 * @param destination
	 *            Desired location
	 * @return List of directions to take
	 */
	public ArrayList<Location> navigate(Location start, Location destination) {
		return getRoute(calculateDistance(start), destination);
	}

	/**
	 * This method calculates the shortest path to any location from the
	 * starting point.
	 * 
	 * @param start
	 *            Starting location
	 * @return Array of previous locations based on the calculations
	 */
	private Location[] calculateDistance(Location start) {
		ArrayList<Location> unvisited = new ArrayList<Location>(EnumSet.allOf(Location.class));
		ArrayList<Location> visited = new ArrayList<Location>();
		Location[] prev = new Location[Location.values().length];
		int[] distance = new int[Location.values().length];
		Location currentLocation = start;

		Arrays.fill(distance, Integer.MAX_VALUE - 1);
		distance[start.get()] = 0;

		while (!unvisited.isEmpty()) {
			int closestDistance = Integer.MAX_VALUE;

			unvisited.remove(currentLocation);
			visited.add(currentLocation);

			for (int i = 0, current, test; i < currentLocation.dir.size(); ++i) {
				if (!visited.contains(currentLocation.dir.get(i).location)) {
					current = distance[currentLocation.dir.get(i).location.get()];
					test = currentLocation.dir.get(i).distance;

					if (current == Integer.MAX_VALUE - 1 || test + distance[currentLocation.get()] < current) {
						distance[currentLocation.dir.get(i).location.get()] = test + distance[currentLocation.get()];
						prev[currentLocation.dir.get(i).location.get()] = currentLocation;
					}
				}
			}

			for (int i = 0; i < unvisited.size(); ++i)
				if (distance[unvisited.get(i).get()] < closestDistance) {
					closestDistance = distance[unvisited.get(i).get()];
					currentLocation = unvisited.get(i);
				}
		}
		return prev;
	}

	/**
	 * This method uses a path and destination to calculate the optimum route.
	 * 
	 * @param path
	 *            Array of locations returned from the calculateDistance method
	 * @param destination
	 *            Desired location
	 * @return List of locations in the order of shortest distance to take
	 */
	public ArrayList<Location> getRoute(Location[] path, Location destination) {
		ArrayList<Location> route = new ArrayList<Location>();
		Location current = destination;

		route.add(destination);

		while (path[current.get()] != null) {
			route.add(path[current.get()]);
			current = path[current.get()];
		}
		return route;
	}

	/**
	 * This method uses a path and destination to calculate the optimum route
	 * given a list of possible locations.
	 * 
	 * @param list
	 *            List of possible locations
	 * @return List of locations in the order of shortest distance to take
	 */
	public int getClosest(ArrayList<Location> list) {
		ArrayList<Location> unvisited = new ArrayList<Location>(EnumSet.allOf(Location.class));
		ArrayList<Location> visited = new ArrayList<Location>();
		int[] distance = new int[Location.values().length];
		Location currentLocation = Location.CITY_HALL;
		int closestLocation = Integer.MAX_VALUE;
		int index = -1;

		Arrays.fill(distance, Integer.MAX_VALUE - 1);
		distance[currentLocation.get()] = 0;

		while (!unvisited.isEmpty()) {
			int closestDistance = Integer.MAX_VALUE;

			unvisited.remove(currentLocation);
			visited.add(currentLocation);

			for (int i = 0, current, test; i < currentLocation.dir.size(); ++i) {
				if (!visited.contains(currentLocation.dir.get(i).location)) {
					current = distance[currentLocation.dir.get(i).location.get()];
					test = currentLocation.dir.get(i).distance;

					if (current == Integer.MAX_VALUE - 1 || test + distance[currentLocation.get()] < current)
						distance[currentLocation.dir.get(i).location.get()] = test + distance[currentLocation.get()];
				}
			}

			for (int i = 0; i < unvisited.size(); ++i)
				if (distance[unvisited.get(i).get()] < closestDistance) {
					closestDistance = distance[unvisited.get(i).get()];
					currentLocation = unvisited.get(i);
				}
		}

		for (int i = 0; i < list.size(); ++i)
			if (distance[list.get(i).get()] < closestLocation) {
				closestLocation = distance[list.get(i).get()];
				index = i;
			}

		return index;
	}
}
