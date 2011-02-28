package taboosearch.permutations.tsp;

import java.util.LinkedList;
import java.util.List;

import taboosearch.permutations.AbstractGenerationAndSolutionFabric;
import taboosearch.permutations.Move;
import taboosearch.permutations.Solution;
import taboosearch.Generation;

import readers.Graph;
import taboosearch.Evaluator;
import taboosearch.Initializator;

public class TSPInitializator<S extends Solution, G extends Generation<S>> extends Initializator<S, G> {
	private double[][] weights;
	private int n = 0;
	private Evaluator<S, ? extends Move<S>> evaluator;
	private AbstractGenerationAndSolutionFabric<S, G> fabric;

	public TSPInitializator(
			Graph graph,
			Evaluator<S, ? extends Move<S>> evaluator,
			AbstractGenerationAndSolutionFabric<S, G> fabric) {	
		this.weights = graph.getWeights();
		this.n = graph.getVertexesNumber();
		this.evaluator = evaluator;
		this.fabric = fabric;
	}
	
	private List<Integer> getSolution(int startFrom) {
		LinkedList<Integer> route = new LinkedList<Integer>();
		int v = startFrom,
			nearestV = startFrom;
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
		
		/*
		LinkedList<Integer> numbers = new LinkedList<Integer>();
		for (int i = 0; i < n; ++i)
			numbers.add(i);
		Collections.shuffle(numbers);
		return numbers;
		*/
		
		return route;
	}

	public G getInitialGeneration() {
		return getInitialGeneration(1);
	}
	
	public G getInitialGeneration(int i) {
		assert i < n;
		G result = fabric.makeGeneration();
		S solution = fabric.makeSolution(getSolution(i));
		solution.setCost(evaluator.evaluate(solution));
		result.add(solution);
		return result;
	}

}
