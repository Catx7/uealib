package simulatedannealing;

import core.Solution;

public class TemperatureShedule implements ITemperatureShedule {

	private double T;
	private IAnnealProvider annealProvider;
	
	public TemperatureShedule(IInitialTemperatureProvider temperatureProvider, IAnnealProvider annealProvider) {
		this.annealProvider = annealProvider;
		T = temperatureProvider.getInitialTemperature();
		
	}
	

	@Override
	public double getTemperature() {
		return T;
	}

	@Override
	public void anneal() {
		T = annealProvider.anneal(T);
	}

}
