package diffevolution;


import diffevolution.Selector;
import common.TicksStoppingCriteria;
import common.UnconditionalTransitionCriteria;
import core.Generator;

public class DifferentialEvolutionAlgorithm <S extends Solution<S>,
										     G extends Generation<S>,
									         C extends Context<G, S>> {
		
		protected Initializator<S, G> initializator;
		protected Generator<G> generator;
		protected core.StoppingCriteria<G> stoppingCriteria;
		protected Selector<S, G, C> selector;
		protected core.TransitionCriteria<G> transitionCriteria;
		protected C context;
		
		public DifferentialEvolutionAlgorithm(Initializator<S, G> initializator,
				Generator<G> generator,
				TicksStoppingCriteria<S, G, C> stoppingCriteria,
				Selector<S, G, C> selector,
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
			G currentGeneration = this.initializator.getInitialGeneration();
			
			while (!this.stoppingCriteria.isSatisfied(currentGeneration)) {
				G g = generator.getNext(currentGeneration);
				G h = selector.keepTheBestSolutions(g, currentGeneration);
		
		        if (transitionCriteria.isSatisfied(g, currentGeneration)) {
		                currentGeneration = h;
		        }
		        this.context.tick();
			}
			return currentGeneration;
		}
}
