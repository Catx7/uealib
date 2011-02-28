package taboosearch;

import java.util.Collection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import common.Evaluated;
import common.Fabric;
import common.TicksStoppingCriteria;

import taboosearch.permutations.Move;
import taboosearch.permutations.Solution;

public abstract class ParallelTabooSearchAlgorithm<S extends Solution,
										  		   M extends Move<S>,
										  		   G extends Generation<S>,
										  		   C extends Context<S, M, G>> {
	protected Initializator<S, G> initializator;
	protected Fabric<Generator<S, M, G>> generatorFabric;
	protected TicksStoppingCriteria<S, G, C> stoppingCriteria;
	protected Fabric<Selector<S, M, G, C>> selectorFabric;
	protected core.TransitionCriteria<G> transitionCriteria;
	protected C context;
	
	public ParallelTabooSearchAlgorithm(
			Initializator<S, G> initializator,
			Fabric<Generator<S, M, G>> generatorFabric,
			TicksStoppingCriteria<S, G, C> stoppingCriteria,
			Fabric<Selector<S, M, G, C>> selectorFabric,
			C context) {
		this.initializator = initializator;
		this.generatorFabric = generatorFabric;
		this.stoppingCriteria = stoppingCriteria;
		this.selectorFabric = selectorFabric;
		this.context = context;
	}
	
	
	protected abstract Collection<TabooSearchWorker<S, M, G, C>> getWorkers();

	
	public Evaluated<S> solve() {
		long begin = System.currentTimeMillis();
		Collection<TabooSearchWorker<S, M, G, C>> workers = getWorkers();
		ExecutorService threadExecutor = Executors.newFixedThreadPool(workers.size());
		
		for (TabooSearchWorker<S, M, G, C> worker : workers)
			threadExecutor.execute(worker);
		
		threadExecutor.shutdown();
		try {
			threadExecutor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
		} catch (InterruptedException e) {
			System.err.println(e.getMessage());
		}
		
		long finish = System.currentTimeMillis();
		System.out.println((finish - begin) / 1000.0 + "sec");
		
		return new Evaluated<S>(context.bestSolutionEver, context.bestSolutionEverCost);
    }
	
}
