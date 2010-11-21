package core;

public interface Generator {
    /**
     * Основываясь исключительно на данных о переданном ему поколении g,
     * конструирует новое поколение.
     */
	public Generation getNext(Generation g);
}
