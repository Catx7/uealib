package core;

public class Algorithm {

	protected Initializator init;
	protected Generator generator;
	protected StoppingCriteria stoppingCriteria;
	protected Selector selector;
	protected TransitionCriteria transitionCriteria;

	public Algorithm() {}
	
	public Algorithm(Initializator i, Generator g, StoppingCriteria stop,
			Selector selector, TransitionCriteria trans) {

		this.init = i;
		this.generator = g;
		this.stoppingCriteria = stop;
		this.selector = selector;
		this.transitionCriteria = trans;
	}

	@SuppressWarnings("unchecked")
	public Generation solve() {
		Generation currentGeneration = this.init.getInitialGeneration();

		while (!stoppingCriteria.isSatisfied(currentGeneration)) {

			Generation g = generator.getNext(currentGeneration);
			Generation h = selector.keepTheBestSolutions(g, currentGeneration);

			if (transitionCriteria.isSatisfied(currentGeneration, h)) {
				currentGeneration = h;
			}
		}

		return currentGeneration;
	}
}
