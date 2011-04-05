package diffevolution;


import diffevolution.Selector;
import common.TicksStoppingCriteria;
import common.UnconditionalTransitionCriteria;
import core.Generator;

public class DifferentialEvolutionAlgorithm <S extends ArraySolution<?>,
										     G extends Generation<S>,
										     Cr extends AbstractCrossoverFabric<S>,
									         C extends Context<G, S, Cr>> {
		
		protected Initializator<S, G> initializator;
		protected Generator<G> generator;
		protected core.StoppingCriteria<G> stoppingCriteria;
		protected Selector<S, G, Cr,C> selector;
		protected core.TransitionCriteria<G> transitionCriteria;
		protected C context;
		
		public DifferentialEvolutionAlgorithm(Initializator<S, G> initializator,
				Generator<G> generator,
				TicksStoppingCriteria<S, G, C> stoppingCriteria,
				Selector<S, G, Cr, C> selector,
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
			//System.out.println(currentGeneration.getRepresentation());
			while (!this.stoppingCriteria.isSatisfied(currentGeneration)) {
				G g = generator.getNext(currentGeneration);
				G h = selector.keepTheBestSolutions(g, currentGeneration);
				
		        if (transitionCriteria.isSatisfied(g, currentGeneration)) {
		                currentGeneration = h;
		        }
		        this.context.tick();
			}
			return (G) currentGeneration.getBest();
		}
}
