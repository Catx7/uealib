package taboosearch.tsp;
import readers.Graph;
import taboosearch.Evaluator;
import taboosearch.exceptions.NotEvaluatedSolution;
import taboosearch.permutations.FrequencyMemory;
import taboosearch.permutations.Move;
public class TSPEvaluator extends Evaluator<TSPSolution, TSPSwapMove> {

	private double[][] weights;
	private int n;
	FrequencyMemory<TSPSolution, TSPSwapMove> frequencyMemory;
	
	public TSPEvaluator(Graph graph, FrequencyMemory<TSPSolution, TSPSwapMove> frequencyMemory) {
		this.weights = graph.getWeights();
		this.n = graph.getVertexesNumber();
		this.frequencyMemory = frequencyMemory;
	}
	
	public double evaluate(TSPSolution solution) {
		assert n == solution.length();
		
		double value = 0;
		int v = solution.get(0);
		
		for (int i = 1; i < n + 1; ++i) {
			int w = solution.get(i % n);
			value += weights[v][w];
			v = w;
		}
		
		return value;
	}
	
	public double evaluate(TSPSolution solution, TSPSwapMove move) {
		int i = move.getI(),
			j = move.getJ();
		
		double cost;
		try {
			cost = solution.getCost();
		} catch (Exception e) {
			cost = evaluate(solution);
		}
		int v1 = solution.get(i); 
		int v2 = solution.get(j);
		int t1 = solution.get((i - 1) % n);
		int w1 = solution.get((i + 1) % n);
		int t2 = solution.get((j + 1) % n);
		int w2 = solution.get((j - 1) % n);
		
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
	
	public double evaluateMove(TSPSolution solution, TSPSwapMove move) {
		double d = frequencyMemory.getPenalty(solution, move);
		//System.out.println(d);
		double cost;
		try {
			cost = solution.getCost();
		} catch (NotEvaluatedSolution e) {
			System.err.println(e.getMessage());
			cost = evaluate(solution);
		}
		return evaluate(solution, move) - cost + d;
	}
	
}