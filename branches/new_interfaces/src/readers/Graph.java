package readers;

public class Graph implements Problem {
	private double[][] weights;

	public void setWeights(double[][] weights) {
		assert weights.length == weights[1].length;
		
		this.weights = weights;
	}

	public double[][] getWeights() {
		return weights;
	}
	
	public int getN() {
		return weights.length;
	}	
}
