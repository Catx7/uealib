package diffevolution.functions;

import diffevolution.Evaluator;
import functions.Functions;


public class FEvaluator<F extends Functions> extends Evaluator<FSolution> {
	
	private F function;

	public FEvaluator(F function) {
		this.function = function;
	}
	
	public double evaluate(FSolution solution) {
		if (this.function.getMax() > 0)
			return -this.function.getResult(solution.getArray());
		return this.function.getResult(solution.getArray());
	}
	
	public double getLeft(){
		return this.function.getLeftBorder();
	}
	
	public double getRight(){
		return this.function.getRightBorder();
	}
}
