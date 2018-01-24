import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Genetic {
	ArrayList<Board> population = new ArrayList<>();
	int populationSize;
	int n;
	int generation;

	//Initializes Population and N for N Queens
	public Genetic(int popSize, int n) {
		this.n = n;
		for (int i = 0; i < popSize; i++)
			population.add(new Board(n));
		populationSize = population.size();
	}

	//Solves the Board Using Selection, Crossover, and Mutation to Create Generations
	public Board solve(int limit) {
		Random rn = new Random();
		generation = 0;
		
		//While Solution isn't Found
		while (population.get(0).heuristic != 0) {
			
			//Return Null if Generation Limit Reached without Solution
			if (generation == limit)
				return null;
			
			//Sort Population by Fitness Function
			Collections.sort(population);
			
			
			ArrayList<Board> popUpdate = new ArrayList<>();

			//Keeps the Better Half of the Population
			for (int i = 0; i < populationSize / 2; i++)
				popUpdate.add(new Board(population.get(i).current));

			//Produces the other Half of the Population Using Selection, Crossover, and Mutation
			for (int i = 0; i < populationSize / 2 + populationSize % 2; i++) {
				int index1 = rn.nextInt(population.size());
				int index2 = rn.nextInt(population.size());

				//Select Parents
				Board p1 = population.get(index1);
				Board p2 = population.get(index2);
				Queen[] pos = new Queen[n];

				//Generate Child
				for (int j = 0; j < n; j++)
					if (rn.nextInt(2) == 0)
						pos[j] = new Queen(p1.current[j].pos);
					else
						pos[j] = new Queen(p2.current[j].pos);

				//Mutate [Current Probability 10% Chance of Mutation]
				//Note: Increasing Mutation Does NOT Guarantee Increase Chance of Finding Solution
				//		but it Increases Speed if Stuck
				for (int j = 0; j < n; j++)
					if (rn.nextInt(10) < 1)
						pos[j].pos[0] = rn.nextInt(n);
				Board c = new Board(pos);
				
				//Add to Population
				popUpdate.add(c);
			}
			population = popUpdate;
			generation++;
		}
		return population.get(0);
	}
}
