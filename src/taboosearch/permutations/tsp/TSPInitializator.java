package taboosearch.permutations.tsp;

import java.util.LinkedList;
import java.util.List;

import taboosearch.permutations.AbstractSolutionFabric;
import taboosearch.permutations.Move;
import taboosearch.permutations.Solution;

import readers.Graph;
import taboosearch.Evaluator;
import taboosearch.Initializator;

public class TSPInitializator<S extends Solution> extends Initializator<S> {
	private int n;
	private double[][] weights;
	private Evaluator<S, ? extends Move<S>> evaluator;
	private AbstractSolutionFabric<S> fabric;

	public TSPInitializator(Graph graph, Evaluator<S, ? extends Move<S>> evaluator, AbstractSolutionFabric<S> fabric) {	
		this.n = graph.getVertexesNumber();
		this.weights = graph.getWeights();
		this.evaluator = evaluator;
		this.fabric = fabric;
	}
	
	private List<Integer> getSolution(int startFrom) {
		LinkedList<Integer> route = new LinkedList<Integer>();
		int v = startFrom, nearestV = startFrom;
		route.add(v);
		
		for (int times = 1; times < n; ++times) {
			double min = Double.MAX_VALUE;
			for (int w = 0; w < n; ++w) {
				if (this.weights[v][w] < min && !route.contains(w)) {
				    min = this.weights[v][w];
				    nearestV = w;
				}
			}
			route.add(nearestV);
			v = nearestV;
		}
				
		return route;
	}

	public S getInitialSolution(int seed) {
		assert seed < n;
		/* seed is the vertex number, from which greedy search starts */
		S solution = fabric.makeSolution(getSolution(seed));
		solution.setCost(evaluator.evaluate(solution));
		return solution;
	}
}
