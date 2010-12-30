package core;

/**
 * Представляет собой корректное решение задачи.
 */
public abstract class Solution<S extends Solution<S>> {
	
	public abstract S copy();
}
