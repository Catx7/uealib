package taboosearch;

import java.util.LinkedList;

public class Initializator implements core.Initializator {
	
	private double[][] weights = null;
	private int n = 0;
	
	public Initializator(double[][] weights) {
		assert weights.length == weights[0].length;
		
		this.weights = weights;
		this.n = weights.length;
	}
	
	/**
	 * Жадный алгоритм.
	 */
	private Solution getInitSolutionBeginFrom(int begin) {
		LinkedList<Integer> path = new LinkedList<Integer>();
		int v = begin;
	    int nearest_v = v;
		int w;
		double min;
		
		path.offerLast(v);
		for (int times = 1; times < n; ++times) {
			min = 35565;
			for (w = 0; w < this.n; ++w) {
				if (this.weights[v][w] < min && !path.contains(w)) {
				    min = this.weights[v][w];
				    nearest_v = w;
				}
			}
			path.offerLast(nearest_v);
			v = nearest_v;
		}
		path.offerLast(begin);
		
		int[] result = new int[path.size()];
		for (int i = 0; i < result.length; ++i)
			result[i] = (int)path.get(i);
		
		return new Solution(result);
	}

	public Generation getInitialGeneration() {
		Generation g = new Generation();
		g.add(this.getInitSolutionBeginFrom(0));
		return g;
	}
	
}
