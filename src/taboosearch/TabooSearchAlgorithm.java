package taboosearch;

import core.Algorithm;
import readers.Graph;
import taboosearch.tenures.ConstantTenureStrategy;


public class TabooSearchAlgorithm extends Algorithm<LazyGeneration> {
	
	public TabooSearchAlgorithm(Graph g) {
		Evaluator e = new Evaluator(g.getWeights());
		Taboolator tr = new Taboolator(new ConstantTenureStrategy(5));
		
		Context ctx = Context.getInstance();
		ctx.e = e;
		ctx.tr = tr;
		
		Initializator init = new Initializator(g.getWeights());
		Generator generator = new Generator();
		TickStoppingCriteria stop = new TickStoppingCriteria(50);
		Selector selector = new Selector();
		TransitionCriteria trans = new TransitionCriteria();
		
		this.init = init;
		this.generator = generator;
		this.stoppingCriteria = stop;
		this.selector = selector;
		this.transitionCriteria = trans;
	}

	public LazyGeneration solve() {
		LazyGeneration currentGeneration = this.init.getInitialGeneration();
		
		while (!this.stoppingCriteria.isSatisfied(currentGeneration)) {
			LazyGeneration g = generator.getNext(currentGeneration);
			LazyGeneration h = selector.keepTheBestSolutions(g, currentGeneration);
	
	        if (transitionCriteria.isSatisfied(currentGeneration, h)) {
	                currentGeneration = h;
	        }
	        
			Context.getInstance().tick();
			System.out.print(currentGeneration.get(0).castToSolution().toString());
			System.out.println(currentGeneration.get(0).castToSolution().getFitness());
			
		}
		
		return currentGeneration;
    }

}
