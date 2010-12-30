package core;

public interface Generator <T extends Generation<? extends Solution<?>>> {
    /**
     * Основываясь на данных о переданном ему поколении g и, может быть, контексте,
     * конструирует новое поколение.
     */
	public T getNext(T g);
}
