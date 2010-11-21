package core;

public interface Selector {
	/**
	 * "Процеживает" полученное поколение g, оставляя в нём одно или больше
	 * наилучших решений.
	 */
	public Generation keepTheBestSolutions(Generation g);
}
