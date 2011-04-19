package simulatedannealing.continous;

import java.util.Random;

import simulatedannealing.GenerationList;
import usablefunctions.Function;
import core.Initializator;

public class FuncInitializator implements Initializator<GenerationList> {
	private double[][] domain;
	public FuncInitializator(double[][] domain) {
		this.domain = domain;
	}
	
	@Override
	public GenerationList getInitialGeneration() {
		Point a = new Point(domain.length);
		Random r = new Random();

		for(int i=0;i<a.getDimension();++i) {
			a.coords[i] = r.nextDouble()*(domain[i][1]-domain[i][0]) + domain[i][0];
		}
		
		GenerationList b = new GenerationList();
		b.add(a);
		return b;
	}

}
