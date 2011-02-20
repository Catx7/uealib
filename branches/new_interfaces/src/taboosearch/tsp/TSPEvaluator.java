package taboosearch.tsp;
import readers.Graph;
import taboosearch.Evaluator;
import taboosearch.exceptions.NotEvaluatedSolution;
import taboosearch.permutations.FrequencyMemory;
import taboosearch.permutations.Move;
import taboosearch.permutations.Solution;

public class TSPEvaluator<S extends Solution, M extends Move<S>> implements Evaluator<S, M> {
	private double[][] weights;
	private int n;
	FrequencyMemory<S, M> frequencyMemory;
	
	public TSPEvaluator(Graph graph, FrequencyMemory<S, M> frequencyMemory) {
		this.weights = graph.getWeights();
		this.n = graph.getVertexesNumber();
		this.frequencyMemory = frequencyMemory;
	}
	
	public double evaluate(S solution) {
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
	
	public double evaluate(S solution, M move) {
		int i = move.getI();
		int j = move.getJ();
		
		double cost;
		try {
			cost = solution.getCost();
		} catch (NotEvaluatedSolution e) {
			System.err.println(e.getMessage());
			cost = evaluate(solution);
		}

		if (Math.abs(i - j) == 1) {
			int v1 = solution.get(i); 
			int v2 = solution.get(j);
			int t1 = solution.get((i - 1) % n);
			int t2 = solution.get((j + 1) % n);
			cost += - weights[t1][v1] - weights[t2][v2]
			        + weights[t1][v2] + weights[v1][t2];
		} else {
			int v1 = solution.get(i); 
			int v2 = solution.get(j);
			int t1 = solution.get((i - 1) % n);
			int w1 = solution.get((i + 1) % n);
			int t2 = solution.get((j + 1) % n);
			int w2 = solution.get((j - 1) % n);
			cost += - weights[v1][w1] - weights[v2][w2]
			        - weights[v1][t1] - weights[v2][t2]
			        + weights[v1][w2] + weights[v2][w1]
			        + weights[v1][t2] + weights[v2][t1];
		}
		
		return cost;
	}
	
	public double evaluateMove(S solution, M move) {
		double d = frequencyMemory.getPenalty(solution, move);
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