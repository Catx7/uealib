package core;

public interface Generator {
    /**
     * Основываясь на данных о переданном ему поколении g и, может быть, контексте,
     * конструирует новое поколение.
     */
	@SuppressWarnings("unchecked")
	public<T extends Solution> Generation getNext(Generation g);
}
