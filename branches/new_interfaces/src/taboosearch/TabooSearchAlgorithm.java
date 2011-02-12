package taboosearch;

import java.util.List;

import common.Pair;
import common.TicksStoppingCriteria;
import common.UnconditionalTransitionCriteria;

public class TabooSearchAlgorithm<S extends Solution,
							      M extends Move<S>,
								  G extends Generation<S>,
								  C extends Context<S, M, G>> {
	
	protected Initializator<S, M, G> initializator;
	protected Generator<S, M, G> generator;
	protected core.StoppingCriteria<G> stoppingCriteria;
	protected Selector<S, M, G, C> selector;
	protected core.TransitionCriteria<G> transitionCriteria;
	protected C context;

	public TabooSearchAlgorithm(
			Initializator<S, M, G> initializator,
			Generator<S, M, G> generator,
			TicksStoppingCriteria<S, G, C> stoppingCriteria,
			Selector<S, M, G, C> selector,
			UnconditionalTransitionCriteria<S, G, C> transitionCriteria,
			C context) {

		this.initializator = initializator;
		this.generator = generator;
		this.stoppingCriteria = stoppingCriteria;
		this.selector = selector;
		this.transitionCriteria = transitionCriteria;
		this.context = context;
	}

	public G solve() {
		long begin = System.currentTimeMillis();
		G currentGeneration = this.initializator.getInitialGeneration();
		while (!this.stoppingCriteria.isSatisfied(currentGeneration)) {
			Pair<S, List<M>> moves = generator.getNext(currentGeneration);
			G h = selector.keepTheBestSolutions(moves);
	
	        if (transitionCriteria.isSatisfied(currentGeneration, h)) {
	        	currentGeneration = h;
	        }
	        
			S s = currentGeneration.get(0);
			this.context.setCurrentSolution(s, this.context.getEvaluator().evaluate(s));
			this.context.tick();
		}
		System.out.println();
		System.out.println(context.bestSolutionEver.getStringRepresentation());
		System.out.println(context.bestSolutionEverCost);
		long finish = System.currentTimeMillis();
		System.out.println((finish - begin) / 100.0 + "sec");
		return currentGeneration;
    }

}
