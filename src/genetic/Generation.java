package genetic;

import java.util.ArrayList;

public class Generation<S extends genetic.Solution<S>> extends ArrayList<S>
		implements core.Generation<S> {

	private static final long serialVersionUID = 1L;

	protected GeneticContext<Generation<S>, S> context;

	public <C extends GeneticContext<Generation<S>, S>> Generation(C context) {
		this.context = context;
	}

	@Override
	public Generation<S> getBest() {
		double currentMaximum = 0;
		Generation<S> best = new Generation<S>(context);
		for (S solution : this) {
			double fitness = context.getEvaluator().evaluate(solution);
			if (fitness > currentMaximum) {
				currentMaximum = fitness;
				best.clear();
				best.add(solution);
			} else if (fitness == currentMaximum)
				best.add(solution);
		}
		return best;
	}
	
	public String getRepresentation() {
		String representation = "Generation[";
		for (Solution<S> solution: this)
			representation += "\n  " + solution.getRepresentation(); // Q&D
		return representation + "\n]";
	}
}