package taboosearch.knapsack;

import java.util.HashSet;

import taboosearch.Generation;
import taboosearch.Initializator;

public class KPInitializator extends Initializator<KPSolution, Generation<KPSolution>> {

	@Override
	public Generation<KPSolution> getInitialGeneration(int i) {
		return getInitialGeneration();
	}

	@Override
	public Generation<KPSolution> getInitialGeneration() {
		KPSolution s = new KPSolution(new HashSet<Integer>());
		s.setCost(0);
		return new Generation<KPSolution>(s);
	}

}
