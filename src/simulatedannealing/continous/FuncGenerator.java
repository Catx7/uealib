package simulatedannealing.continous;

import java.util.Random;

import simulatedannealing.GenerationList;
import usablefunctions.Function;
import core.Generator;

public class FuncGenerator implements Generator<GenerationList> {
	private double[][] domain;
	public FuncGenerator(double[][] domain) {
		this.domain = domain;
	}
	
	
	@Override
	public GenerationList getNext(GenerationList g) {
		Random r = new Random();

		double eps = 0.05;
		Point a = (Point) g.get(0);
		Point b = new Point(a.getDimension());
		
		
		//TODO: математика ололо
		for(int i = 0; i < a.getDimension(); ++i) {
			int direct = r.nextDouble() < 0.5 ? 1 : -1;
			
			if(direct == 1)
				b.coords[i] = a.coords[i] + (domain[i][1]-a.coords[i])*r.nextDouble()*eps;
			else
				b.coords[i] = a.coords[i] - (a.coords[i]-domain[i][0])*r.nextDouble()*eps;
				         
		}
		GenerationList res = new GenerationList();
		res.add(b);
		return res;

	}

}
