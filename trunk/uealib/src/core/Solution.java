package core;

/**
 * Представляет собой корректное решение задачи.
 */
public interface Solution extends Comparable<Solution> {
	/**
	 * @return значение целевой функции для данного решения
	 */
	double getFitness();
}
