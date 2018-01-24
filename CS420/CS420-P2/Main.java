
public class Main {
	public static void main(String[] args) {
		steepestHillClimbTest();
		geneticAlgorithmTest();
	}

	public static void steepestHillClimbTest() {
		HillClimbing h = new HillClimbing();
		int solutions = 0;
		int success = 0;
		int steps = 0;
		long elapsedTime = 0;
		

		// Hill Climbing Results
		for (int i = 0; i < 200; i++) {
			long start = System.nanoTime();
			Board b = new Board(21); // Starting Board
			Board c; // Ending Board
			long end = System.nanoTime();
			c = h.solve(b);
			if (c.heuristic == 0) {
				// Prints First 3 Solved Boards
				if (solutions < 3) {
					System.out.println("SAHC Starting Board " + solutions);
					b.print();
					System.out.println("SAHC Solution Board " + solutions);
					c.print();
					solutions++;
				}
				success++;
				steps += h.steps;
				elapsedTime += (end - start);
			}
		}
		System.out.println("Success: " + (double)success/2 + "%, " + (double) (steps / success) + "Steps");
		System.out.println("Average Elapsed Time: " + (elapsedTime / success) + "ns\n");
	}

	public static void geneticAlgorithmTest() {
		int solutions = 0;
		int success = 0;
		int generations = 0;
		long elapsedTime = 0;
		Board b;
		for (int i = 0; i < 200; i++) {
			Genetic g = new Genetic(500, 21); // Population Size, Board Size N x N
			long start = System.nanoTime();
			b = g.solve(3000);
			long end = System.nanoTime();

			if (b != null) {
				if (solutions < 3) {
					System.out.println("Genetic Solution Board " + solutions);
					b.print();
					solutions++;
				}
				elapsedTime += (end - start);
				success++;
				generations += g.generation;
			}
		}
		
		System.out.println("Success: " + (double)success/2 + "%, " + ((double)generations / success) + " Generations");
		System.out.println("Average Elapsed Time: " + (elapsedTime / success) + "ns");
	}
}
