package simulatedannealing.continous;

import simulatedannealing.IEvaluator;
import usablefunctions.Function;
import core.Solution;

public class FuncEvaluator implements IEvaluator<Point> {

	private Function func;
	public FuncEvaluator(Function func) {
		this.func = func;
	}

	@Override
	public int compare(Point arg0, Point arg1) {
		return Double.compare(evaluate(arg1), evaluate(arg0));
	}

	@Override
	public double evaluate(Point a) {
		if(a.getFitness() != null)
			return a.getFitness();
		
		double result = func.apply(a.coords);
		a.setFitness(new Double(result));
		return result;
	}

}
