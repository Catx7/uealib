package genetic;

import core.StoppingCriteria;

public class GenerationNumStoppingCriterion<G extends genetic.Generation<S>, S extends genetic.Solution<S>>
		implements StoppingCriteria<G> {

	protected int maxGenerationNum;
	protected GeneticContext<G, S> context;

	public GenerationNumStoppingCriterion(int maxGenerationNum,
			GeneticContext<G, S> context) {
		this.maxGenerationNum = maxGenerationNum;
		this.context = context;
	}

	@Override
	public boolean isSatisfied(G g) {
		return maxGenerationNum == context.getCurrentGenerationNumber();
	}

}
