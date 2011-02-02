package taboosearch.tsp;

import java.util.LinkedList;
import java.util.List;

import readers.Graph;
import taboosearch.Initializator;

public class TSPInitializator extends Initializator<TSPSolution, TSPGeneration> {
	private double[][] weights;
	private int n = 0;
	private TSPContext context;
	
	public TSPInitializator(Graph graph, TSPContext context) {	
		this.weights = graph.getWeights();
		this.n = graph.getN();
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
		TSPSalesmanRoute route = new TSPSalesmanRoute(this.getSolution(0));
		double routeCost	   = context.getEvaluator().evaluate(route);
		TSPSolution s 		   = new TSPSolution(route, routeCost);
		
		TSPGeneration result = new TSPGeneration();
		result.add(s);
		return result;
	}
}
