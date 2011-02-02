package taboosearch.tsp;
import readers.Graph;
import taboosearch.Evaluator;

public class TspEvaluator extends Evaluator<TspSolution> {

	private double[][] weights;
	private int n;
	
	public TspEvaluator(Graph graph) {
		this.weights = graph.getWeights();
		this.n = graph.getN();
	}
	
	public double evaluate(TspSalesmanRoute route) {
		assert n == route.length();
		
		double value = 0;
		int v = route.get(0);
		
		for (int i = 1; i < n + 1; ++i) {
			int w = route.get(i % n);
			value += weights[v][w];
			v = w;
		}
		
		return value;
	}
	
	public double evaluate(TspSalesmanRoute route, double routeCost, TspSwapMove move) {
		int i = move.getI(),
			j = move.getJ();
		
		double cost = routeCost;
		int v1 = route.get(i); 
		int v2 = route.get(j);
		int t1 = route.get((i - 1) % n);
		int w1 = route.get((i + 1) % n);
		int t2 = route.get((j + 1) % n);
		int w2 = route.get((j - 1) % n);
		
		if (Math.abs(i - j) == 1) {
			cost += - weights[t1][w2] - weights[t2][w1]
			        + weights[t1][w1] + weights[t2][w2];
		} else {
			cost += - weights[v1][w1] - weights[v2][w2]
			        - weights[v1][t1] - weights[v2][t2]
			        + weights[v1][w2] + weights[v2][w1]
			        + weights[v1][t2] + weights[v2][t1];
		}
		
		return cost;
	}
	
	public double evaluate(TspSolution solution) {
		if (solution.isLazy()) {
			return evaluate(solution.getRoute(),
							solution.getRouteCost(),
							solution.getMove());
		} else {
			return evaluate(solution.getRoute());
		}
	}
	
}