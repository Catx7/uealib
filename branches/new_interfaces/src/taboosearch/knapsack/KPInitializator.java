package taboosearch.knapsack;

import java.util.HashSet;
import taboosearch.Initializator;

public class KPInitializator extends Initializator<KPSolution> {

	@Override
	public KPSolution getInitialSolution(int seed) {
		KPSolution s = new KPSolution(new HashSet<Integer>());
		s.setCost(0);
		return s;
	}

}
