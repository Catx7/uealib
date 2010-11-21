package core;

public interface Generator {
    /**
     * Основываясь исключительно на данных о переданном ему поколении g,
     * конструирует новое поколение.
     */
	@SuppressWarnings("unchecked")
	public Generation getNext(Generation g);
}
