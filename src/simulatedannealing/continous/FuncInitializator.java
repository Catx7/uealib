package simulatedannealing.continous;

import java.util.Random;

import simulatedannealing.IInitializator;
import usablefunctions.Function;
import core.Initializator;

public class FuncInitializator implements IInitializator<Point> {
	private double[][] domain;
	public FuncInitializator(double[][] domain) {
		this.domain = domain;
	}
	
	@Override
	public Point  getInitialSolution() {
		Point a = new Point(domain.length);
		Random r = new Random();

		for(int i=0;i<a.getDimension();++i) {
			a.coords[i] = r.nextDouble()*(domain[i][1]-domain[i][0]) + domain[i][0];
		}
		
		return a;
	}

}
