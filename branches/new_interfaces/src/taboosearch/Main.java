package taboosearch;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import javax.xml.parsers.ParserConfigurationException;

import common.AbstractGenerationFabric;
import common.Fabric;
import common.Pair;
import common.TicksStoppingCriteria;
import common.UnconditionalTransitionCriteria;

import readers.Graph;
import readers.graphs.CoordsGraphReader;
import readers.graphs.GeoCoordsGraphReader;
import readers.graphs.GraphReader;
import readers.graphs.LowerRowMatrixGraphReader;
import readers.graphs.MatrixGraphReader;
import readers.graphs.UpperRowMatrixGraphReader;

import taboosearch.exceptions.NotEvaluatedSolution;
import taboosearch.exceptions.UnsupportedMoveType;
import taboosearch.gui.GUI;
import taboosearch.knapsack.KPContext;
import taboosearch.knapsack.KPEvaluator;
import taboosearch.knapsack.KPGenerator;
import taboosearch.knapsack.KPInitializator;
import taboosearch.knapsack.KPMove;
import taboosearch.knapsack.KPSolution;
import taboosearch.knapsack.KPTaboolator;
import taboosearch.permutations.AbstractGenerationAndSolutionFabric;
import taboosearch.permutations.AbstractMoveFabric;
import taboosearch.permutations.Generator;
import taboosearch.permutations.SwapMove;
import taboosearch.permutations.Solution;
import taboosearch.permutations.FrequencyMemory;
import taboosearch.permutations.Move;
import taboosearch.permutations.Util;
import taboosearch.permutations.tsp.*;
import taboosearch.readers.KnapsackProblem;
import taboosearch.readers.KnapsackProblemReader;
import taboosearch.tenures.ConstantTenureStrategy;
import taboosearch.utils.EvaluatorFabric;


import org.xml.sax.SAXException;

public class Main {
	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException, IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		
		KPContext context = new KPContext();
		
		KnapsackProblemReader r = new KnapsackProblemReader();
		KnapsackProblem task = r.readFromFile("/home/rrhu/knap500.txt");
		
		KPEvaluator evaluator = new KPEvaluator(task);
		KPTaboolator taboolator = new KPTaboolator(new ConstantTenureStrategy(30));
	
		AdmissibilityChecker<KPSolution, KPMove> checker
			= new AdmissibilityChecker<KPSolution, KPMove>(evaluator, taboolator);
		
		EliteCandidateList<KPSolution, KPMove> eliteList
			= new EliteCandidateList<KPSolution, KPMove>(1, checker, evaluator);
		
		KPGenerator generator = new KPGenerator(task);
		
		TicksStoppingCriteria<KPSolution, Generation<KPSolution>, KPContext> stoppingCriteria
			= new TicksStoppingCriteria<KPSolution, Generation<KPSolution>, KPContext>(500);
		
		ArrayList<Tickable<KPSolution, KPMove>> ok = new ArrayList<Tickable<KPSolution, KPMove>>();
		ok.add(taboolator);
		ok.add(eliteList);
		
		taboosearch.knapsack.AbstractGenerationAndSolutionFabric fabric
		= new taboosearch.knapsack.AbstractGenerationAndSolutionFabric() {
			public Generation<KPSolution> makeGeneration() { return new Generation<KPSolution>(); }
			public KPSolution makeSolution(HashSet<Integer> items) { return new KPSolution(items); }
		  };
		
		Selector<KPSolution, KPMove, Generation<KPSolution>, KPContext> selector
			= new Selector<KPSolution, KPMove, Generation<KPSolution>, KPContext>
				(evaluator, checker, eliteList, fabric, ok, context);
		
		KPInitializator initializator = new KPInitializator();
		
