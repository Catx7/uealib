package antcolony.fabric;

public class BackpackSolution extends antcolony.fabric.AntSolutionFabric{

	public BackpackSolution (int[] res, double[][] fer, double[][] g){
		nr = res.length;
		result = new int[nr];
		result = res; 
		int nf = fer[0].length;
		feromon = new double[nf][nf];
		feromon = fer;
		graph = new double[nf][nf];
		graph = g;
	}

	public double getFitness() {
		double length = 0;
		for(int i=0; i<nr-1; i++){
			length += result[i]*graph[0][i];
		}
		// ....................
		return length;
	}

	@Override
	public double[] getBest() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
