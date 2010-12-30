package genetic.tsp;

import genetic.Generation;
import genetic.GenerationFabric;
import genetic.GenerationNumStoppingCriterion;
import genetic.GeneticAlgorithm;
import genetic.GeneticContext;
import genetic.RouletteWheelGenerator;
import core.TransitionCriteria;
import readers.*;

public class Main {

	public static final int EX_DATAERR = 65;
	public static final int EX_NOINPUT = 66;

	public static void main(String[] args) {
		GeneticAlgorithm<Generation<SalesmanRoute>, SalesmanRoute> geneticTSPSolver;
		Graph graph;
		GeneticContext<Generation<SalesmanRoute>, SalesmanRoute> context;
		long beginTime;
		try {
			graph = new GeoCoordsGraphReader()
					.readFromFile("graphs/burma14.txt");
			beginTime = System.currentTimeMillis();
			context = new GeneticContext<Generation<SalesmanRoute>, SalesmanRoute>(
					new TSPEvaluator(graph), graph.getWeights().length,
					new GenerationFabric<SalesmanRoute>());
			Initializator init = new Initializator(context);
			GenerationNumStoppingCriterion<Generation<SalesmanRoute>, SalesmanRoute> stop = new GenerationNumStoppingCriterion<Generation<SalesmanRoute>, SalesmanRoute>(
					9000, context);
			core.Generator<Generation<SalesmanRoute>> generator = new RouletteWheelGenerator<Generation<SalesmanRoute>, SalesmanRoute>(
					context, true);
			core.Selector<Generation<SalesmanRoute>> selector = new genetic.KeepAllSelector<Generation<SalesmanRoute>>();
			TransitionCriteria<Generation<SalesmanRoute>> transCriterion = new genetic.UnconditionalTransition<Generation<SalesmanRoute>>();
			geneticTSPSolver = new GeneticAlgorithm<Generation<SalesmanRoute>, SalesmanRoute>(
					init, generator, stop, selector, transCriterion, context);
		} catch (java.util.InputMismatchException e) {
			System.err.println("Invalid data!");
			System.exit(EX_DATAERR);
			return;
		} catch (java.util.NoSuchElementException e) {
			System.err.println("Premature end of data!");
			System.exit(EX_NOINPUT);
			return;
		} /*
		 * catch (java.io.FileNotFoundException e) {
		 * System.err.println("File not found!"); System.exit(EX_NOINPUT);
		 * return; }
		 */
		Generation<SalesmanRoute> lastGeneration = new Generation<SalesmanRoute>(
				null);
		lastGeneration = geneticTSPSolver.solve();
		long endTime = System.currentTimeMillis();
		System.out.println("Executution time = "
				+ ((endTime - beginTime) / 1000.) + "s .");
		System.out.println("The best route in the last generation is "
				+ lastGeneration.getBest().get(0).getRepresentation());
		System.out.println("The best route is "
				+ context.getBestKnownRoute().getRepresentation());
		TSPEvaluator evaluator = new TSPEvaluator(graph);
		System.out.println("Fitness = "
				+ evaluator.evaluate(lastGeneration.getBest().get(0)));
		System.out.println("The best fitness = " + context.getBestFitness());
		System.out.println("Weight = "
				+ evaluator.getWeight(lastGeneration.getBest().get(0)));
		System.out.println("The best weight = "
				+ evaluator.getWeight(context.getBestKnownRoute()));
		final int optTour[] = { 0, 1, 13, 2, 3, 4, 5, 11, 6, 12, 7, 10, 8, 9 };
		System.out.println("Weight of random solution = "
				+ new TSPEvaluator(graph)
						.getWeight(new RandomSalesmanRouteGenerator(14)
								.generateRandomSolution()));
		System.out
				.println("Weight of the optimal solution = "
						+ new TSPEvaluator(graph).getWeight(new SalesmanRoute(
								optTour)));
	}
}
