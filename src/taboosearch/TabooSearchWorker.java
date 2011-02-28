package taboosearch;

import java.util.List;
import common.Pair;
import common.TicksStoppingCriteria;
import taboosearch.exceptions.UnsupportedMoveType;
import taboosearch.permutations.Solution;
import taboosearch.permutations.Move;

public class TabooSearchWorker<S extends Solution,
							   M extends Move<S>,
							   G extends Generation<S>,
							   C extends Context<S, M, G>> implements Runnable {
	private C context;
	private TicksStoppingCriteria<S, G, C> stoppingCriteria;
	private Generator<S, M, G> generator;
	private Selector<S, M, G, C> selector;
	private G currentGeneration;
	
	public TabooSearchWorker(
			G initialGeneration,
			Generator<S, M, G> generator,
			TicksStoppingCriteria<S, G, C> stoppingCriteria,
			Selector<S, M, G, C> selector,
			C context) {
		this.currentGeneration = initialGeneration;
		this.generator = generator;
		this.stoppingCriteria = stoppingCriteria;
		this.selector = selector;
		this.context = context;
	}

	@Override
	public void run() {
		try {
			while (!stoppingCriteria.isSatisfied(currentGeneration, context)) {
				Pair<S, List<M>> moves = generator.getNext(currentGeneration);
				currentGeneration = selector.keepTheBestSolutions(moves);
				context.tick();
			}
		} catch (UnsupportedMoveType e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}

}
