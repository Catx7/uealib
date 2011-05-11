package simulatedannealing.tsp;

import java.util.HashSet;
import java.util.Iterator;

import readers.Graph;
import simulatedannealing.IInitializator;
import core.Initializator;
import core.Solution;

public class TSPGreedyInitializator implements IInitializator<TSPWay>{

	private int n;
	private double[][] w;
	
	public TSPGreedyInitializator(Graph g) {
		this.n = g.getWeights().length;
		this.w = g.getWeights();
	}
	
	@Override
	public TSPWay getInitialSolution() {
		
		int prev[] = new int[n];
		for(int i=0; i<prev.length; ++i)
			prev[i] = -1;
		
		HashSet<Integer> currentSet = new HashSet<Integer>();
		
		currentSet.add(0);
		prev[0] = 0;
		for(int cur = 1; cur < n; ++cur) {
			double distance = Double.MAX_VALUE;
			int previous = -1;
			int next = -1;
			
			for (Integer v: currentSet) {
				if(w[cur][v] < distance)
				{
					distance = w[cur][v];
					previous = prev[v];
					next = v;
				}
			}
			
			currentSet.add(cur);
			prev[next] = cur;
			prev[cur] = previous;
		}
		int way[] = new int[n];
		
		for(int i=0, p=0; i<n;++i) {
			way[i] = p;
			p  = prev[p];
		}
		
		
		TSPWay s = new TSPWay(way);
		return s;
	}

}
