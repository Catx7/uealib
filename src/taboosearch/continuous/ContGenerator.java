package taboosearch.continuous;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import common.Pair;
import taboosearch.AdmissibilityChecker;
import taboosearch.exceptions.NotEvaluatedSolution;
import taboosearch.exceptions.UnsupportedMoveType;

public class ContGenerator
			implements taboosearch.Generator<ContSolution, ContMove> {
	
	private AdmissibilityChecker<ContSolution, ContMove> admissibilityChecker;
	
	static final int N = 10;
	static final double R = 0.8;
	
	public ContGenerator(AdmissibilityChecker<ContSolution, ContMove> admissibilityChecker) {
		this.admissibilityChecker = admissibilityChecker;
	}
	
	@Override
	public Pair<ContSolution, Collection<ContMove>> getMoves(ContSolution solution)
			throws NotEvaluatedSolution {
		assert solution.length() == 2;
		
		double[] radii = new double[N - 1];
		for (int i = 0; i < N - 1; ++i) {
			radii[i] = (R / N) * (i + 1);
		}
		
		Random random = new Random();
		ArrayList<ContMove> result = new ArrayList<ContMove>();
		
		for (int i = 0; i < N - 1; ++i) {
			try {
				ContMove move = null;
				int failsCount = 0;
				do {
					double angle = random.nextDouble() * 360;
					double angleInRadians = angle / 180 * Math.PI;
					double cos = Math.cos(angleInRadians);
					double sin = Math.sin(angleInRadians);
					
					//System.out.println(resX + " " + resY + " " + Math.sqrt(resX*resX + resY*resY));
					move = new ContMove(radii[i] * cos, radii[i] * sin);
					failsCount++;
					if (failsCount == 40) break;
				} while (!admissibilityChecker.isAdmissible(solution, move));
				if (move != null) result.add(move);
			} catch (UnsupportedMoveType e) {
				e.printStackTrace();
			}
		}
		
		return new Pair<ContSolution, Collection<ContMove>>(solution, result);
	}

}