		TabooSearchAlgorithm<KPSolution, KPMove, Generation<KPSolution>, KPContext> a
		= new TabooSearchAlgorithm<KPSolution, KPMove, Generation<KPSolution>, KPContext>(
				initializator,
				generator,
				stoppingCriteria,
				selector,
				context);
		try {
			a.solve();
		} catch (UnsupportedMoveType e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotEvaluatedSolution e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		(new GUI(context.getSeries())).run();
		/*Integer okok = new Integer(100);
		String lala = "HUI";
		lala.in
		System.out.println(okok);*/
		/*
		//GeoCoordsGraphReader graphReader = new GeoCoordsGraphReader();
		//Graph graph = graphReader.readFromFile("/home/rrhu/workspace/uealib/graphs/burma14.txt");

		GraphReader graphReader = new MatrixGraphReader();
		Graph graph = graphReader.readFromFile("/home/rrhu/workspace/uealib/graphs/bays29.txt");		

		//GraphReader graphReader = new UpperRowMatrixGraphReader();
		//Graph graph = graphReader.readFromFile("/home/rrhu/workspace/uealib/graphs/gr48.txt");

		FrequencyMemory<Solution, Move<Solution>> frequencyMemory =
			new FrequencyMemory<Solution, Move<Solution>>(graph.getVertexesNumber(), 2000);
		
		TSPEvaluator<Solution, Move<Solution>> evaluator
			= new TSPEvaluator<Solution, Move<Solution>>(graph, frequencyMemory);
		  
		AbstractGenerationAndSolutionFabric<Solution, Generation<Solution>> fabric
			= new AbstractGenerationAndSolutionFabric<Solution, Generation<Solution>>() {
				public Generation<Solution> makeGeneration() { return new Generation<Solution>(); }
				public Solution makeSolution(int[] permutation) { return new Solution(permutation);	}
			  };
			
		TSPInitializator<Solution, Generation<Solution>> initializator
			= new TSPInitializator<Solution, Generation<Solution>>(graph, evaluator, fabric);
		
		AbstractMoveFabric<Solution, Move<Solution>> moveFabric = new AbstractMoveFabric<Solution, Move<Solution>>() {
			public Move<Solution> makeMove(int i, int j) {
				return new SwapMove<Solution>(i, j) {
					protected Solution makeSolution(int[] permutation) {
						return new Solution(permutation);
					}
				};
			}
		};
		*/
		//TabooSearchAlgorithm<Solution, Move<Solution>, Generation<Solution>, Context<Solution, Move<Solution>, Generation<Solution>>>
		//	algorithm = (new Util<Solution, Move<Solution>, Generation<Solution>>()).getAlgorithm(
		//			fabric, moveFabric, initializator, evaluator, frequencyMemory,
		//			graph.getVertexesNumber(), 10 /* tenure */, 1 /* elite list size */, 20000 /* iterations */);
		
		/*try {
			algorithm.solve();
		} catch (UnsupportedMoveType e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		*/
		/*
		EvaluatorFabric evaluatorFabric
			= new EvaluatorFabric(graph, 2000) {
				@SuppressWarnings("unchecked")
				@Override
				public Pair<FrequencyMemory<Solution, Move<Solution>>, Evaluator<Solution, Move<Solution>>> getInstance() {
					FrequencyMemory<Solution, Move<Solution>> frequencyMemory 
						= new FrequencyMemory<Solution, Move<Solution>>(graph.getVertexesNumber(), diversityCoef);
					
					TSPEvaluator<Solution, Move<Solution>> evaluator
						= new TSPEvaluator<Solution, Move<Solution>>(graph, frequencyMemory);
					
					return new Pair(frequencyMemory, evaluator);
				}
			  };
		ParallelTabooSearchAlgorithm<Solution, Move<Solution>, Generation<Solution>, Context<Solution, Move<Solution>, Generation<Solution>>>
			algorithm2 = (new Util<Solution, Move<Solution>, Generation<Solution>>()).getParallelAlgorithm(
					fabric, moveFabric, initializator, evaluatorFabric,
		*///			graph.getVertexesNumber(), 10 /* tenure */, 3 /* elite list size */, 10000 /* iterations */);
		
		//algorithm2.solve();
		
		//(new GUI(context.getSeries())).run();
	}

}
