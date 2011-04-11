package taboosearch.permutations;

import java.util.ArrayList;
import java.util.Collection;

import common.Fabric;
import common.alternative.TicksStoppingCriteria;

import taboosearch.Context;
import taboosearch.Generator;
import taboosearch.Initializator;
import taboosearch.Selector;
import taboosearch.TabooSearchWorker;

public class ParallelTabooSearchAlgorithm<S extends Solution, M extends Move<S>, C extends Context<S, M>>
				extends taboosearch.ParallelTabooSearchAlgorithm<S, M, C> {

	int dimensionality;
	public ParallelTabooSearchAlgorithm(
			int dimensionality,
			Initializator<S> initializator,
			Fabric<Generator<S, M>> generatorFabric,
			TicksStoppingCriteria<S, C> stoppingCriteria,
			Fabric<Selector<S, M, C>> selectorFabric, C context) {
		super(initializator, generatorFabric, stoppingCriteria, selectorFabric, context);
		this.dimensionality = dimensionality;
	}

	@Override
	protected Collection<TabooSearchWorker<S, M, C>> getWorkers() {
		ArrayList<TabooSearchWorker<S, M, C>> workers = new ArrayList<TabooSearchWorker<S, M, C>>();
		int n = Runtime.getRuntime().availableProcessors();
		int delta = dimensionality / n - 1;
		for (int i = 0; i < n; ++i) {
			S initialSolution = initializator.getInitialSolution(i + delta);
			workers.add(new TabooSearchWorker<S, M, C>
				(initialSolution, generatorFabric.make(),
				 stoppingCriteria, selectorFabric.make(), context));
		}
		return workers;
	}
}
