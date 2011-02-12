package taboosearch.cbir;

import java.util.Collections;
import java.util.LinkedList;

import taboosearch.Initializator;
import taboosearch.readers.FeaturesSpace;

public class CBIRInitializator extends Initializator<CBIRSolution, CBIRSwapMove, CBIRGeneration, CBIRContext> {
	private int n = 0;
	CBIRContext context;

	public CBIRInitializator(FeaturesSpace space, CBIRContext context) {	
		this.n = space.n;
		this.context = context;
	}

	public CBIRGeneration getInitialGeneration() {
		CBIRGeneration result = new CBIRGeneration();
		
		LinkedList<Integer> ll = new LinkedList<Integer>();
		//int[] order = new int[n]; //12
		//Collections.shuffle();
		for (int i = 0; i < n; ++i) ll.add(i); //order[i] = i;
		Collections.shuffle(ll);
		
		int[] order = new int[ll.size()];
		for (int i = 0; i < ll.size(); ++i) {
			order[i] = ll.get(i);	
		}
			
		CBIRSolution solution = new CBIRSolution(order);
		solution.setCost(context.getEvaluator().evaluate(solution));
		result.add(solution);
		return result;
	}
}
