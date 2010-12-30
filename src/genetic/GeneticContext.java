package genetic;

public class GeneticContext<G extends Generation<S>, S extends Solution<S>>
		extends core.Context {

	private int currentGenerationNumber;

	private Evaluator<S> evaluator;

	private int chromosomeLength;

	private AbstractGenerationFabric<G, GeneticContext<G, S>> fabric;

	private S bestKnownRoute;

	private double bestFitness;

	public S getBestKnownRoute() {
		return bestKnownRoute;
	}

	public void setBestKnownRoute(S newBestKnownRoute) {
		bestKnownRoute = newBestKnownRoute.copy();
		bestFitness = evaluator.evaluate(bestKnownRoute);
	}

	public double getBestFitness() {
		return bestFitness;
	}

	public GeneticContext(Evaluator<S> evaluator, int chromosomeLength,
			AbstractGenerationFabric<G, GeneticContext<G, S>> fabric) {
		this.setEvaluator(evaluator);
		this.chromosomeLength = chromosomeLength;
		this.fabric = fabric;
	}

	public int getChromosomeLength() {
		return chromosomeLength;
	}

	public int getCurrentGenerationNumber() {
		return currentGenerationNumber;
	}

	public void incGenerationNumber() {
		++currentGenerationNumber;
	}

	public void setEvaluator(Evaluator<S> evaluator) {
		this.evaluator = evaluator;
	}

	public Evaluator<S> getEvaluator() {
		return evaluator;
	}

	public AbstractGenerationFabric<G, GeneticContext<G, S>> getGenerationFabric() {
		return fabric;
	}

}
