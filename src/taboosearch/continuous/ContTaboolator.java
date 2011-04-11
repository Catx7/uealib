package taboosearch.continuous;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import taboosearch.Attribute;
import taboosearch.Taboolator;
import taboosearch.knapsack.KPMove;
import taboosearch.knapsack.KPSolution;
import taboosearch.tenures.TenureStrategy;

public class ContTaboolator extends Taboolator<ContSolution, ContMove> {
	protected List<Ball> balls;
	
	public ContTaboolator(TenureStrategy strategy) {
		super(strategy);
		balls = new ArrayList<Ball>();
	}
	
	public boolean isTabu(final ContSolution solution, final ContMove move) {
		double newX = solution.getCoord(0) + move.deltaX;
		double newY = solution.getCoord(1) + move.deltaY;
		for (int i = 0; i < balls.size(); ++i) {
			Ball ball = balls.get(i);
			if (Math.pow(ball.centerX - newX, 2) + Math.pow(ball.centerY - newY, 2) < Math.pow(ball.radius, 2)) {
				return true;
			}
		}
		return false;
	}

	public void setTabu(final ContSolution solution, final ContMove move) {
		Ball ball = new Ball();
		ball.centerX = solution.getCoord(0);
		ball.centerY = solution.getCoord(1);
		ball.radius = ContGenerator.R / 30;
		ball.tenure = strategy.getTenure();
		balls.add(ball);
	}
	
	private void decreaseTenures() {
		LinkedList<Integer> toErase = new LinkedList<Integer>();

		for (int i = 0; i < balls.size(); ++i) {
			int tenure = balls.get(i).tenure;
			if (tenure == 1) {
				toErase.add(i);
			} else {
				balls.get(i).tenure = tenure - 1;
			}
		}
		
		for (Integer idx : toErase)
			balls.remove(idx);
	}
}
