package ts;

import readers.Graph;

public class Context extends core.Context {
		
	private static Context instance;

	/**
	 * Возвращает экземпляр контекста.
	 */
	public static Context getInstance() {
		if (instance == null) {
			instance = new Context();
		}
		return instance;
	}
	
	Evaluator e;
	
}