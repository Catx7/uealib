package taboosearch;


import readers.Graph;
import readers.graphs.GeoCoordsGraphReader;
import readers.graphs.GraphReader;
import taboosearch.tenures.ConstantTenureStrategy;
import taboosearch.tsp.TspContext;
import taboosearch.tsp.TspEvaluator;
import taboosearch.tsp.TspGeneration;
import taboosearch.tsp.TspGenerator;
import taboosearch.tsp.TspInitializator;
import taboosearch.tsp.TspSolution;
import taboosearch.tsp.TspTaboolator;

public class Main {

	public static void main(String[] args) {
		GraphReader graphReader = new GeoCoordsGraphReader();
		Graph graph = graphReader.readFromFile("/home/rrhu/workspace/uealib/graphs/burma14.txt");

		TspEvaluator evaluator = new TspEvaluator(graph);
		TspTaboolator taboolator = new TspTaboolator(new ConstantTenureStrategy(5));
		
		TspContext context = new TspContext();
		
		context.setEvaluator(evaluator);
		context.setTaboolator(taboolator);
		context.bestSolutionEverFitness = Double.MAX_VALUE;
		
		TspInitializator initializator = new TspInitializator(graph, context);
		TspGenerator generator = new TspGenerator(context);
		
		TicksStoppingCriteria<TspSolution, TspGeneration, TspContext> stoppingCriteria
			= new TicksStoppingCriteria<TspSolution, TspGeneration, TspContext>(context, 2000);
		
		Selector<TspSolution, TspGeneration, TspContext> selector
			= new Selector<TspSolution, TspGeneration, TspContext>(context);
		
		TransitionCriteria<TspSolution, TspGeneration, TspContext> transitionCriteria
			= new TransitionCriteria<TspSolution, TspGeneration, TspContext>();
		
		TabooSearchAlgorithm<TspSolution, TspGeneration, TspContext> algorithm
			= new TabooSearchAlgorithm<TspSolution, TspGeneration, TspContext>(
				initializator,
				generator,
				stoppingCriteria,
				selector,
				transitionCriteria,
				context);
		algorithm.solve();
	}

}
