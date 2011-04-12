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
			int direct = r.nextDouble() < 0.5 ? 1 : -1;
			a.coords[i] = direct * r.nextDouble();
		}
		
		GenerationList b = new GenerationList();
		b.add(a);
		return b;
	}

}
