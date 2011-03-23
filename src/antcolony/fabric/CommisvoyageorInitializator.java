package antcolony.fabric;



public class CommisvoyageorInitializator extends InitializatorFabric{

	public CommisvoyageorInitializator(double[][] w) {
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

	public GenerationFabric getInitialGeneration() {
		int[] rez  = new int[n];
		AntAlgoritmParam ant_al = new AntAlgoritmParam(0.5,0.5,1,0,100);
		//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		antcolony.fabric.GeneratorFabric gen = new antcolony.fabric.CommisvoyageorGenerator(weights, 0, ant_al);
		//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		AntSolutionFabric s = new CommisvoyageorSolution(rez, feromon, weights);
		//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		g_i = new CommisvoyageorGeneration(s);
	
		g_i = new CommisvoyageorGeneration(gen.getNext(g_i).getSolution());

		return g_i;
	}
}
