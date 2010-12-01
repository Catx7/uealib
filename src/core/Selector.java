package core;

public interface Selector<T extends Generation<? extends Solution>> {
	/**
	 * "Процеживает" полученное поколение g, оставляя в нём одно или больше
	 * наилучших решений.
	 */
	
	public T keepTheBestSolutions(T g, T currentGeneration);
}
