package taboosearch;

public class Evaluator {

	int[][] weights;
	int n;
	
	public Evaluator(int[][] graph) {
		this.weights = graph;
		this.n = graph.length;
	}
	
	public int evaluate(Solution s) {
		int value = 0;
		int v = s.get(0);
		int w;
		for ( int i = 1; i < n + 1; ++i ) {
			w = s.get(i);
			value += weights[v][w];
			v = w;
		}
		return value;
	}
	
}