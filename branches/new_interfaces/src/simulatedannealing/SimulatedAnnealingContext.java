package simulatedannealing;

import core.Solution;

class SimulatedAnnealingContext <T extends Solution> implements core.Context {
	private ITemperatureShedule shedule;
	private int stabilyzedCount;

	private IInitializator<T> i;
	private IGenerator<T> g;
	private IEvaluator e;

	public SimulatedAnnealingContext() {
		stabilyzedCount = 0;
	}

	public void initTemperatureShedule() {
		initTemperatureShedule(0.5);
	}
	
	public void initTemperatureShedule(double c) {
		shedule = new DefaultShedule(0.5, this, c);
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

	public ITemperatureShedule getShedule() {
		return shedule;
	}

	public IInitializator<T> getInitializator() {
		return i;
	}

	public void setInitializator(IInitializator<T> i) {
		this.i = i;
	}

	public IGenerator<T> getGenerator() {
		return g;
	}

	public void setGenerator(IGenerator<T> g) {
		this.g = g;
	}

	public IEvaluator getEvaluator() {
		return e;
	}

	public void setEvaluator(IEvaluator e) {
		this.e = e;
	}

}
