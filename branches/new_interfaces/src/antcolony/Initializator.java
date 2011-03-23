package antcolony;



public class Initializator implements core.Initializator{

private double[][] weights = null;
	private double[][]	feromon = null;
	private double homeferomon = 0.01;
	private int n = 0;
	protected Generation g_i;
	
	public Initializator(double[][] w) {
		n = w[0].length;
		weights = new double[n][n];
		weights = w;
		feromon = new double[n][n];
		for(int i=0; i<n; i++){
			for(int j=0; j<n; j++){
				feromon[i][j] = homeferomon;
			}
		}
	}
	
	//public Solution getInitSolution(int begin) {
	//	Generator gen = new Generator(weights, begin);
	//	int[] rez = new int[n+1]; 
	//	Solution sol = new Solution(rez, feromon);
	//	Solution s = gen.getNextSolution(sol);
	//		Solution s = new Solution();
	//	return s;
	//}
	
	public Generation getInitialGeneration() {
		int[] rez  = new int[n];
		AntAlgoritmParam ant_al = new AntAlgoritmParam(0.5,0.5,1,0,100);
		antcolony.Generator gen = new antcolony.Generator(weights, 0, ant_al);
		AntSolution s = new AntSolution(rez, feromon, weights);
		g_i = new Generation(s);
	
		g_i = new Generation(gen.getNext(g_i).getSolution());

		return g_i;
	}
	
	
}
