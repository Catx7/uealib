package simulatedannealing.tsp;

import core.Solution;
import readers.Graph;
import simulatedannealing.Evaluator;

public class TSPEvaluator implements Evaluator {

	double[][] weights;
	
	public TSPEvaluator(Graph g) {
		weights = g.getWeights();
	}
	@Override
	public int compare(Solution o1, Solution o2) {
		if(Math.abs(evaluate(o1) - evaluate(o2)) < 1e-10)
			return 0;
		
		if(evaluate(o2) < evaluate(o1))
			return -1;
		return 1;
	}

	@Override
	public double evaluate(Solution s) {
		TSPWay w = (TSPWay)s;
		int[] way = w.getWay();
		double result = 0;
		
		for(int i=0; i<way.length;++i) {
			result += weights[way[i]][way[(i+1)%way.length]];
		}
		
		return result;
	}

}
