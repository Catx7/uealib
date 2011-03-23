package antcolony.fabric;



public abstract class InitializatorFabric{

	protected double[][] weights = null;
	protected double[][]	feromon = null;
	protected double homeferomon = 0.01;
	protected int n = 0;
	protected GenerationFabric g_i;
	
	public abstract GenerationFabric getInitialGeneration();
	
	
	
	//public Solution getInitSolution(int begin) {
	//	Generator gen = new Generator(weights, begin);
	//	int[] rez = new int[n+1]; 
	//	Solution sol = new Solution(rez, feromon);
	//	Solution s = gen.getNextSolution(sol);
	//		Solution s = new Solution();
	//	return s;
	//}
	
	
	
	
}
