package simulatedannealing;

import core.Solution;

class DefaultShedule implements ITemperatureShedule {

	private double T;
	private double c;
	public DefaultShedule(double percentOfDegradation, SimulatedAnnealingContext ctx, double c) {
		Solution initialSolution = ctx.getInitializator().getInitialSolution();
		IEvaluator e = ctx.getEvaluator();
		double sum = 0;

		int count = 100;
		for (int i = 0; i < count; ++i) {
			Solution nextSolution = ctx.getGenerator().getNext(initialSolution);

			double delta = Math.abs(e.evaluate(nextSolution) - e.evaluate(initialSolution));
			sum += delta;
		}

		double avg = sum / count;

		T = -avg / Math.log(percentOfDegradation);
		this.c = c;
	}
	public DefaultShedule(double percentOfDegradation, SimulatedAnnealingContext ctx) {
		this(percentOfDegradation, ctx, 0.98);
	}
	

	
	
	public void setC(double c) {
		this.c = c;
	}

	@Override
	public double getTemperature() {
		return T;
	}

	@Override
	public void anneal() {
		T = T * c;
	}

}
