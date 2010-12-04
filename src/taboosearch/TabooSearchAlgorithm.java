package taboosearch;

import core.Algorithm;
import core.StoppingCriteria;
import readers.Graph;
import taboosearch.tenures.ConstantTenureStrategy;


public class TabooSearchAlgorithm extends Algorithm {
	
	public TabooSearchAlgorithm(Graph g) {
		Evaluator e = new Evaluator(g.getWeights());
		Taboolator tr = new Taboolator(new ConstantTenureStrategy(5));
		
		Context ctx = Context.getInstance();
		ctx.e = e;
		ctx.tr = tr;
		
		Initializator init = new Initializator(g.getWeights());
		Generator generator = new Generator();
		StoppingCriteria stop = new TickStoppingCriteria(50);
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
	        
			Context.getInstance().tick();
			System.out.print(((Solution)currentGeneration.get(0)).toString());
			System.out.println(((Solution)currentGeneration.get(0)).getFitness());
			
		}
		
		return currentGeneration;
    }

}
