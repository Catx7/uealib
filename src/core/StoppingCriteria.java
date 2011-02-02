package core;

/**
 * Критерий остановки работы алгоритма.
 */
public interface StoppingCriteria<T extends Generation<? extends Solution>> {
	/**
	 * На основе полученного g и, может быть, контекста, принимает решение
	 * об остановке алгоритма.
	 */
	
	public boolean isSatisfied(T g);
}
