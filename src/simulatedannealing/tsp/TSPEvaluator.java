package simulatedannealing.tsp;

import core.Solution;
import readers.Graph;
import simulatedannealing.IEvaluator;

public class TSPEvaluator implements IEvaluator<TSPWay> {

	double[][] weights;
	
	public TSPEvaluator(Graph g) {
		weights = g.getWeights();
	}
	@Override
	public int compare(TSPWay o1, TSPWay o2) {
		if(Math.abs(evaluate(o1) - evaluate(o2)) < 1e-10)
			return 0;
		
		if(evaluate(o2) < evaluate(o1))
			return -1;
		return 1;
	}

	@Override
	public double evaluate(TSPWay w) {
		
		if(w.getFitness() != null)
			return w.getFitness();
		
		int[] way = w.getWay();
		double result = 0;
		
		for(int i=0; i<way.length;++i) {
			result += weights[way[i]][way[(i+1)%way.length]];
		}
		
		w.setFitness((new Double(result)));
		return result;
	}

}
