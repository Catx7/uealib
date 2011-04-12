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

		double eps = r.nextDouble() / 10.;
		Point a = (Point) g.get(0);
		Point b = new Point(a.getDimension());
		
		
		//TODO: математика ололо
		for(int i = 0; i < a.getDimension(); ++i) {
			int direct = r.nextDouble() < 0.5 ? 1 : -1;
			b.coords[i] = (a.coords[i] + direct * eps);
			if(b.coords[i] < domain[i][0]) {
				b.coords[i] = b.coords[i] + (domain[i][1]-domain[i][0]);				
			}
			else if(b.coords[i] > domain[i][1]) {
				b.coords[i] = b.coords[i] - (domain[i][1]-domain[i][0]);
			}
		}
		GenerationList res = new GenerationList();
		res.add(b);
		return res;

	}

}
