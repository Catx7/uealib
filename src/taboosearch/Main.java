package taboosearch;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import common.AbstractGenerationFabric;
import common.TicksStoppingCriteria;
import common.UnconditionalTransitionCriteria;

import readers.Graph;
import readers.graphs.CoordsGraphReader;
import readers.graphs.GeoCoordsGraphReader;
import readers.graphs.GraphReader;
import readers.graphs.UpperRowMatrixGraphReader;

import taboosearch.cbir.CBIRAdmissibilityChecker;
import taboosearch.cbir.CBIRContext;
import taboosearch.cbir.CBIREvaluator;
import taboosearch.cbir.CBIRFrequencyMemory;
import taboosearch.cbir.CBIRGeneration;
import taboosearch.cbir.CBIRGenerator;
import taboosearch.cbir.CBIRInitializator;
import taboosearch.cbir.CBIRSolution;
import taboosearch.cbir.CBIRSwapMove;
import taboosearch.cbir.CBIRTaboolator;
import taboosearch.gui.GUI;
import taboosearch.permutations.Generator;
import taboosearch.readers.FeaturesSpace;
import taboosearch.readers.FeaturesSpaceReader;
import taboosearch.tenures.ConstantTenureStrategy;
import taboosearch.tsp.*;


import org.xml.sax.SAXException;

public class Main {

	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
		//List<Integer> list = new LinkedList<Integer>();
		//FileInputStream fi = new FileInputStream("/home/rrhu/workspace/uealib/graphs/berlin52.opt.txt");
		//Scanner s = new Scanner(fi, "utf-8");
		//for (int times = 0; times < 52; ++times) list.add(s.nextInt() - 1);
		//s.close();
		

		/*FeaturesSpaceReader fr = new FeaturesSpaceReader();
		FeaturesSpace space = fr.readFromFile("/home/rrhu/cbir/cpp/result.xml");
		space.computeD();
		space.computeLambdas();
		for (int i = 0; i < space.n; ++i) {
			for (int j = 0; j < space.n; ++j) {
				//System.out.print(space.d[i][j] + " ");
			}
			//System.out.println();
		}
		
		//CBIREvaluator ev = new CBIREvaluator(space);
		

		
		CBIREvaluator evaluator = new CBIREvaluator(space);
		CBIRTaboolator taboolator = new CBIRTaboolator(new ConstantTenureStrategy(7));
		CBIRFrequencyMemory frequencyMemory = new CBIRFrequencyMemory(space);
		
		CBIRContext context = new CBIRContext(evaluator, taboolator, frequencyMemory);
		
		
		CBIRInitializator initializator = new CBIRInitializator(space, context);
		CBIRGenerator generator = new CBIRGenerator(space, context);
		
		CBIRAdmissibilityChecker checker = new CBIRAdmissibilityChecker(evaluator, taboolator, frequencyMemory);
		
		
		TicksStoppingCriteria<CBIRSolution, CBIRGeneration, CBIRContext> stoppingCriteria
		= new TicksStoppingCriteria<CBIRSolution, CBIRGeneration, CBIRContext>(context, 1000);
	
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
	
	int[] or = new int[16];
	for (int i = 0; i < 16; ++i) or[i] = i;
		
	System.out.println("EV" + evaluator.evaluate(new CBIRSolution(or)));
	System.out.println("EV" + evaluator.evaluate(context.bestSolutionEver));*/
		
		
		//GeoCoordsGraphReader graphReader = new GeoCoordsGraphReader();
		//Graph graph = graphReader.readFromFile("/home/rrhu/workspace/uealib/graphs/burma14.txt");

		//CoordsGraphReader graphReader = new CoordsGraphReader();
		//Graph graph = graphReader.readFromFile("/home/rrhu/workspace/uealib/graphs/berlin52.txt");

		GraphReader graphReader = new UpperRowMatrixGraphReader();
		Graph graph = graphReader.readFromFile("/home/rrhu/workspace/uealib/graphs/bayg29.txt");
		
		taboosearch.permutations.FrequencyMemory<TSPSolution, TSPSwapMove> frequencyMemory
			= new taboosearch.permutations.FrequencyMemory<TSPSolution, TSPSwapMove>(graph, 1500 /* diversificationCoef */);
		
		TSPEvaluator evaluator = new TSPEvaluator(graph, frequencyMemory);
		
		Taboolator<TSPSolution, TSPSwapMove> taboolator
			= new Taboolator<TSPSolution, TSPSwapMove>(new ConstantTenureStrategy(13));

		AbstractGenerationFabric<TSPSolution, TSPGeneration> generationFabric
			= new TSPGenerationFabric();
		
		AdmissibilityChecker<TSPSolution, TSPSwapMove> checker
			= new AdmissibilityChecker<TSPSolution, TSPSwapMove>(evaluator, taboolator);
	
		EliteCandidateList<TSPSolution, TSPSwapMove> eliteList
			= new EliteCandidateList<TSPSolution, TSPSwapMove>(1, checker, evaluator);
		
		TSPContext context
			= new TSPContext(evaluator, taboolator, frequencyMemory, generationFabric, eliteList);
		
		TSPInitializator initializator = new TSPInitializator(graph, evaluator);
		
		taboosearch.permutations.Generator<TSPSolution, TSPSwapMove, TSPGeneration> generator
			= new TSPGenerator(graph);

		TicksStoppingCriteria<TSPSolution, TSPGeneration, TSPContext> stoppingCriteria
			= new TicksStoppingCriteria<TSPSolution, TSPGeneration, TSPContext>(context, 2000);
		
		Selector<TSPSolution, TSPSwapMove, TSPGeneration, TSPContext> selector
			= new Selector<TSPSolution, TSPSwapMove, TSPGeneration, TSPContext>(checker, context);
		
		UnconditionalTransitionCriteria<TSPSolution, TSPGeneration, TSPContext> transitionCriteria
			= new UnconditionalTransitionCriteria<TSPSolution, TSPGeneration, TSPContext>();
		
		TabooSearchAlgorithm<TSPSolution, TSPSwapMove, TSPGeneration, TSPContext> algorithm
			= new TabooSearchAlgorithm<TSPSolution, TSPSwapMove, TSPGeneration, TSPContext>(
				initializator,
				generator,
				stoppingCriteria,
				selector,
				transitionCriteria,
				context);
		algorithm.solve();
		
		//System.out.println(list.size());
		//TSPSalesmanRoute route = new TSPSalesmanRoute(list);
		//TSPSolution exact = new TSPSolution(route, evaluator.evaluate(route));
		//System.out.println(exact.toString());
		//System.out.println(evaluator.evaluate(route));
		
		//(new GUI(context.getSeries())).run();
	}

}
