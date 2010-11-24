package simulatedannealing;

class DefaultShedule implements TemperatureShedule {

	private double T;
	private double c;
	public DefaultShedule() {
		//TODO: Зависит от задачи
		T = 1000;
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
