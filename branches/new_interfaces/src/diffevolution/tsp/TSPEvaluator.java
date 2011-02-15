package diffevolution.tsp;

import readers.Graph;
import diffevolution.Evaluator;
import diffevolution.tsp.TSPSolution;

public class TSPEvaluator extends Evaluator<TSPSolution> {

	private double[][] weights;
	private int n;
	
	public TSPEvaluator(Graph graph) {
		this.weights = graph.getWeights();
		this.n = graph.getVertexesNumber();
	}
	
	public double evaluate(TSPSolution solution) {
		assert n == solution.length();
		
		double value = 0;
		int v = solution.get(0);
		
		int w;
		for ( int i = 1; i < n; ++i ) {
			w = solution.get(i);
			value += weights[v][w];
			v = w;
		}
		value += weights[n-1][0];
		
		return value;
	}
}
