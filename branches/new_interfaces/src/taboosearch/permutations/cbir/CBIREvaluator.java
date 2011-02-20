package taboosearch.permutations.cbir;

import taboosearch.Evaluator;
import taboosearch.exceptions.NotEvaluatedSolution;
import taboosearch.readers.Feature;
import taboosearch.readers.FeaturesSpace;

public class CBIREvaluator implements Evaluator<CBIRSolution, CBIRSwapMove> {

	public double[][] d;
	public Feature[][] lambdas;
	private int n;
	
	public CBIREvaluator(FeaturesSpace space) {
		this.d = space.d;
		this.lambdas = space.lambdas;
		this.n = space.d.length;
	}
	
	
	public double evaluate(CBIRSolution solution) {
		assert n == solution.length();
		
		double p = 0;
		int v = solution.get(0);
		
		for (int i = 1; i < n + 1; ++i) {
			int w = solution.get(i % n);
			p += Math.pow(d[v][w], 2);
			v = w;
		}
		p /= n;
		
		double k = 0;
		
		for (int i = 0; i < n; ++i) {
			int w1 = solution.get(i % n);
			int w2 = solution.get((i + 1) % n);
			int w3 = solution.get((i + 2) % n);
			
			k += Math.pow(lambdas[w1][w2].sub(lambdas[w2][w3]).norm(), 2);
		}
		k /= n;
		
		double r = 0;
		/*
		for (int i = 0; i < n; ++i) {
			int i_ = solution.get(i % n); // i
			int j_ = solution.get((i + 1) % n); // j
			int k_ = solution.get((i + 2) % n); // k
			int l_ = solution.get((i + 3) % n); // l
			//(i, j - j, k) - (j, k - k, l)
			
			Feature first = lambdas[i_][j_].sub(lambdas[j_][k_]);
			Feature second = lambdas[j_][k_].sub(lambdas[k_][l_]);
			r += Math.pow(first.sub(second).norm(), 2);
		}
		r /= n;
		*/
		return p + k + r;
	}
	
	public double evaluate(CBIRSolution solution, CBIRSwapMove move) {
		return evaluate(move.operateOn(solution));
	}
	
	public double evaluateMove(CBIRSolution solution, CBIRSwapMove move) {
		double cost;
		try {
			cost = solution.getCost();
		} catch (NotEvaluatedSolution e) {
			System.out.println(e.getMessage());
			cost = evaluate(solution);
		}
		return evaluate(solution, move) - cost;
	}
	
}