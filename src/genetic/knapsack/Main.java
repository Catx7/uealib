package genetic.knapsack;

import genetic.Generation;
import genetic.GenerationFabric;
import genetic.GenerationNumStoppingCriterion;
import genetic.GeneticAlgorithm;
import genetic.GeneticContext;
import genetic.RouletteWheelGenerator;
import core.TransitionCriteria;
import readers.items.TestReader;

public class Main {

	public static final int EX_DATAERR = 65;
	public static final int EX_NOINPUT = 66;

	public static void main(String[] args) {
		GeneticAlgorithm<Generation<KnapsackItems>, KnapsackItems> geneticKnapsackSolver;
		
		readers.Collection collection;
		GeneticContext<Generation<KnapsackItems>, KnapsackItems> context;
		
		long beginTime;
		try {
			collection = new TestReader().readFromFile("src/genetic/knapsack/test1.txt");
			beginTime = System.currentTimeMillis();
			context = new GeneticContext<Generation<KnapsackItems>, KnapsackItems>(
					new KnapsackEvaluator(collection),
					collection.getItems().length,
					new GenerationFabric<KnapsackItems>());
			
			Initializator init = new Initializator(context);
			GenerationNumStoppingCriterion<Generation<KnapsackItems>, KnapsackItems> stop = new GenerationNumStoppingCriterion<Generation<KnapsackItems>, KnapsackItems>(
					5000, context);
			core.Generator<Generation<KnapsackItems>> generator = new RouletteWheelGenerator<Generation<KnapsackItems>, KnapsackItems>(
					context, true);
			core.Selector<Generation<KnapsackItems>> selector = new genetic.KeepAllSelector<Generation<KnapsackItems>>();
			TransitionCriteria<Generation<KnapsackItems>> transCriterion = new genetic.UnconditionalTransition<Generation<KnapsackItems>>();
			
			geneticKnapsackSolver = new GeneticAlgorithm<Generation<KnapsackItems>, KnapsackItems>(
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
		Generation<KnapsackItems> lastGeneration = new Generation<KnapsackItems>(
				null);
		lastGeneration = geneticKnapsackSolver.solve();
		long endTime = System.currentTimeMillis();
		System.out.println(lastGeneration.getRepresentation());
		System.out.println("Execution time = "
				+ ((endTime - beginTime) / 1000.) + "s .");
		System.out.println("The best variant in the last generation is "
				+ lastGeneration.getBest().get(0).getRepresentation());
		System.out.println("The best variant is "
				+ context.getBestKnownSolution().getRepresentation());
		KnapsackEvaluator evaluator = new KnapsackEvaluator(collection);
		System.out.println("Fitness = "
				+ evaluator.evaluate(lastGeneration.getBest().get(0)));
		System.out.println("The best fitness = " + context.getBestFitness());
	}
}
