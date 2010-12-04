package taboosearch;

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
		for ( int i = 1; i < n + 1; ++i ) {
			w = s.get(i);
			value += weights[v][w];
			v = w;
		}
		return value;
	}
	
	public double evaluate(LazySolution s) {
		Solution solution = s.getSolution();
		Move move = s.getMove();
		int i = move.getI();
		int j = move.getJ();
		
		double fitness = solution.getFitness();
		int v1 = solution.get(i); 
		int v2 = solution.get(j);
		int t1 = solution.get(i - 1);
		int w1 = solution.get(i + 1);
		int t2 = solution.get(j + 1);
		int w2 = solution.get(j - 1);
		
		if (Math.abs(i - j) == 1) {
			fitness += - weights[t1][w2] - weights[t2][w1]
			           + weights[t1][w1] + weights[t2][w2];
		} else {
			fitness += - weights[v1][w1] - weights[v2][w2]
			           - weights[v1][t1] - weights[v2][t2]
			           + weights[v1][w2] + weights[v2][w1]
			           + weights[v1][t2] + weights[v2][t1];
		}
		return fitness;
	}
	
}