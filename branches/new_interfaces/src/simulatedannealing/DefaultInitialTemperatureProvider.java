package simulatedannealing;

import core.Solution;

public class DefaultInitialTemperatureProvider implements IInitialTemperatureProvider {

	private IInitializator<Solution> initializator; 
    private IGenerator<Solution> generator; 
    private IEvaluator<Solution> evaluator; 
    private double percentOfDegradation;
	
	public DefaultInitialTemperatureProvider(IInitializator initializator, 
										     IGenerator generator, 
										     IEvaluator evaluator ) {
		
		this.initializator = initializator;
		this.generator = generator;
		this.evaluator = evaluator;
		percentOfDegradation = 0.5;
	}
	
	@Override
	public double getInitialTemperature() {
		Solution initialSolution = initializator.getInitialSolution();
		double sum = 0;

		int count = 100;
		for (int i = 0; i < count; ++i) {
			Solution nextSolution = generator.getNext(initialSolution);

			double delta = Math.abs(evaluator.evaluate(nextSolution) - evaluator.evaluate(initialSolution));
			sum += delta;
		}

		double avg = sum / count;

		double result =  -avg / Math.log(percentOfDegradation);
		return result;
	}
	
}
