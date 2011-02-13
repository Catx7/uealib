package taboosearch.exceptions;

public class NotEvaluatedSolution extends Exception {

	@Override
	public String getMessage() {
		return "getCost() called on the previously not evaluated Solution";	
	}
	
	private static final long serialVersionUID = 603632555445840173L;
}
