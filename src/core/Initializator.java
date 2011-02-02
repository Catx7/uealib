package core;

public interface Initializator<T extends Generation<? extends Solution>> {
	/**
	 * @return Generation, содержащее множество начальных решений.
	 */
	
	public T getInitialGeneration();
}
