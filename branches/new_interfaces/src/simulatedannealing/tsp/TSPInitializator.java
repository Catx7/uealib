package simulatedannealing.tsp;

import readers.Graph;
import simulatedannealing.GenerationList;
import core.Initializator;
import core.Solution;

public class TSPInitializator implements Initializator<GenerationList>{

	private int n;
	
	public TSPInitializator(Graph g) {
		this.n = g.getWeights().length;
	}
	
	@Override
	public GenerationList getInitialGeneration() {
		int way[] = new int[n];
		for(int i=0; i<n ; ++i) {
			way[i] = i;
		}
		Solution s = new TSPWay(way);
		
		GenerationList result = new GenerationList();
		result.add(s);
		return result;
	}

}
