package simulatedannealing.continous;

import simulatedannealing.AbstractSolution;
import core.Solution;

public class Point extends AbstractSolution {
	public double[] coords;
	public Point(int dimension) {
		coords = new double[dimension];
	}
	
	public int getDimension() {
		return coords.length;
	}
	
	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer("[");
		for(int i=0; i<coords.length; ++i) {
			buf.append(coords[i]);
			if(i != coords.length -1)
				buf.append(", ");
		}
		buf.append("]");
		return buf.toString();
	}
	
	@Override
	public Solution copy() {
		// TODO Auto-generated method stub
		return null;
	}
}
