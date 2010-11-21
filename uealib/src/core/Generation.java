package core;

import java.util.Collection;
/**
 * Используется для хранения некоторого множества (или поколения) решений.
 */
public interface Generation extends Collection<Solution>  {
	/**
	 * @return возвращает Generation, содержащее экземпляры с наибольшим значением fitness
	 */
	public Generation getBest();
}
