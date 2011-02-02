package taboosearch;


import common.TicksStoppingCriteria;
import common.UnconditionalTransitionCriteria;

import readers.Graph;
import readers.graphs.GeoCoordsGraphReader;
import readers.graphs.GraphReader;
import taboosearch.tenures.ConstantTenureStrategy;
import taboosearch.tsp.TSPContext;
import taboosearch.tsp.TSPEvaluator;
import taboosearch.tsp.TSPGeneration;
import taboosearch.tsp.TSPGenerator;
import taboosearch.tsp.TSPInitializator;
import taboosearch.tsp.TSPSolution;
import taboosearch.tsp.TSPTaboolator;

public class Main {

	public static void main(String[] args) {
		GraphReader graphReader = new GeoCoordsGraphReader();
		Graph graph = graphReader.readFromFile("/home/rrhu/workspace/uealib/graphs/burma14.txt");

		TSPEvaluator evaluator = new TSPEvaluator(graph);
		TSPTaboolator taboolator = new TSPTaboolator(new ConstantTenureStrategy(5));
		
		TSPContext context = new TSPContext();
		
		context.setEvaluator(evaluator);
		context.setTaboolator(taboolator);
		context.bestSolutionEverFitness = Double.MAX_VALUE;
		
		TSPInitializator initializator = new TSPInitializator(graph, context);
		TSPGenerator generator = new TSPGenerator(context);
		
		TicksStoppingCriteria<TSPSolution, TSPGeneration, TSPContext> stoppingCriteria
			= new TicksStoppingCriteria<TSPSolution, TSPGeneration, TSPContext>(context, 2000);
		
		Selector<TSPSolution, TSPGeneration, TSPContext> selector
			= new Selector<TSPSolution, TSPGeneration, TSPContext>(context);
		
		UnconditionalTransitionCriteria<TSPSolution, TSPGeneration, TSPContext> transitionCriteria
			= new UnconditionalTransitionCriteria<TSPSolution, TSPGeneration, TSPContext>();
		
		TabooSearchAlgorithm<TSPSolution, TSPGeneration, TSPContext> algorithm
			= new TabooSearchAlgorithm<TSPSolution, TSPGeneration, TSPContext>(
				initializator,
				generator,
				stoppingCriteria,
				selector,
				transitionCriteria,
				context);
		algorithm.solve();
	}

}
