package core;

import java.util.Collection;
/**
 * Используется для хранения некоторого множества (или поколения) решений.
 */
public interface Generation<T extends Solution> extends Collection<T>  {
	/**
	 * @return возвращает Generation, содержащее экземпляры с наибольшим значением fitness
	 */
	public Generation<T> getBest();
}
