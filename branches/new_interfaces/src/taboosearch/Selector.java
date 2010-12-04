package taboosearch;

import java.util.HashSet;
import java.util.Iterator;

public class Selector implements core.Selector {

	@SuppressWarnings("unchecked")
	public Generation keepTheBestSolutions(core.Generation g,
										   core.Generation currentGeneration) {
				
		HashSet<SolutionDiff> notTabooSolutions = new HashSet<SolutionDiff>();
		
		Taboolator tr = Context.getInstance().tr;
		Iterator<SolutionDiff> it = g.iterator();
		
		while (it.hasNext()) {
			SolutionDiff s = it.next();
			if (!tr.isTabu(s)) {
				notTabooSolutions.add(s);
			}
		}
		
		double candidateFitness = Double.MAX_VALUE;
		SolutionDiff candidate = null;
		
		for (SolutionDiff m : notTabooSolutions) {
			if (m.getFitness() < candidateFitness) {
				candidateFitness = m.getFitness();
				candidate = m;
			}
		}
		
		tr.setTabu(candidate);
		
		Generation result = new Generation();
		result.add(candidate.castToSolution());
		
		return result;
	}

}
