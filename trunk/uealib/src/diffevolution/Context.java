package diffevolution;

public class Context extends core.Context {
	
	private static Context instance;
	
	public static int Gn = 10;
	public static double F;
	public static double Cr = 0.8;
	private int count = 5000;
	
	Evaluator e;
	/**
	 * Возвращает экземпляр контекста.
	 */
	public static Context getInstance() {
		if (instance == null) {
			instance = new Context();
		}
		return instance;
	}
	
	public void setGn (int n) {
		 Context.Gn = n;
		 return; 
	}
	
	public int getCount() {
		 return count; 
	}
	
	public void setCount(int n) {
		this.count = n;
		return;
	} 

}