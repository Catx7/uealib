package antcolony;

public class Solution extends core.Solution{
	
	private double[][] feromon = null;
	private int[] result = null;
	
	public Solution (int[] res, double[][] fer){
		int nr = res.length;
		result = new int[nr];
		result = res;
		int nf = fer[0].length;
		feromon = new double[nf][nf];
		feromon = fer;
	}
	
	public double[][] GetFeromon(){		
		return feromon;
	}
	
	public int[] GetResult(){		
		return result;
	}

	public double getFitness() {
		double length = 0;
		// ....................
		return length;
	}
	
}
