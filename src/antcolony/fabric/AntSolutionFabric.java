package antcolony.fabric;

import core.Solution;


public abstract class AntSolutionFabric  extends core.Solution{
	
	protected double[][] feromon = null;
	protected int[] result = null;
	protected double[][] graph = null;
	protected int nr;
	

	
	public double[][] GetFeromon(){		
		return feromon;
	}
	
	public int[] GetResult(){		
		return result;
	}
	
	public abstract double getFitness();

	public abstract double[] getBest();

	@Override
	public Solution copy() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
