package genetic.knapsack;

import genetic.Generation;
import genetic.GeneticContext;

public class Initializator extends
		genetic.Initializator<Generation<KnapsackItems>, KnapsackItems> {

	public static final int DEFAULT_GENERATION_SIZE = 100;

	public Initializator(GeneticContext<Generation<KnapsackItems>, KnapsackItems> context) {
		super(context, new Generation<KnapsackItems>(context));
	}

	@Override
	public Generation<KnapsackItems> getInitialGeneration() {
		return this.getInitialGeneration(new RandomKnapsackGenerator(
				context), DEFAULT_GENERATION_SIZE);
	}
}
