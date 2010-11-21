import core.Context;

public class ExampleContext extends Context {
		
	private static ExampleContext instance;

	/**
	 * Возвращает экземпляр контекста.
	 */
	public static ExampleContext getInstance() {
		if (instance == null) {
			instance = new ExampleContext();
		}
		return instance;
	}
	
	/*
	 * Код контекста.
	 */
	
}
