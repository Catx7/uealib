package simulatedannealing;

import core.Generator;
import core.Initializator;


class SimulatedAnnealingContext extends core.Context{
	private static SimulatedAnnealingContext instance;
	private TemperatureShedule shedule;
	private int stabilyzedCount;
	
	private core.Initializator i;
	private core.Generator g;
	
	/**
	 * Возвращает экземпляр контекста.
	 */
	public static SimulatedAnnealingContext getInstance() {
		if (instance == null) {
			instance = new SimulatedAnnealingContext();
		}
		return instance;
	}
	
	private SimulatedAnnealingContext() {
		shedule = new DefaultShedule(0.5);
		stabilyzedCount = 0;
	}
	
	public int getCount() {
		return stabilyzedCount;
	}
	
	public TemperatureShedule getShedule() {
		return shedule;
	}
	
	public Initializator getInitializator() {
		return i;
	}
	
	public Generator getGenerator() {
		return g;
	}

}
