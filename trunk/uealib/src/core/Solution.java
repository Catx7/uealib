package core;

/**
 * Представляет собой корректное решение задачи.
 */
public interface Solution {
	/**
	 * @return значение целевой функции для данного решения
	 */
	double getFitness();
}
