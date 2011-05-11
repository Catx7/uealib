package simulatedannealing.continous;

import java.util.Random;

import simulatedannealing.IGenerator;
import core.Generator;

public class FuncGenerator implements IGenerator<Point> {
	private double[][] domain;
	public FuncGenerator(double[][] domain) {
		this.domain = domain;
	}
	
	
	@Override
	public Point getNext(Point p) {
		Random r = new Random();

		double eps = 0.05;
		Point b = new Point(p.getDimension());
		
		for(int i = 0; i < p.getDimension(); ++i) {
			int direct = r.nextDouble() < 0.5 ? 1 : -1;
			
			if(direct == 1)
				b.coords[i] = p.coords[i] + (domain[i][1]-p.coords[i])*r.nextDouble()*eps;
			else
				b.coords[i] = p.coords[i] - (p.coords[i]-domain[i][0])*r.nextDouble()*eps;
				         
		}
		return b;

	}

}
