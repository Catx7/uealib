package simulatedannealing.tsp;

import readers.Graph;
import simulatedannealing.IInitializator;
import core.Initializator;
import core.Solution;

public class TSPInitializator implements IInitializator<TSPWay>{

	private int n;
	
	public TSPInitializator(Graph g) {
		this.n = g.getWeights().length;
	}
	
	@Override
	public TSPWay getInitialSolution() {
		int way[] = new int[n];
		for(int i=0; i<n ; ++i) {
			way[i] = i;
		}
		TSPWay s = new TSPWay(way);
		return s;
	}

}
