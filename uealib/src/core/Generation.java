package core;

/**
 * Используется для хранения некоторого множества (или поколения) решений.
 */
public interface Generation extends Iterable<Solution> { //Collection<Solution>  {
	/**
	 * @return возвращает Generation, содержащее экземпляры с наибольшим значением fitness
	 */
	public Generation getBest();
}
