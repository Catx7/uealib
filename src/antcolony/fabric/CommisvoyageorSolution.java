package antcolony.fabric;

public class CommisvoyageorSolution extends antcolony.fabric.AntSolutionFabric{	
	
	
	public CommisvoyageorSolution (int[] res, double[][] fer, double[][] g){
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
			if(result[i]!=999999999 && result[i+1]!=999999999){
				length += graph[result[i]][result[i+1]];
			}
		}
		if(result[0]!=999999999 && result[nr-1]!=999999999){
			length+= graph[result[0]][result[nr-1]];
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
