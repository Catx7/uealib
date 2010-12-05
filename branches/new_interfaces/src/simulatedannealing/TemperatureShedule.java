package simulatedannealing;

public interface TemperatureShedule {
	double getTemperature();
	void anneal();
}
