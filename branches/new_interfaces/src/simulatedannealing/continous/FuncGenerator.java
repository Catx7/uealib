package simulatedannealing.continous;

import java.util.Random;

import simulatedannealing.GenerationList;
import core.Generator;

public class FuncGenerator implements Generator<GenerationList> {

	@Override
	public GenerationList getNext(GenerationList g) {
		Random r = new Random();

		double eps = r.nextDouble() / 10.;
		int directX = r.nextDouble() < 0.5 ? 1 : -1;
		int directY = r.nextDouble() < 0.5 ? 1 : -1;

		Point a = (Point) g.get(0);

		Point b = new Point();

		b.x = (a.x + directX * eps) % 1;
		b.y = (a.y + directY * eps) % 1;

		GenerationList res = new GenerationList();
		res.add(b);
		return res;

	}

}
