package taboosearch;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

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

import taboosearch.permutations.AbstractGenerationAndSolutionFabric;
import taboosearch.permutations.AbstractMoveFabric;
import taboosearch.permutations.SwapMove;
import taboosearch.permutations.Solution;
import taboosearch.permutations.FrequencyMemory;
import taboosearch.permutations.Move;
import taboosearch.permutations.Util;
import taboosearch.permutations.tsp.*;
import taboosearch.utils.EvaluatorFabric;


import org.xml.sax.SAXException;

public class Main {
	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException, IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {	
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
				public Solution makeSolution(List<Integer> permutation) { return new Solution(permutation);	}
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
					graph.getVertexesNumber(), 10 /* tenure */, 3 /* elite list size */, 10000 /* iterations */);
		
		algorithm2.solve();

		//(new GUI(context.getSeries())).run();
	}

}
