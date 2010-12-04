package core;

import java.util.List;

/**
 * Используется для хранения некоторого множества (или поколения) решений.
 */
public interface Generation<T extends Solution> extends List<T> {
    /**
     * @return возвращает Generation, содержащее экземпляры с наибольшим значением fitness
     */
    
	public Generation<T> getBest();
}
