package antcolony.fabric;

public class BackpackInitializator extends InitializatorFabric{
	
	private double capacity;
	private double[] ves;

	public BackpackInitializator(double[][] w, double cap, double[] v) {
		n = w[0].length;
		weights = new double[n][n];
		weights = w;
		feromon = new double[n][n];
		for(int i=0; i<n; i++){
			for(int j=0; j<n; j++){
				feromon[i][j] = homeferomon;
			}
			
		}
		capacity = cap;
		ves = v;
	}

	@Override
	public GenerationFabric getInitialGeneration() {
		int[] rez  = new int[n];
		AntAlgoritmParam ant_al = new AntAlgoritmParam(0.5,0.5,1,0,100);
		//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		antcolony.fabric.GeneratorFabric gen = new antcolony.fabric.BackpackGenerator(weights, 0, ant_al);
		//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		AntSolutionFabric s = new BackpackSolution(rez, feromon, weights);
		//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		g_i = new BackpackGeneration(s, capacity,ves);
	
		g_i = new BackpackGeneration(gen.getNext(g_i).getSolution(), capacity,ves);

		return g_i;
	}
}
