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
import common.alternative.TicksStoppingCriteria;
import common.UnconditionalTransitionCriteria;

import readers.Graph;
import readers.graphs.CoordsGraphReader;
import readers.graphs.GeoCoordsGraphReader;
import readers.graphs.GraphReader;
import readers.graphs.LowerRowMatrixGraphReader;
import readers.graphs.MatrixGraphReader;
import readers.graphs.UpperRowMatrixGraphReader;

import taboosearch.continuous.ContEvaluator;
import taboosearch.continuous.ContGenerator;
import taboosearch.continuous.ContInitializator;
import taboosearch.continuous.ContMove;
import taboosearch.continuous.ContSolution;
import taboosearch.continuous.ContTaboolator;
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
import taboosearch.permutations.AbstractMoveFabric;
import taboosearch.permutations.AbstractSolutionFabric;
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

import com.sun.org.apache.xpath.internal.operations.Mod;

public class Main {

	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException, IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		Context<ContSolution, ContMove> context = new Context<ContSolution, ContMove>();
				
		ContEvaluator evaluator = new ContEvaluator();
		ContTaboolator taboolator = new ContTaboolator(new ConstantTenureStrategy(10));
	
		AdmissibilityChecker<ContSolution, ContMove> checker
			= new AdmissibilityChecker<ContSolution, ContMove>(evaluator, taboolator);
		
		EliteCandidateList<ContSolution, ContMove> eliteList
			= new EliteCandidateList<ContSolution, ContMove>(1, checker, evaluator);
		
		ContGenerator generator = new ContGenerator(checker);
		
		TicksStoppingCriteria<ContSolution, Context<ContSolution, ContMove>> stoppingCriteria
			= new TicksStoppingCriteria<ContSolution, Context<ContSolution, ContMove>>(2000);
		
		ArrayList<Tickable<ContSolution, ContMove>> tickables = new ArrayList<Tickable<ContSolution, ContMove>>();
		tickables.add(taboolator);
		tickables.add(eliteList);
		
		Selector<ContSolution, ContMove, Context<ContSolution, ContMove>> selector
			= new Selector<ContSolution, ContMove, Context<ContSolution, ContMove>>
				(evaluator, checker, eliteList, tickables, context);
		
		ContInitializator initializator = new ContInitializator();
		
		TabooSearchAlgorithm<ContSolution, ContMove, Context<ContSolution, ContMove>> a
		= new TabooSearchAlgorithm<ContSolution, ContMove, Context<ContSolution, ContMove>>(
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
		
		// (new GUI(context.getSeries())).run();
		
		
		/*
		KPContext context = new KPContext();
		KnapsackProblemReader r = new KnapsackProblemReader();
		KnapsackProblem task = r.readFromFile("/home/rrhu/workspace/uealib/knapsacks/2000.txt");
		
		KPEvaluator evaluator = new KPEvaluator(task);
		KPTaboolator taboolator = new KPTaboolator(new ConstantTenureStrategy(30));
	
		AdmissibilityChecker<KPSolution, KPMove> checker
			= new AdmissibilityChecker<KPSolution, KPMove>(evaluator, taboolator);
		
		EliteCandidateList<KPSolution, KPMove> eliteList
			= new EliteCandidateList<KPSolution, KPMove>(1, checker, evaluator);
		
		KPGenerator generator = new KPGenerator(task);
		
		TicksStoppingCriteria<KPSolution, KPContext> stoppingCriteria
			= new TicksStoppingCriteria<KPSolution, KPContext>(1500);
		
		ArrayList<Tickable<KPSolution, KPMove>> ok = new ArrayList<Tickable<KPSolution, KPMove>>();
		ok.add(taboolator);
		ok.add(eliteList);
		
		Selector<KPSolution, KPMove, KPContext> selector
			= new Selector<KPSolution, KPMove, KPContext>
				(evaluator, checker, eliteList, ok, context);
		
		KPInitializator initializator = new KPInitializator();
		
		TabooSearchAlgorithm<KPSolution, KPMove, KPContext> a
		= new TabooSearchAlgorithm<KPSolution, KPMove, KPContext>(
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
		}*/
		//(new GUI(context.getSeries())).run();
		
		//GeoCoordsGraphReader graphReader = new GeoCoordsGraphReader();
		//Graph graph = graphReader.readFromFile("/home/rrhu/workspace/uealib/graphs/burma14.txt");

		//GraphReader graphReader = new MatrixGraphReader();
		//Graph graph = graphReader.readFromFile("/home/rrhu/workspace/uealib/graphs/bays29.txt");		

		/*GraphReader graphReader = new CoordsGraphReader();
		Graph graph = graphReader.readFromFile("/home/rrhu/workspace/uealib/graphs/pr76.txt");

		FrequencyMemory<Solution, Move<Solution>> frequencyMemory =
			new FrequencyMemory<Solution, Move<Solution>>(graph.getVertexesNumber(), 15000);
		
		TSPEvaluator<Solution, Move<Solution>> evaluator
			= new TSPEvaluator<Solution, Move<Solution>>(graph, frequencyMemory);
		  
		AbstractSolutionFabric<Solution> fabric
			= new AbstractSolutionFabric<Solution>() {
				public Solution makeSolution(int[] permutation) { return new Solution(permutation);	}
			  };
			
		TSPInitializator<Solution> initializator
			= new TSPInitializator<Solution>(graph, evaluator, fabric);
		
		AbstractMoveFabric<Solution, Move<Solution>> moveFabric = new AbstractMoveFabric<Solution, Move<Solution>>() {
			public Move<Solution> makeMove(int i, int j) {
				return new SwapMove<Solution>(i, j) {
					protected Solution makeSolution(int[] permutation) {
						return new Solution(permutation);
					}
				};
			}
		};
		
		TabooSearchAlgorithm<Solution, Move<Solution>, Context<Solution, Move<Solution>>>
			algorithm = (new Util<Solution, Move<Solution>>()).getAlgorithm(
					moveFabric, initializator, evaluator, frequencyMemory,*/
					//graph.getVertexesNumber(), 12 /* tenure */, 20 /* elite list size */, 700000 /* iterations */);
		
		/*try {
			algorithm.solve();
		} catch (UnsupportedMoveType e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		} catch (NotEvaluatedSolution e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		/*EvaluatorFabric evaluatorFabric
			= new EvaluatorFabric(graph, 150000) {
				@SuppressWarnings("unchecked")
				@Override
				public Pair<FrequencyMemory<Solution, Move<Solution>>, Evaluator<Solution, Move<Solution>>> make() {
					FrequencyMemory<Solution, Move<Solution>> frequencyMemory 
						= new FrequencyMemory<Solution, Move<Solution>>(graph.getVertexesNumber(), diversityCoef);
					
					TSPEvaluator<Solution, Move<Solution>> evaluator
						= new TSPEvaluator<Solution, Move<Solution>>(graph, frequencyMemory);
					
					return new Pair(frequencyMemory, evaluator);
				}
			  };
		ParallelTabooSearchAlgorithm<Solution, Move<Solution>, Context<Solution, Move<Solution>>>
			algorithm2 = (new Util<Solution, Move<Solution>>()).getParallelAlgorithm(
					moveFabric, initializator, evaluatorFabric,*/
					//graph.getVertexesNumber(), 17 /* tenure */, 5 /* elite list size */, 5000000 /* iterations */);
		
	//	algorithm2.solve();
		
		//(new GUI(context.getSeries())).run();
	}

}
