package simulatedannealing;

import core.Generator;
import core.Initializator;

class SimulatedAnnealingContext implements core.Context {
	private TemperatureShedule shedule;
	private int stabilyzedCount;

	private Initializator<GenerationList> i;
	private Generator<GenerationList> g;
	private Evaluator e;

	public SimulatedAnnealingContext() {
		stabilyzedCount = 0;
	}

	public void initTemperatureShedule() {
		shedule = new DefaultShedule(0.5, this);
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

	public TemperatureShedule getShedule() {
		return shedule;
	}

	public Initializator<GenerationList> getInitializator() {
		return i;
	}

	public void setInitializator(Initializator<GenerationList> i) {
		this.i = i;
	}

	public Generator<GenerationList> getGenerator() {
		return g;
	}

	public void setGenerator(Generator<GenerationList> g) {
		this.g = g;
	}

	public Evaluator getEvaluator() {
		return e;
	}

	public void setEvaluator(Evaluator e) {
		this.e = e;
	}

}
