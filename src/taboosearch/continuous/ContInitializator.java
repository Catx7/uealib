package taboosearch.continuous;

import taboosearch.Initializator;

public class ContInitializator extends Initializator<ContSolution> {
	@Override
	public ContSolution getInitialSolution(int seed) {
		double[] c = {-5.0, 5.0};
		ContSolution s = new ContSolution(c);
		s.setCost(0);
		return s;
	}
}
