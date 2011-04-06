package pso;


import pso.Selector;
import common.TicksStoppingCriteria;
import common.UnconditionalTransitionCriteria;
import pso.Generator;
//import core.StoppingCriteria;

public class PSOAlgorithm <S extends Solution<S>,
										     G extends Generation<S>,
									         C extends Context<G, S>> {
		
		protected Initializator<S, G> initializator;
		protected Generator<G, S> generator;
		protected common.TicksStoppingCriteria<S, G, C> stoppingCriteria;
		protected Selector<S, G, C> selector;
		protected core.TransitionCriteria<G> transitionCriteria;
		protected C context;
		
		public PSOAlgorithm(Initializator<S, G> initializator,
				Generator<G, S> generator,
				TicksStoppingCriteria<S, G, C> stoppingCriteria,
				Selector<S, G, C> selector,
				UnconditionalTransitionCriteria<S, G, C> transitionCriteria,
				C context) {
			
			this.initializator = initializator;
			this.generator = generator;
			this.stoppingCriteria =  stoppingCriteria;
			this.selector = selector;
			this.transitionCriteria = transitionCriteria;
			this.context = context;
		}
		
		public S solve() {
			G currentGeneration = this.initializator.getInitialGeneration();
			G bestGeneration = currentGeneration;
			S bestSolution = bestGeneration.get(0);
			
			while (!this.stoppingCriteria.isSatisfied(currentGeneration, this.context)) {
				G g =  generator.getNextGeneration(currentGeneration, bestGeneration, bestSolution);
				bestGeneration = selector.keepTheBestSolutions(g, bestGeneration);
				bestSolution = selector.keepTheBestSolution(bestGeneration);
		
		        /*if (transitionCriteria.isSatisfied(g, currentGeneration)) {
		                currentGeneration = h;
		        }*/
		        this.context.tick();
		        //System.out.println(bestSolution.getRepresentation());
			}
			return bestSolution;
		}
}
