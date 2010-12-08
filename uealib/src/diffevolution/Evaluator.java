package diffevolution;

//import diffevolution.Solution;

public class Evaluator {

	double[][] weights;
	int n;
	
	public Evaluator(double[][] graph) {
		this.weights = graph;
		this.n = graph.length;
	}
	
	public double evaluate(Solution s) {
		double value = 0;
		int v = s.get(0);
		int w;
		for ( int i = 1; i < n; ++i ) {
			w = s.get(i);
			value += weights[v][w];
			v = w;
		}
		value += weights[n-1][0];
		return value;
	}
}
	