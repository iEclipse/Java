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
 * This class handles the saved locations and the connections between each
 * location.
 * 
 * @author Andrew Trang
 *
 */
public class Map {
	HashMap<String, Location> hm = new HashMap<String, Location>();
	ArrayList<Location> landmark = new ArrayList<Location>();
	ArrayList<Location> recreation = new ArrayList<Location>();
	ArrayList<Location> sports = new ArrayList<Location>();
	ArrayList<Location> dining = new ArrayList<Location>();
	ArrayList<Location> fastfood = new ArrayList<Location>();
	ArrayList<Location> restaurant = new ArrayList<Location>();
	ArrayList<Location> healthcare = new ArrayList<Location>();

	/**
	 * This constructor initializes the saved locations and the hashmap for the
	 * quick-search.
	 */
	public Map() {
		System.out.println("Loading Map..");
		Location.LIBRARY.dir.add(new Instruction(Location.STADIUM, Direction.EAST, 3));
		Location.LIBRARY.dir.add(new Instruction(Location.CITY_HALL, Direction.SOUTH, 5));
		Location.STADIUM.dir.add(new Instruction(Location.VINEWOOD, Direction.EAST, 2));
		Location.STADIUM.dir.add(new Instruction(Location.THEATRE, Direction.SOUTH, 1));
		Location.VINEWOOD.dir.add(new Instruction(Location.FORUM, Direction.EAST, 5));
		Location.VINEWOOD.dir.add(new Instruction(Location.HOSPITAL, Direction.SOUTH, 6));
		Location.FORUM.dir.add(new Instruction(Location.COUNTRY_CLUB, Direction.SOUTH, 1));
		Location.CITY_HALL.dir.add(new Instruction(Location.LIBRARY, Direction.NORTH, 5));
		Location.CITY_HALL.dir.add(new Instruction(Location.THEATRE, Direction.EAST, 4));
		Location.THEATRE.dir.add(new Instruction(Location.CITY_HALL, Direction.WEST, 4));
		Location.THEATRE.dir.add(new Instruction(Location.STADIUM, Direction.NORTH, 1));
		Location.THEATRE.dir.add(new Instruction(Location.HOSPITAL, Direction.EAST, 7));
		Location.THEATRE.dir.add(new Instruction(Location.BURGER_SHOT, Direction.SOUTH, 2));
		Location.HOSPITAL.dir.add(new Instruction(Location.THEATRE, Direction.WEST, 7));
		Location.HOSPITAL.dir.add(new Instruction(Location.VINEWOOD, Direction.NORTH, 6));
		Location.HOSPITAL.dir.add(new Instruction(Location.COUNTRY_CLUB, Direction.EAST, 3));
		Location.HOSPITAL.dir.add(new Instruction(Location.GYM, Direction.SOUTH, 1));
		Location.COUNTRY_CLUB.dir.add(new Instruction(Location.HOSPITAL, Direction.WEST, 3));
		Location.COUNTRY_CLUB.dir.add(new Instruction(Location.FORUM, Direction.NORTH, 1));
		Location.COUNTRY_CLUB.dir.add(new Instruction(Location.CLUCKIN_BELL, Direction.SOUTH, 3));
		Location.BURGER_SHOT.dir.add(new Instruction(Location.THEATRE, Direction.NORTH, 2));
		Location.BURGER_SHOT.dir.add(new Instruction(Location.GYM, Direction.EAST, 1));
		Location.GYM.dir.add(new Instruction(Location.BURGER_SHOT, Direction.WEST, 1));
		Location.GYM.dir.add(new Instruction(Location.HOSPITAL, Direction.NORTH, 1));
		Location.GYM.dir.add(new Instruction(Location.CLUCKIN_BELL, Direction.EAST, 1));
		Location.GYM.dir.add(new Instruction(Location.PIMIENTOS, Direction.SOUTHEAST, 2));
		Location.CLUCKIN_BELL.dir.add(new Instruction(Location.GYM, Direction.WEST, 1));
		Location.CLUCKIN_BELL.dir.add(new Instruction(Location.COUNTRY_CLUB, Direction.NORTH, 3));
		Location.CLUCKIN_BELL.dir.add(new Instruction(Location.PIMIENTOS, Direction.SOUTH, 3));
		Location.PIMIENTOS.dir.add(new Instruction(Location.CLUCKIN_BELL, Direction.NORTH, 3));

		recreation.add(Location.LIBRARY);
		landmark.add(Location.LIBRARY);
		recreation.add(Location.STADIUM);
		landmark.add(Location.STADIUM);
		sports.add(Location.STADIUM);
		recreation.add(Location.VINEWOOD);
		landmark.add(Location.VINEWOOD);
		recreation.add(Location.FORUM);
		landmark.add(Location.FORUM);
		sports.add(Location.FORUM);
		landmark.add(Location.CITY_HALL);
		recreation.add(Location.THEATRE);
		landmark.add(Location.THEATRE);
		healthcare.add(Location.HOSPITAL);
		recreation.add(Location.COUNTRY_CLUB);
		dining.add(Location.BURGER_SHOT);
		fastfood.add(Location.BURGER_SHOT);
		restaurant.add(Location.BURGER_SHOT);
		healthcare.add(Location.GYM);
		dining.add(Location.CLUCKIN_BELL);
		fastfood.add(Location.CLUCKIN_BELL);
		restaurant.add(Location.CLUCKIN_BELL);
		dining.add(Location.PIMIENTOS);
		restaurant.add(Location.PIMIENTOS);

		hm.put("los santos public library", Location.LIBRARY);
		hm.put("los santos saints' stadium", Location.STADIUM);
		hm.put("vinewood boulevard", Location.VINEWOOD);
		hm.put("los santos forum", Location.FORUM);
		hm.put("los santos city hall", Location.CITY_HALL);
		hm.put("centennial theatre", Location.THEATRE);
		hm.put("all saints general hospital", Location.HOSPITAL);
		hm.put("richman country club", Location.COUNTRY_CLUB);
		hm.put("burgershot", Location.BURGER_SHOT);
		hm.put("los santos gym", Location.GYM);
		hm.put("cluckin'bell", Location.CLUCKIN_BELL);
		hm.put("pimiento's", Location.PIMIENTOS);
		System.out.println("Load Complete..");
	}
}
