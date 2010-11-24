package core;

public interface Generator {
    /**
     * Основываясь на данных о переданном ему поколении g и, может быть, контекста,
     * конструирует новое поколение.
     */
	@SuppressWarnings("unchecked")
	public Generation getNext(Generation g);
}
