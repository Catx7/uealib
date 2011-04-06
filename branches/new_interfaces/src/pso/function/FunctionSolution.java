package pso.function;

import java.util.List;


public class FunctionSolution extends pso.Solution<FunctionSolution> {
	
	final protected double[] collection;	
	public static int[] maxItemNum;
	
	
	public FunctionSolution(double[] items) {
		this.collection = items.clone();
	}
	
	public FunctionSolution(List<Double> items) {
		this.collection = new double[items.size()];
		for (int i = 0; i < this.collection.length; ++i)
			this.collection[i] = items.get(i);
	}
	
	public int length() {
        return this.collection.length;
	}
	
	public double get(int index) {
		return this.collection[index];
	}
	
	public double[] toArray() {
		return this.collection.clone();
	}

	public String getRepresentation() {
		StringBuilder result = new StringBuilder();
		result.append("[ ");
		
		result.append(collection[0]);
		for (int i = 1; i < collection.length; ++i) {
			result.append(", ");
			result.append(collection[i]);
		}

		result.append(" ] ");
		return result.toString();
	}
	
	@Override
	public FunctionSolution copy() {
		return new FunctionSolution(collection);
	}
	
}
