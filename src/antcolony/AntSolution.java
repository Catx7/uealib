package antcolony;

import core.Solution;


public class AntSolution  extends core.Solution{
	
	private double[][] feromon = null;
	private int[] result = null;
	private double[][] graph = null;
	private int nr;
	
	public AntSolution (int[] res, double[][] fer, double[][] g){
		nr = res.length;
		result = new int[nr];
		result = res; 
		int nf = fer[0].length;
		feromon = new double[nf][nf];
		feromon = fer;
		graph = new double[nf][nf];
		graph = g;
	}
	
	public double[][] GetFeromon(){		
		return feromon;
	}
	
	public int[] GetResult(){		
		return result;
	}

	public double getFitness() {
		double length = 0;
		for(int i=0; i<nr-1; i++){
			length += graph[result[i]][result[i+1]];
		}
		length+= graph[result[0]][result[nr-1]];
		// ....................
		return length;
	}

	@Override
	public Solution copy() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
