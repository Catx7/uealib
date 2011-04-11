package taboosearch;

import java.util.Collection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import common.Evaluated;
import common.Fabric;
import common.alternative.TicksStoppingCriteria;

import taboosearch.permutations.Move;
import taboosearch.permutations.Solution;

public abstract class ParallelTabooSearchAlgorithm<S extends Solution,
										  		   M extends Move<S>,
										  		   C extends Context<S, M>> {
	protected Initializator<S> initializator;
	protected Fabric<Generator<S, M>> generatorFabric;
	protected TicksStoppingCriteria<S, C> stoppingCriteria;
	protected Fabric<Selector<S, M, C>> selectorFabric;
	protected C context;
	
	public ParallelTabooSearchAlgorithm(
			Initializator<S> initializator,
			Fabric<Generator<S, M>> generatorFabric,
			TicksStoppingCriteria<S, C> stoppingCriteria,
			Fabric<Selector<S, M, C>> selectorFabric,
			C context) {
		this.initializator = initializator;
		this.generatorFabric = generatorFabric;
		this.stoppingCriteria = stoppingCriteria;
		this.selectorFabric = selectorFabric;
		this.context = context;
	}
	
	protected abstract Collection<TabooSearchWorker<S, M, C>> getWorkers();
	
	public Evaluated<S> solve() {
		long begin = System.currentTimeMillis();
		Collection<TabooSearchWorker<S, M, C>> workers = getWorkers();
		ExecutorService threadExecutor = Executors.newFixedThreadPool(workers.size());
		
		for (TabooSearchWorker<S, M, C> worker : workers)
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
