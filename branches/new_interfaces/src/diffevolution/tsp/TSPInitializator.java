package diffevolution.tsp;

import java.util.LinkedList;
import java.util.List;

import readers.Graph;
import diffevolution.Initializator;


public class TSPInitializator extends Initializator<TSPSolution, TSPGeneration> {
	private double[][] weights;
	private int n = 0;
	
	private static java.util.Random rand = new java.util.Random();

	public TSPInitializator(Graph graph) {	
		this.weights = graph.getWeights();
		this.n = graph.getVertexesNumber();
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

	private TSPSolution getRandomSolution() {
		LinkedList<Integer> route = new LinkedList<Integer>();
		
		for (int k = this.n - 1; k > 0 ; --k) 
			route.add(rand.nextInt(k));
		route.add(0);
		
		return new TSPSolution(route).leh2route();
	}
	
	
	public TSPGeneration getInitialGeneration() {
		int generationSize = 10;
		TSPGeneration result = new TSPGeneration();
		int inx = 0;
		while ( inx < generationSize ) {
			result.add(getRandomSolution());
			++inx;
		}
		return result;
	}
}
