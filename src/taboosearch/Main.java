package taboosearch;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.ParserConfigurationException;

import common.AbstractGenerationFabric;
import common.TicksStoppingCriteria;
import common.UnconditionalTransitionCriteria;

import readers.Graph;
import readers.graphs.CoordsGraphReader;
import readers.graphs.GeoCoordsGraphReader;
import readers.graphs.GraphReader;
import readers.graphs.LowerRowMatrixGraphReader;
import readers.graphs.MatrixGraphReader;
import readers.graphs.UpperRowMatrixGraphReader;


import taboosearch.cbir.*;
import taboosearch.permutations.DummyFrequencyMemory;
import taboosearch.permutations.MoveFabric;
import taboosearch.permutations.SwapMove;
import taboosearch.permutations.Solution;
import taboosearch.permutations.FrequencyMemory;
import taboosearch.permutations.Move;
import taboosearch.gui.GUI;
import taboosearch.permutations.Generator;
import taboosearch.readers.FeaturesSpace;
import taboosearch.readers.FeaturesSpaceReader;
import taboosearch.tenures.ConstantTenureStrategy;
import taboosearch.tenures.TenureStrategy;
import taboosearch.tsp.*;


import org.xml.sax.SAXException;

public class Main {

	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException, IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		/*
		FeaturesSpaceReader fr = new FeaturesSpaceReader();
		FeaturesSpace space = fr.readFromFile("/home/rrhu/cbir/cpp/result.xml");
		space.computeD();
		space.computeLambdas();
		
		CBIREvaluator evaluator = new CBIREvaluator(space);
		
		Taboolator<CBIRSolution, CBIRSwapMove> taboolator
			= new Taboolator<CBIRSolution, CBIRSwapMove>(new ConstantTenureStrategy(18));

		AbstractGenerationFabric<CBIRSolution, CBIRGeneration> generationFabric
			= new CBIRGenerationFabric();
		
		AdmissibilityChecker<CBIRSolution, CBIRSwapMove> checker
			= new AdmissibilityChecker<CBIRSolution, CBIRSwapMove>(evaluator, taboolator);
	
		EliteCandidateList<CBIRSolution, CBIRSwapMove> eliteList
			= new EliteCandidateList<CBIRSolution, CBIRSwapMove>(10, checker, evaluator);
		
		taboosearch.permutations.DummyFrequencyMemory<CBIRSolution, CBIRSwapMove> frequencyMemory
			= new taboosearch.permutations.DummyFrequencyMemory<CBIRSolution, CBIRSwapMove>();
		
		CBIRContext context
			= new CBIRContext(evaluator, taboolator, frequencyMemory, generationFabric, eliteList);
		
		CBIRInitializator initializator = new CBIRInitializator(space, evaluator);
		CBIRGenerator generator = new CBIRGenerator(space.n);	
		
		TicksStoppingCriteria<CBIRSolution, CBIRGeneration, CBIRContext> stoppingCriteria
			= new TicksStoppingCriteria<CBIRSolution, CBIRGeneration, CBIRContext>(context, 10000);
	
		Selector<CBIRSolution, CBIRSwapMove, CBIRGeneration, CBIRContext> selector
			= new Selector<CBIRSolution, CBIRSwapMove, CBIRGeneration, CBIRContext>(checker, context);
		
		UnconditionalTransitionCriteria<CBIRSolution, CBIRGeneration, CBIRContext> transitionCriteria
			= new UnconditionalTransitionCriteria<CBIRSolution, CBIRGeneration, CBIRContext>();
		
		TabooSearchAlgorithm<CBIRSolution, CBIRSwapMove, CBIRGeneration, CBIRContext> algorithm
			= new TabooSearchAlgorithm<CBIRSolution, CBIRSwapMove, CBIRGeneration, CBIRContext>(
				initializator,
				generator,
				stoppingCriteria,
				selector,
				transitionCriteria,
				context);
		algorithm.solve();
		
		(new GUI(context.getSeries())).run();
		
		int[] or = new int[space.n];
		for (int i = 0; i < space.n; ++i) or[i] = i;
			
		System.out.println("EV" + evaluator.evaluate(new CBIRSolution(or)));
		System.out.println("EV" + evaluator.evaluate(context.bestSolutionEver));
		*/
		
		
		//GeoCoordsGraphReader graphReader = new GeoCoordsGraphReader();
		//Graph graph = graphReader.readFromFile("/home/rrhu/workspace/uealib/graphs/burma14.txt");

		GraphReader graphReader = new MatrixGraphReader();
		Graph graph = graphReader.readFromFile("/home/rrhu/workspace/uealib/graphs/bays29.txt");		

