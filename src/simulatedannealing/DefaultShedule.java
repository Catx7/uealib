package simulatedannealing;

import core.Solution;

class DefaultShedule implements TemperatureShedule {

	private double T;
	private double c;

	public DefaultShedule(double percentOfDegradation, SimulatedAnnealingContext ctx) {
		GenerationList initg = ctx.getInitializator().getInitialGeneration();
		Evaluator e = ctx.getEvaluator();
		Solution init = initg.get(0);
		double sum = 0;

		int count = 100;
		for (int i = 0; i < count; ++i) {
			GenerationList nextg = ctx.getGenerator().getNext(initg);
			Solution next = nextg.get(0);

			double delta = Math.abs(e.evaluate(next) - e.evaluate(init));
			sum += delta;
		}

		double avg = sum / count;

		T = -avg / Math.log(percentOfDegradation);
		c = 0.98;
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
