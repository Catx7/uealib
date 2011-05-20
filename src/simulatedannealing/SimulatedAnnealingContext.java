package simulatedannealing;

import java.util.ArrayList;


public class SimulatedAnnealingContext implements core.Context {
	private int stabilyzedCount;

	
	public ArrayList<Double> statistic; 

	public SimulatedAnnealingContext() {
		statistic = new ArrayList<Double>();
		stabilyzedCount = 0;
	}

	public int getCount() {
		return stabilyzedCount;
	}

	public void incCount() {
		stabilyzedCount++;
	}

	public void countToZero() {
		stabilyzedCount = 0;
	}
}
