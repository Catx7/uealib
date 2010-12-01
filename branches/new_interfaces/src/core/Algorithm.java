package core;

public class Algorithm<T extends Generation<? extends Solution>> {

	protected Initializator<T> init;
	protected Generator<T> generator;
	protected StoppingCriteria<T> stoppingCriteria;
	protected Selector<T> selector;
	protected TransitionCriteria<T> transitionCriteria;

	public Algorithm() {}
	
	public Algorithm(Initializator<T> i, Generator<T> g, StoppingCriteria<T> stop,
			Selector<T> selector, TransitionCriteria<T> trans) {

		this.init = i;
		this.generator = g;
		this.stoppingCriteria = stop;
		this.selector = selector;
		this.transitionCriteria = trans;
	}

	public T solve() {
		T currentGeneration = this.init.getInitialGeneration();

		while (!stoppingCriteria.isSatisfied(currentGeneration)) {

			T g = generator.getNext(currentGeneration);
			T h = selector.keepTheBestSolutions(g, currentGeneration);

			if (transitionCriteria.isSatisfied(currentGeneration, h)) {
				currentGeneration = h;
			}
		}

		return currentGeneration;
	}
}
