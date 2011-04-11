package taboosearch;

import java.util.Collection;
import common.Pair;
import common.alternative.TicksStoppingCriteria;
import taboosearch.exceptions.NotEvaluatedSolution;
import taboosearch.permutations.Solution;
import taboosearch.permutations.Move;

public class TabooSearchWorker<S extends Solution,
							   M extends Move<S>,
							   C extends Context<S, M>> implements Runnable {
	private C context;
	private TicksStoppingCriteria<S, C> stoppingCriteria;
	private Generator<S, M> generator;
	private Selector<S, M, C> selector;
	private S currentSolution;
	
	public TabooSearchWorker(
			S initialSolution,
			Generator<S, M> generator,
			TicksStoppingCriteria<S, C> stoppingCriteria,
			Selector<S, M, C> selector,
			C context) {
		this.currentSolution = initialSolution;
		this.generator = generator;
		this.stoppingCriteria = stoppingCriteria;
		this.selector = selector;
		this.context = context;
	}

	@Override
	public void run() {
		try {
			while (!stoppingCriteria.isSatisfied(currentSolution, context)) {
				Pair<S, Collection<M>> moves = generator.getMoves(currentSolution);
				currentSolution = selector.getBestSolution(moves);
				context.tick();
			}
		} catch (NotEvaluatedSolution e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}

}
