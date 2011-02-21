package taboosearch;

import taboosearch.exceptions.UnsupportedMoveType;

public interface Evaluator<S extends Solution, M extends Move<S>> {
	public double evaluateMove(S solution, M move) throws UnsupportedMoveType;
	public double evaluate(S solution, M move) throws UnsupportedMoveType;
	public double evaluate(S solution);
	public FrequencyMemory<S, M> getFrequencyMemory();
}
