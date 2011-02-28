package taboosearch.permutations;

import java.util.ArrayList;
import java.util.Collection;

import common.Fabric;
import common.TicksStoppingCriteria;

import taboosearch.Context;
import taboosearch.Generation;
import taboosearch.Generator;
import taboosearch.Initializator;
import taboosearch.Selector;
import taboosearch.TabooSearchWorker;

public class ParallelTabooSearchAlgorithm<S extends Solution, M extends Move<S>, G extends Generation<S>, C extends Context<S, M, G>>
				extends taboosearch.ParallelTabooSearchAlgorithm<S, M, G, C> {

	int dimensionality;
	public ParallelTabooSearchAlgorithm(
			int dimensionality,
			Initializator<S, G> initializator,
			Fabric<Generator<S, M, G>> generatorFabric,
			TicksStoppingCriteria<S, G, C> stoppingCriteria,
			Fabric<Selector<S, M, G, C>> selectorFabric, C context) {
		super(initializator, generatorFabric, stoppingCriteria, selectorFabric, context);
		this.dimensionality = dimensionality;
	}

	@Override
	protected Collection<TabooSearchWorker<S, M, G, C>> getWorkers() {
		ArrayList<TabooSearchWorker<S, M, G, C>> workers = new ArrayList<TabooSearchWorker<S,M,G,C>>();
		int n = Runtime.getRuntime().availableProcessors();
		int delta = dimensionality / n - 1;
		for (int i = 0; i < n; ++i) {
			G initialGeneration = initializator.getInitialGeneration(i + delta);
			workers.add(new TabooSearchWorker<S, M, G, C>
				(initialGeneration, generatorFabric.getInstance(),
				 stoppingCriteria, selectorFabric.getInstance(), context));
		}
		return workers;
	}
}
