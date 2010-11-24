package taboosearch;

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
	
	private int ticks = 0;
	
	public void tick() {
		this.ticks++;
		tr.tick();
	}
	
	public int getTicks() {
		return ticks;
	}
	
	Evaluator e;
	Taboolator tr;
	
}