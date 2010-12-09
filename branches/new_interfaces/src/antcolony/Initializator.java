package antcolony;

import java.util.LinkedList;

public class Initializator implements core.Initializator {
	
	private int[][] weights = null;
	private double[][]	feromon = null;
	private double homeferomon = 0.25;
	private int n = 0;
	
	public Initializator(int[][] w) {
		n = w[0].length;
		weights = new int[n][n];
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
		int[] rez  = null;
		Solution s = new Solution(rez, feromon);
		Generation g = new Generation(s);
		return g;
	}
	
	
}
