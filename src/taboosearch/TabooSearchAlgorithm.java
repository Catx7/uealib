package taboosearch;

import core.Algorithm;

public class TabooSearchAlgorithm<S extends Solution,
								  G extends Generation<S>,
								  C extends Context<S, G>> extends Algorithm<G> {
	
	C context;
	
	public TabooSearchAlgorithm(
			Initializator<S, G> initializator,
			Generator<S, G> generator,
			TicksStoppingCriteria<S, G, C> stoppingCriteria,
			Selector<S, G, C> selector,
			TransitionCriteria<S, G, C> transitionCriteria,
			C context) {
		super(initializator, generator, stoppingCriteria, selector, transitionCriteria, context);
		this.context = context;
	}

	public G solve() {
		G currentGeneration = this.init.getInitialGeneration();
		while (!this.stoppingCriteria.isSatisfied(currentGeneration)) {
			G g = generator.getNext(currentGeneration);
			G h = selector.keepTheBestSolutions(g, currentGeneration);
	
	        if (transitionCriteria.isSatisfied(currentGeneration, h)) {
	        	currentGeneration = h;
	        }
	        
	        this.context.tick();
			S s = currentGeneration.get(0);
			//System.out.println(s.getStringRepresentation());
			
			if (context.getEvaluator().evaluate(s) < context.bestSolutionEverFitness) {
				context.bestSolutionEver = s;
				context.bestSolutionEverFitness = context.getEvaluator().evaluate(s);
			}

		}
		System.out.println(context.bestSolutionEver.getStringRepresentation());
		System.out.println(context.bestSolutionEverFitness);
		return currentGeneration;
    }

}
