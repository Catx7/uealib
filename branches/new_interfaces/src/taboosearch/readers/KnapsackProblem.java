package taboosearch.readers;

public class KnapsackProblem {
	private double[] values;
	private double[] weights;
	private double capacity;
	
	public KnapsackProblem(double[] weights, double[] values, double capacity) {
		this.weights = weights.clone();
		this.values = values.clone();
		this.capacity = capacity;		
	}

	public double[] getWeights() {
		return weights;
	}
	
	public double[] getValues() {
		return values;
	}
	
	public double getCapacity() {
		return capacity;
	}
}
