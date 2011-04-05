package diffevolution.functions;

import java.util.List;


public class FSolution extends diffevolution.ArraySolution<Double>{
	
	final protected double[] coordinates;
	
	
	public FSolution(double[] coordinates) {
		this.coordinates = coordinates.clone();
	}
	
	@Override
	public String getRepresentation() {
		double eps = 0.00001;
		StringBuilder result = new StringBuilder();
		result.append("[ ");
		
		result.append(coordinates[0]);
		for (int i = 1; i < coordinates.length; ++i) {
			result.append(", ");
			if(Math.abs(coordinates[i]) > eps)
				result.append(coordinates[i]);
			else
				result.append(0);
		}

		result.append(" ] ");
		return result.toString();
	}

	@Override
	public FSolution copy() {
		return new FSolution(this.coordinates);
	}
	
	public FSolution(List<Double> coordinates) {
		this.coordinates = new double[coordinates.size()];
		for (int i = 0; i < this.coordinates.length; ++i)
			this.coordinates[i] = coordinates.get(i);
	}

	@Override
	public Double get(int index) {
		return this.coordinates[index];
	}

	@Override
	public int length() {
		return this.coordinates.length;
	}

	@Override
	public void set(int index, Double value) {
		this.coordinates[index] = value;
	}
	
	public double[] getArray() {
		return this.coordinates;
	}
}

