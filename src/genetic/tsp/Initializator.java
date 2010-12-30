package genetic.tsp;

import genetic.Generation;
import genetic.GeneticContext;

public class Initializator extends
		genetic.Initializator<Generation<SalesmanRoute>, SalesmanRoute> {

	public static final int DEFAULT_GENERATION_SIZE = 100;

	public Initializator(GeneticContext<Generation<SalesmanRoute>, SalesmanRoute> context) {
		super(context, new Generation<SalesmanRoute>(context));
	}

	@Override
	public Generation<SalesmanRoute> getInitialGeneration() {
		return this.getInitialGeneration(new RandomSalesmanRouteGenerator(
				context), DEFAULT_GENERATION_SIZE);
	}
}
