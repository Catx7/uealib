package taboosearch;

import java.util.List;

import taboosearch.exceptions.NotEvaluatedSolution;
import taboosearch.exceptions.UnsupportedMoveType;
import common.Pair;
import common.TicksStoppingCriteria;

public class TabooSearchAlgorithm<S extends Solution,
							      M extends Move<S>,
								  G extends Generation<S>,
								  C extends Context<S, M, G>> {
	
	protected Initializator<S, G> initializator;
	protected Generator<S, M, G> generator;
	protected TicksStoppingCriteria<S, G, C> stoppingCriteria;
	protected Selector<S, M, G, C> selector;
	protected core.TransitionCriteria<G> transitionCriteria;
	protected C context;

	public TabooSearchAlgorithm(
			Initializator<S, G> initializator,
			Generator<S, M, G> generator,
			TicksStoppingCriteria<S, G, C> stoppingCriteria,
			Selector<S, M, G, C> selector,
			C context) {
		this.initializator = initializator;
		this.generator = generator;
		this.stoppingCriteria = stoppingCriteria;
		this.selector = selector;
		this.context = context;
	}

	public G solve() throws UnsupportedMoveType, NotEvaluatedSolution {
		long begin = System.currentTimeMillis();
		
		G currentGeneration = initializator.getInitialGeneration();
		while (!stoppingCriteria.isSatisfied(currentGeneration, context)) {
			Pair<S, List<M>> moves = generator.getNext(currentGeneration);
			currentGeneration = selector.keepTheBestSolutions(moves);
			context.tick();
		}
		System.out.println();
		System.out.println(context.bestSolutionEver.getStringRepresentation());
		System.out.println(context.bestSolutionEverCost);
		
		long finish = System.currentTimeMillis();
		System.out.println((finish - begin) / 1000.0 + "sec");
		return currentGeneration;
    }

}
