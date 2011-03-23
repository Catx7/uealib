package genetic.knapsack;

import java.util.BitSet;

import genetic.RandomSolutionGenerator;

public class RandomKnapsackGenerator extends
		RandomSolutionGenerator<KnapsackItems> {
	
	private java.util.Random random;
	
	private genetic.Evaluator<KnapsackItems> evaluator;
		
	public RandomKnapsackGenerator(
			genetic.GeneticContext<genetic.Generation<KnapsackItems>, KnapsackItems> context) {
		super(context);
		evaluator = context.getEvaluator();
		random = new java.util.Random();
	}
	
	public KnapsackItems generateRandomKnapsackItems() {
		BitSet vector = new BitSet(chromosomeLength);
		for (int i = 0; i < chromosomeLength; ++i)
			if (random.nextBoolean())
				vector.set(i);
		return new KnapsackItems(vector, chromosomeLength, evaluator);
	}
	
	@Override
	public KnapsackItems generateRandomSolution() {
		KnapsackItems solution = generateRandomKnapsackItems();
		evaluator.makeFeasibleSolution(solution);
		return solution;
	}

}