		//GraphReader graphReader = new UpperRowMatrixGraphReader();
		//Graph graph = graphReader.readFromFile("/home/rrhu/workspace/uealib/graphs/bayg29.txt");
		
		FrequencyMemory<Solution, Move<Solution>> frequencyMemory
			= new FrequencyMemory<Solution, Move<Solution>>(graph.getVertexesNumber(), 2000);
		
		TSPEvaluator<Solution, Move<Solution>> evaluator
			= new TSPEvaluator<Solution, Move<Solution>>(graph, frequencyMemory);

		Taboolator<Solution, Move<Solution>> taboolator
			= new Taboolator<Solution, Move<Solution>>(new ConstantTenureStrategy(6));

		AbstractGenerationFabric<Solution, Generation<Solution>> generationFabric
			= new AbstractGenerationFabric<Solution, Generation<Solution>>() {
				public Generation<Solution> makeGeneration() {
					return new Generation<Solution>();
				}
			  };
		
		AdmissibilityChecker<Solution, Move<Solution>> checker
			= new AdmissibilityChecker<Solution, Move<Solution>>(evaluator, taboolator);
	
		EliteCandidateList<Solution, Move<Solution>> eliteList
			= new EliteCandidateList<Solution, Move<Solution>>(1, checker, evaluator);
		
		Context<Solution, Move<Solution>, Generation<Solution>> context
			= new Context<Solution, Move<Solution>, Generation<Solution>>(evaluator, taboolator, frequencyMemory, generationFabric, eliteList);
		
		TSPInitializator<Solution, Generation<Solution>> initializator
			= new TSPInitializator<Solution, Generation<Solution>>(graph, evaluator, generationFabric);
		
		Generator<Solution, Move<Solution>, Generation<Solution>> generator
			= new Generator<Solution, Move<Solution>, Generation<Solution>>(graph.getVertexesNumber(),
				new MoveFabric<Solution, Move<Solution>>() {
					public Move<Solution> makeMove(int i, int j) {
						return new SwapMove<Solution>(i, j) {
							protected Solution makeSolution(int[] permutation) {
								return new Solution(permutation);
							}
						};
					}
				});

		TicksStoppingCriteria<Solution, Generation<Solution>,
			Context<Solution, Move<Solution>, Generation<Solution>>> stoppingCriteria
			= new TicksStoppingCriteria<Solution, Generation<Solution>,
				Context<Solution, Move<Solution>, Generation<Solution>>>(20000);
		
		Selector<Solution, Move<Solution>, Generation<Solution>,
			Context<Solution, Move<Solution>, Generation<Solution>>> selector
			= new Selector<Solution, Move<Solution>, Generation<Solution>,
				Context<Solution, Move<Solution>, Generation<Solution>>>
				(evaluator, taboolator, frequencyMemory, checker, eliteList, generationFabric, context);
		

		// NEW: [ 20, 23, 30, 7, 2, 42, 21, 17, 3, 18, 31, 22, 1, 49, 32, 45, 19, 41, 8, 9, 10, 43, 33, 51, 11, 52, 14, 13, 47, 26, 27, 28, 12, 25, 4, 6, 15, 5, 24, 48, 37, 38, 40, 39, 36, 35, 34, 44, 46, 16, 29, 50 ]  7570.497072025889
		//370.676sec
		/*
		 * 		threadExecutor.execute(new TabooSearchWorker(1, graph, context, stoppingCriteria, 10000, 20, 5));
		threadExecutor.execute(new TabooSearchWorker(19, graph, context, stoppingCriteria, 10000, 20, 5));
		threadExecutor.execute(new TabooSearchWorker(44, graph, context, stoppingCriteria, 10000, 20, 5));
		threadExecutor.execute(new TabooSearchWorker(25, graph, context, stoppingCriteria, 10000, 20, 5));
		 */
		/*ParallelTabooSearchAlgorithm<TSPSwapMove, TSPContext> ok
			= new ParallelTabooSearchAlgorithm<TSPSwapMove, TSPContext>(graph, context, stoppingCriteria, 5000, 11, 3);
		ok.solve();
		*/
		TabooSearchAlgorithm<Solution, Move<Solution>, Generation<Solution>, Context<Solution, Move<Solution>, Generation<Solution>>> algorithm
			= new TabooSearchAlgorithm<Solution, Move<Solution>, Generation<Solution>, Context<Solution, Move<Solution>, Generation<Solution>>>(
				initializator,
				generator,
				stoppingCriteria,
				selector,
				context);
		algorithm.solve();
		
		//(new GUI(context.getSeries())).run();
	}

}
