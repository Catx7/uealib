package taboosearch.continuous;

import taboosearch.Solution;

public class ContSolution extends Solution {
	private double[] coords;
	
	public ContSolution(double[] coords) {
		this.coords = coords.clone();
	}
	
	public double getCoord(int idx) {
		return coords[idx];
	}
	
	public int length() {
		return coords.length;
	}

	@Override
	public String getStringRepresentation() {
		return coords[0] + " " + coords[1];
	}

	@Override
	public core.Solution copy() {
		return null;
	}
}
