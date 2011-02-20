package taboosearch;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import readers.Graph;
import taboosearch.permutations.Move;
import taboosearch.permutations.Solution;


import common.Evaluated;
import common.Pair;
import common.TicksStoppingCriteria;

public class ParallelTabooSearchAlgorithm<M extends Move<Solution>, C extends Context<Solution, M, Generation<Solution>>> {
	
/*	private Graph graph;
	private C context;
	private TicksStoppingCriteria<TSPSolution, TSPGeneration, C> stoppingCriteria;
	private double diversityCoef;
	private int tenure;
	private int eliteListSize;
	
	public ParallelTabooSearchAlgorithm(
			Graph graph,
			C context,
			TicksStoppingCriteria<TSPSolution, TSPGeneration, C> stoppingCriteria,
			double diversityCoef,
			int tenure,
			int eliteListSize) {
		this.graph = graph;
		this.context = context;
		this.stoppingCriteria = stoppingCriteria;
		this.diversityCoef = diversityCoef;
		this.tenure = tenure;
		this.eliteListSize = eliteListSize;
	}
	
	@SuppressWarnings("unchecked")
	public Evaluated<TSPSolution> solve() {
		long begin = System.currentTimeMillis();
		
		int n =Runtime.getRuntime().availableProcessors();
		ExecutorService threadExecutor = Executors.newFixedThreadPool(n);
		int delta = graph.getVertexesNumber() / n - 1;
		for (int i = 0; i < n; ++i) {
			threadExecutor.execute(
					new TabooSearchWorker(1 + i * delta, graph, context, stoppingCriteria, diversityCoef, tenure, eliteListSize));
		}
		
		
		threadExecutor.shutdown();
		try {
			threadExecutor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
		} catch (InterruptedException e) {
			System.err.println(e.getMessage());
		}
		
		long finish = System.currentTimeMillis();
		System.out.println((finish - begin) / 1000.0 + "sec");
		
		return new Evaluated<TSPSolution>(context.bestSolutionEver, context.bestSolutionEverCost);
    }*/
	
}
