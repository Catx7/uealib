package taboosearch;

import java.util.HashSet;
import java.util.Iterator;

public class Selector implements core.Selector<LazyGeneration> {

	public LazyGeneration keepTheBestSolutions(LazyGeneration g,
											   LazyGeneration currentGeneration) {
				
		HashSet<LazySolution> notTabooSolutions = new HashSet<LazySolution>();
		
		Taboolator tr = Context.getInstance().tr;
		Iterator<LazySolution> it = g.iterator();
		
		while (it.hasNext()) {
			LazySolution s = it.next();
			if (!tr.isTabu(s)) {
				notTabooSolutions.add(s);
			}
		}
		
		double candidateFitness = Double.MAX_VALUE;
		LazySolution candidate = null;
		
		for (LazySolution m : notTabooSolutions) {
			if (m.getFitness() < candidateFitness) {
				candidateFitness = m.getFitness();
				candidate = m;
			}
		}
		
		tr.setTabu(candidate);
		
		LazyGeneration result = new LazyGeneration();
		result.add(candidate);
		
		return result;
	}

}
