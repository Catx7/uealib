package diffevolution;

import readers.Graph;
import diffevolution.Context;
import diffevolution.Evaluator;
import diffevolution.Generator;
import diffevolution.Initializator;
import diffevolution.Selector;
import diffevolution.TransitionCriteria;
import diffevolution.StoppingCriteria;
import core.Algorithm;

public class DifferentalEvolution extends Algorithm {
	public DifferentalEvolution(Graph g, int Gn, double Cr, double F) {
		Evaluator e = new Evaluator(g.getWeights());

		Context ctx = Context.getInstance();
		ctx.e = e;
		Context.Gn = Gn;
		Context.F = F;
		Context.Cr = Cr;
		
		Initializator init = new Initializator(g.getWeights());
		Generator generator = new Generator();
		StoppingCriteria stop = new StoppingCriteria();
		Selector selector = new Selector();
		TransitionCriteria trans = new TransitionCriteria();
		
		this.init = init;
		this.generator = generator;
		this.stoppingCriteria = stop;
		this.selector = selector;
		this.transitionCriteria = trans;
	}
	
	   @SuppressWarnings("unchecked")
		public core.Generation solve() {
			core.Generation currentGeneration = this.init.getInitialGeneration();
			
			while (!this.stoppingCriteria.isSatisfied(currentGeneration)) {
				core.Generation g = generator.getNext(currentGeneration);
				core.Generation h = selector.keepTheBestSolutions(g, currentGeneration);
		
		        if (transitionCriteria.isSatisfied(currentGeneration, h)) {
		                currentGeneration = h;
		        }
			}
			Generation result = new Generation();
			result.add(((Generation) currentGeneration).get(0));
			
			return result;
	    }
	
}
