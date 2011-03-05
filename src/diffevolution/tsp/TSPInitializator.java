package diffevolution.tsp;

import java.util.LinkedList;
import readers.Graph;
import diffevolution.Initializator;


public class TSPInitializator extends Initializator<TSPSolution, TSPGeneration> {
	private double[][] weights;
	private int n = 0;
		
	private static java.util.Random rand = new java.util.Random();
	
	public static final int DEFAULT_GENERATION_SIZE = 100;
	private int generationSize;

	public TSPInitializator(Graph graph) {	
		this.weights = graph.getWeights();
		this.n = graph.getVertexesNumber();
		this.generationSize = DEFAULT_GENERATION_SIZE;
	}
	
	private TSPSolution getSolution(int startFrom) {
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
		return new TSPSolution(route);
	}

	private TSPSolution getRandomSolution() {
		LinkedList<Integer> route = new LinkedList<Integer>();
		
		for (int k = this.n - 1; k > 0 ; --k) 
			route.add(rand.nextInt(k));
		route.add(0);
		
		return new TSPSolution(route).leh2route();
	}
	
	
	public TSPGeneration getInitialGeneration() {
		
		TSPGeneration result = new TSPGeneration();		
		int inx = 0;
	    while ( inx < this.generationSize && inx < this.n ) {
	    	result.add( getSolution(inx) );
			++inx;
		}		
	    while ( inx < this.generationSize ) {
	    	result.add( getRandomSolution() );
			++inx;
		}
		return result;
	}
	
	public void setGenerationSize(int size) {
		this.generationSize = size;		
	}

}
