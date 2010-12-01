package core;

/**
 * Представляет собой корректное решение задачи.
 */
public abstract class Solution implements Comparable<Solution> {
	/**
	 * @return значение целевой функции для данного решения
	 */
	public abstract double getFitness();
	
	public int compareTo(Solution that) {
		return Double.compare(this.getFitness(), that.getFitness());
	}	
}
