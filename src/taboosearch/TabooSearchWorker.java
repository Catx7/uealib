package taboosearch;

import java.util.List;

import common.AbstractGenerationFabric;
import common.Pair;
import common.TicksStoppingCriteria;
import taboosearch.permutations.FrequencyMemory;
import taboosearch.permutations.Solution;
import taboosearch.permutations.Move;

import readers.Graph;
import taboosearch.tenures.ConstantTenureStrategy;
import taboosearch.permutations.tsp.TSPEvaluator;
import taboosearch.permutations.tsp.TSPInitializator;

import taboosearch.permutations.Generator;

public class TabooSearchWorker<M extends Move<Solution>, C extends Context<Solution, M, Generation<Solution>>>
				implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
/*
	private C context;
	private FrequencyMemory<TSPSolution, M> frequencyMemory;
	private Evaluator<TSPSolution, M> evaluator;
	private Taboolator<TSPSolution, M> taboolator;
	private AbstractGenerationFabric<TSPSolution, TSPGeneration> generationFabric;
	private AdmissibilityChecker<TSPSolution, M> checker;
	private EliteCandidateList<TSPSolution, M> eliteList;
	private TicksStoppingCriteria<TSPSolution, TSPGeneration, C> stoppingCriteria;
	private Generator<TSPSolution, M, TSPGeneration> generator;
	private Selector<TSPSolution, M, TSPGeneration, C> selector;
	private TSPGeneration currentGeneration;
	
	public TabooSearchWorker(
			int idx,
			Graph graph,
			C context,
			TicksStoppingCriteria<TSPSolution, TSPGeneration, C> stoppingCriteria,
			double diversityCoef,
			int tenure,
			int eliteListSize) {
		
		this.frequencyMemory = new FrequencyMemory<TSPSolution, M>(graph.getVertexesNumber(), diversityCoef);		
		//this.evaluator = new TSPEvaluator<M>(graph, frequencyMemory);
		this.taboolator	= new Taboolator<TSPSolution, M>(new ConstantTenureStrategy(tenure));
		this.generationFabric = new TSPGenerationFabric();
		this.checker = new AdmissibilityChecker<TSPSolution, M>(evaluator, taboolator);
		this.eliteList = new EliteCandidateList<TSPSolution, M>(eliteListSize, checker, evaluator);
		
		this.generator = null; //new TSPGenerator<M>(graph);
		
		this.selector = new Selector<TSPSolution, M, TSPGeneration, C>
			(evaluator, taboolator, frequencyMemory, checker, eliteList, generationFabric, context);
		
		this.stoppingCriteria = stoppingCriteria;
		this.context = context;
		//TSPInitializator initializator = new TSPInitializator(graph, evaluator);
		//currentGeneration = initializator.getInitialGeneration(idx);
	}

	@Override
	public void run() {
		while (!stoppingCriteria.isSatisfied(currentGeneration, context)) {
			Pair<TSPSolution, List<M>> moves = generator.getNext(currentGeneration);
			currentGeneration = selector.keepTheBestSolutions(moves);
			context.tick();
		}
	}
*/
}
