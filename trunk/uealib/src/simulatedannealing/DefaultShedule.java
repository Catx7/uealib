package simulatedannealing;

import core.Generation;
import core.Solution;

class DefaultShedule implements TemperatureShedule {

	private double T;
	private double c;
	public DefaultShedule(double percentOfDegradation) {
		SimulatedAnnealingContext ctx = SimulatedAnnealingContext.getInstance();
		Generation initg = ctx.getInitializator().getInitialGeneration();
		Solution init = (Solution)initg.get(0);
		double sum = 0;
		
		int count = 100;
		for(int i = 0; i< count ; ++i) {
			Generation nextg = ctx.getGenerator().getNext(initg);
			Solution next = (Solution)nextg.get(0);
			
			double delta = Math.abs(next.getFitness() - init.getFitness());
			sum += delta;			
		}
		
		double avg = sum/count;
		
		T = -avg/Math.log(percentOfDegradation);
		c = 0.98;
	}
	@Override
	public double getTemperature() {
		return T;
	}

	@Override
	public void anneal() {
		T = T*c;
	}

}
