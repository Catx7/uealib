package genetic.tsp;

import genetic.RandomSolutionGenerator;

public class RandomSalesmanRouteGenerator extends
		RandomSolutionGenerator<SalesmanRoute> {

	public RandomSalesmanRouteGenerator(int chromosomeLength) {
		super(chromosomeLength);
	}

	public RandomSalesmanRouteGenerator(
			genetic.GeneticContext<genetic.Generation<SalesmanRoute>, SalesmanRoute> context) {
		super(context);
	}

	private static java.util.Random random = new java.util.Random();

	@Override
	public SalesmanRoute generateRandomSolution() {
		// the Knuth shuffle
		int[] tour = new int[chromosomeLength];
		tour[0] = 0;
		for (int i = 1; i <= chromosomeLength - 1; ++i) {
			int s = random.nextInt(i + 1);
			tour[i] = tour[s];
			tour[s] = i;
		}
		return new SalesmanRoute(tour);
	}

}
