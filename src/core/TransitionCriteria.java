package core;

/**
 * Критерий перехода от поколения к поколению.
 */
public interface TransitionCriteria<T extends Generation<? extends Solution>> {
	/**
	 * На основе полученных поколений g и h, и, может быть, контекста, принимает решение
	 * о переходе от поколения g к поколению h.
	 */
	
	public boolean isSatisfied(T g, T h);
}
