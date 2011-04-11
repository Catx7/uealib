package taboosearch;

import java.util.Collection;
import taboosearch.exceptions.NotEvaluatedSolution;
import taboosearch.exceptions.UnsupportedMoveType;
import common.Pair;
import common.alternative.TicksStoppingCriteria;

public class TabooSearchAlgorithm<S extends Solution,
							      M extends Move<S>,
								  C extends Context<S, M>> {
	
	protected Initializator<S> initializator;
	protected Generator<S, M> generator;
	protected TicksStoppingCriteria<S, C> stoppingCriteria;
	protected Selector<S, M, C> selector;
	protected C context;

	public TabooSearchAlgorithm(
			Initializator<S> initializator,
			Generator<S, M> generator,
			TicksStoppingCriteria<S, C> stoppingCriteria,
			Selector<S, M, C> selector,
			C context) {
		this.initializator = initializator;
		this.generator = generator;
		this.stoppingCriteria = stoppingCriteria;
		this.selector = selector;
		this.context = context;
	}

	public S solve() throws UnsupportedMoveType, NotEvaluatedSolution {
		long begin = System.currentTimeMillis();
		
		S currentSolution = initializator.getInitialSolution();
		while (!stoppingCriteria.isSatisfied(currentSolution, context)) {
			Pair<S, Collection<M>> moves = generator.getMoves(currentSolution);
			currentSolution = selector.getBestSolution(moves);
			context.tick();
		}
		System.out.println();
		System.out.println(context.bestSolutionEver.getStringRepresentation());
		System.out.println(context.bestSolutionEverCost);
		
		long finish = System.currentTimeMillis();
		System.out.println((finish - begin) / 1000.0 + "sec");
		return currentSolution;
    }

}
