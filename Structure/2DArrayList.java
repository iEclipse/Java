import java.util.ArrayList;

class 2DArrayList {
	public static void main(String[] args) {
		// Creates a 2D ArrayList
		ArrayList<ArrayList<String>> market = new ArrayList<ArrayList<String>>();

		// Fills the 2D ArrayList with Individual Items
		for (int i = 0; i < 20; ++i) {
			market.add(new ArrayList<String>());
			market.get(i).add("Part1");
			market.get(i).add("Part2");
			market.get(i).add("Part3");
		}

		// Display Market Items, Replaces an Item, and Displays Updated Market
		displayContents(market);
		replaceContents(market.get(2), "Replaced1", "Replaced2", "Replaced3");
		displayContents(market);
	}

	// Displays each Item in a Market
	public static void displayContents(ArrayList<ArrayList<String>> market) {
		System.out.println("--- Market Items ---");
		for (int i = 0; i < 20; ++i) {
			for (int j = 0; j < 3; ++j) {
				System.out.print(market.get(i).get(j) + " ");
			}
			System.out.println();
		}
	}

	// Replaces an Item in a Market
	// *NOTE: Use a string array argument and modify the method in order to
	// allow a variable number of "parts"
	public static void replaceContents(ArrayList<String> item, String part1, String part2, String part3) {
		item.clear();
		item.add(part1);
		item.add(part2);
		item.add(part3);
	}
}