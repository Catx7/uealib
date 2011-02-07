package taboosearch.tsp;

import java.util.LinkedList;
import java.util.List;

import readers.Graph;
import taboosearch.Initializator;

public class TSPInitializator extends Initializator<TSPSolution, TSPSwapMove, TSPGeneration, TSPContext> {
	private double[][] weights;
	private int n = 0;
	TSPContext context;

	public TSPInitializator(Graph graph, TSPContext context) {	
		this.weights = graph.getWeights();
		this.n = graph.getVertexesNumber();
		this.context = context;
	}
	
	private List<Integer> getSolution(int startFrom) {
		LinkedList<Integer> route = new LinkedList<Integer>();
		int v = startFrom,
			nearestV = startFrom;
		route.add(v);
		
		for (int times = 1; times < n; ++times) {
			double min = Double.MAX_VALUE;
			for (int w = 0; w < this.n; ++w) {
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

	public TSPGeneration getInitialGeneration() {
		TSPSolution solution = new TSPSolution(this.getSolution(0));
		TSPGeneration result = new TSPGeneration();
		solution.setCost(context.getEvaluator().evaluate(solution));
		result.add(solution);
		return result;
	}
}