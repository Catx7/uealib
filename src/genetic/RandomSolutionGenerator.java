package genetic;

public abstract class RandomSolutionGenerator<S extends Solution<S>> {

	protected int chromosomeLength;

	public RandomSolutionGenerator(int chromosomeLength) {
		this.chromosomeLength = chromosomeLength;
	}

	public RandomSolutionGenerator(GeneticContext<?, S> context) {
		this.chromosomeLength = context.getChromosomeLength();
	}

	public int getChromosomeLength() {
		return chromosomeLength;
	}

	public abstract S generateRandomSolution();

}
