package simulatedannealing.continous;

import java.util.Random;

import simulatedannealing.GenerationList;
import core.Generation;
import core.Initializator;

public class FuncInitializator implements Initializator<GenerationList>{

	@Override
	public GenerationList getInitialGeneration() {
		Point a = new Point();
		Random r = new Random();
		
		int directX = r.nextDouble()<0.5?1:-1;
		int directY = r.nextDouble()<0.5?1:-1;
		a.x = directX*r.nextDouble();
		a.y = directY*r.nextDouble();
		
		GenerationList b = new GenerationList();
		b.add(a);
		return b;
	}

}
