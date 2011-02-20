package taboosearch.exceptions;

public class UnsupportedMoveType extends Exception {
	private String moveType;
	
	public UnsupportedMoveType(String moveType) {
		this.moveType = moveType;
	}
	
	@Override
	public String getMessage() {
		return "evaluator can't evaluate " + moveType;	
	}
	
	private static final long serialVersionUID = 603632555445840173L;
}
