package simulatedannealing;


class SimulatedAnnealingContext extends core.Context{
	private static SimulatedAnnealingContext instance;
	private TemperatureShedule shedule;
	private int stabilyzedCount;
	
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
		shedule = new DefaultShedule();
		stabilyzedCount = 0;
	}
	
	public int getCount() {
		return stabilyzedCount;
	}
	
	public TemperatureShedule getShedule() {
		return shedule;
	}

}
