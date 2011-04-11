package taboosearch.continuous;

import java.util.List;

import taboosearch.Attribute;
import taboosearch.Move;

public class ContMove implements Move<ContSolution> {
	public double deltaX;
	public double deltaY;
	
	public ContMove(double deltaX, double deltaY) {
		this.deltaX = deltaX;
		this.deltaY = deltaY;
	}
	
	@Override
	public List<? extends Attribute<ContSolution>> getAttributes(
			ContSolution solution) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ContSolution operateOn(ContSolution solution) {
		double[] coords = new double[solution.length()];
		coords[0] = solution.getCoord(0);
		coords[1] = solution.getCoord(1);
		coords[0] += deltaX;
		coords[1] += deltaY;
		return new ContSolution(coords);
	}

}
