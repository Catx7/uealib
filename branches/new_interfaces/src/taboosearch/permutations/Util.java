package taboosearch.permutations;

import common.AbstractGenerationFabric;
import common.TicksStoppingCriteria;

import taboosearch.AdmissibilityChecker;
import taboosearch.EliteCandidateList;
import taboosearch.Initializator;
import taboosearch.Context;
import taboosearch.Evaluator;
import taboosearch.Generation;
import taboosearch.Selector;
import taboosearch.TabooSearchAlgorithm;
import taboosearch.Taboolator;
import taboosearch.tenures.ConstantTenureStrategy;

public class Util<S extends Solution, M extends Move<S>, G extends Generation<S>>  {
	
	public TabooSearchAlgorithm<S, M, G, Context<S, M, G>> getAlgorithm(			
			Evaluator<S, M> evaluator,
			Initializator<S, G> initializator,
			MoveFabric<S, M> moveFabric,
			int n) {
		
		Taboolator<S, M> taboolator
			= new Taboolator<S, M>(new ConstantTenureStrategy(18));
		
		AdmissibilityChecker<S, M> checker
			= new AdmissibilityChecker<S, M>(evaluator, taboolator);
	
		EliteCandidateList<S, M> eliteList
			= new EliteCandidateList<S, M>(1, checker, evaluator);
	
		Generator<S, M, G> generator = new Generator<S, M, G>(n, moveFabric);
	
		
		Context<S, M, G> context = new Context<S, M, G>();
		
		TicksStoppingCriteria<S, G,	Context<S, M, G>> stoppingCriteria
			= new TicksStoppingCriteria<S, G, Context<S, M, G>>(20000);
		
		
		Selector<S, M, G, Context<S, M, G>> selector = new Selector<S, M, G, Context<S, M, G>>
				(evaluator, taboolator, evaluator.getFrequencyMemory(),
				 checker, eliteList, initializator.getGenerationFabric(), context);
		
		return new TabooSearchAlgorithm<S, M, G, Context<S, M, G>>(
				initializator,
				generator,
				stoppingCriteria,
				selector,
				context);
	}
}
