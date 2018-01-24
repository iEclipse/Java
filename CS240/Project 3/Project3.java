import java.util.Scanner;
public class Project3 {

	static FileHandler fh = new FileHandler();
	static boolean debug = true;

	// Main method containing the program's initial load and loop
	public static void main(String[] args) throws Exception {
		if (args.length > 0) {
			if (!fh.getFiles(args[0]))
				System.exit(0);
			fh.displayFiles(debug);
			printText();
			getAverages();
			loop();
		}
	}
	// Assigns averages to each list
	public static void getAverages() {
		double minAvg = 0;
		double maxAvg = 0;
		for (int i = 0; i < fh.hc.size; i++) {
			fh.hc.array[i].getAvg();
			if (fh.hc.array[i].name != null && minAvg == 0) {
				minAvg = fh.hc.array[i].avg;
				maxAvg = minAvg;
			}
			if (fh.hc.array[i].avg < minAvg)
				minAvg = fh.hc.array[i].avg;
			if (fh.hc.array[i].avg > maxAvg)
				maxAvg = fh.hc.array[i].avg;
		}
		System.out.printf("Minimum average: %.3f\n", minAvg);
		for (int i = 0; i < fh.hc.size; i++) {
			if (fh.hc.array[i].avg == minAvg)
				System.out.println("   " + fh.hc.array[i].name);
		}
		System.out.printf("\nMaximum average: %.3f\n", maxAvg);
		for (int i = 0; i < fh.hc.size; i++) {
			if (fh.hc.array[i].avg == maxAvg)
				System.out.println("   " + fh.hc.array[i].name);
		}
	}
	// Prints the data
	public static void printText() {
		System.out.println("\n# of collisions: " + fh.hc.collisions
				+ "\nSize of table: " + fh.hc.size
				+ "\n\n# of Names: " + fh.hc.names);
	}
	// Input and output loop
	public static void loop() {
		Scanner sc = new Scanner(System.in);
		String name;
		int index;
		while (true) {
			System.out.print("\nEnter name: ");
			name = sc.nextLine();
			index = Math.abs(fh.hc.hashCode(name) % fh.hc.size);
			int temp = index;
			boolean found = false;
			do {
				if (fh.hc.array[index].name != null && fh.hc.array[index].name.equals(name)) {
					System.out.println(fh.hc.array[index].name + ": "
							+ "Avg: " + fh.hc.array[index].avg
							+ "  # Scores: " + fh.hc.array[index].size);
					found = true;
					break;
				}
				else{
				fh.hc.index = index;
				index = fh.hc.hashCode2(name);
				}
			} while (index != temp);
			if (!found)
				System.out.println(name + " not found.");
		}
	}
}
