package taboosearch;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import common.TicksStoppingCriteria;
import common.UnconditionalTransitionCriteria;

import readers.Graph;
import readers.graphs.CoordsGraphReader;
import readers.graphs.GeoCoordsGraphReader;

import taboosearch.gui.GUI;
import taboosearch.tenures.ConstantTenureStrategy;
import taboosearch.tsp.*;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		//List<Integer> list = new LinkedList<Integer>();
		//FileInputStream fi = new FileInputStream("/home/rrhu/workspace/uealib/graphs/berlin52.opt.txt");
		//Scanner s = new Scanner(fi, "utf-8");
		//for (int times = 0; times < 52; ++times) list.add(s.nextInt() - 1);
		//s.close();
		
		GeoCoordsGraphReader graphReader = new GeoCoordsGraphReader();
		Graph graph = graphReader.readFromFile("/home/rrhu/workspace/uealib/graphs/burma14.txt");

		//CoordsGraphReader graphReader = new CoordsGraphReader();
		//Graph graph = graphReader.readFromFile("/home/rrhu/workspace/uealib/graphs/berlin52.txt");

		TSPEvaluator evaluator = new TSPEvaluator(graph);
		TSPTaboolator taboolator = new TSPTaboolator(new ConstantTenureStrategy(6));
		TSPFrequencyMemory frequencyMemory = new TSPFrequencyMemory(graph);
		
		TSPContext context = new TSPContext(evaluator, taboolator, frequencyMemory);
		
		TSPInitializator initializator = new TSPInitializator(graph, context);
		TSPGenerator generator = new TSPGenerator(graph, context);
		
		TSPAdmissibilityChecker checker = new TSPAdmissibilityChecker(evaluator, taboolator, frequencyMemory);
		
		TicksStoppingCriteria<TSPSolution, TSPGeneration, TSPContext> stoppingCriteria
			= new TicksStoppingCriteria<TSPSolution, TSPGeneration, TSPContext>(context, 1000);
		
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
		
		/*System.out.println(list.size());
		TSPSalesmanRoute route = new TSPSalesmanRoute(list);
		TSPSolution exact = new TSPSolution(route, evaluator.evaluate(route));
		System.out.println(exact.toString());
		System.out.println(evaluator.evaluate(route));
		*/
		(new GUI(context.getSeries())).run();
	}

}
