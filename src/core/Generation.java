package core;

import java.util.List;

/**
 * Используется для хранения некоторого множества (или поколения) решений.
 */
public interface Generation<S extends Solution<S>> extends List<S> {
    /**
     * @return возвращает Generation, содержащее экземпляры с наибольшим значением fitness
     */
    
	public Generation<S> getBest();

}
