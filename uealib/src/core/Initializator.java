package core;

public interface Initializator {
	/**
	 * @return Generation, содержащее множество начальных решений.
	 */
	@SuppressWarnings("unchecked")
	public Generation getInitialGeneration();
}
