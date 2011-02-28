package taboosearch.permutations;

import java.util.ArrayList;

import common.Fabric;
import common.Pair;
import common.TicksStoppingCriteria;

import taboosearch.AdmissibilityChecker;
import taboosearch.EliteCandidateList;
import taboosearch.Initializator;
import taboosearch.Context;
import taboosearch.Evaluator;
import taboosearch.Generation;
import taboosearch.ParallelTabooSearchAlgorithm;
import taboosearch.Selector;
import taboosearch.TabooSearchAlgorithm;
import taboosearch.Taboolator;
import taboosearch.Tickable;
import taboosearch.tenures.ConstantTenureStrategy;

public class Util<S extends Solution, M extends Move<S>, G extends Generation<S>>  {
	
	public TabooSearchAlgorithm<S, M, G, Context<S, M, G>> getAlgorithm(			
			AbstractGenerationAndSolutionFabric<S, G> fabric,
			AbstractMoveFabric<S, M> moveFabric,
			Initializator<S, G> initializator,
			Evaluator<S, M> evaluator,
			FrequencyMemory<S, M> frequencyMemory,
			int dimensionality,
			int tabooTenure,
			int eliteListSize,
			int numberOfIterations) {
		
		Context<S, M, G> context = new Context<S, M, G>();
		
		Taboolator<S, M> taboolator
			= new Taboolator<S, M>(new ConstantTenureStrategy(tabooTenure));
		
		AdmissibilityChecker<S, M> checker
			= new AdmissibilityChecker<S, M>(evaluator, taboolator);
	
		EliteCandidateList<S, M> eliteList
			= new EliteCandidateList<S, M>(eliteListSize, checker, evaluator);
	
		Generator<S, M, G> generator = new Generator<S, M, G>(dimensionality, moveFabric);
		
		TicksStoppingCriteria<S, G,	Context<S, M, G>> stoppingCriteria
			= new TicksStoppingCriteria<S, G, Context<S, M, G>>(numberOfIterations);
		
		ArrayList<Tickable<S, M>> ok = new ArrayList<Tickable<S, M>>();
		ok.add(taboolator);
		ok.add(frequencyMemory);
		ok.add(eliteList);
		
		Selector<S, M, G, Context<S, M, G>> selector = new Selector<S, M, G, Context<S, M, G>>
				(evaluator, checker, eliteList, fabric, ok, context);
		
		return new TabooSearchAlgorithm<S, M, G, Context<S, M, G>>(
				initializator,
				generator,
				stoppingCriteria,
				selector,
				context);
	}
	
	abstract class SelectorFabric implements Fabric<Selector<S, M, G, Context<S, M, G>>> {
		protected Context<S, M, G> context;
		public SelectorFabric(Context<S, M, G> context) {
			this.context = context;
		}		
	}
	
	public ParallelTabooSearchAlgorithm<S, M, G, Context<S, M, G>> getParallelAlgorithm(			
			final AbstractGenerationAndSolutionFabric<S, G> fabric,
			final AbstractMoveFabric<S, M> moveFabric,
			final Initializator<S, G> initializator,
			final Fabric<Pair<FrequencyMemory<S, M>, Evaluator<S, M>>> evaluatorFabric,
			final int dimensionality,
			final int tabooTenure,
			final int eliteListSize,
			final int numberOfIterations) {
		
		Context<S, M, G> context = new Context<S, M, G>();
		
		Fabric<taboosearch.Generator<S, M, G>> generatorFabric
			= new Fabric<taboosearch.Generator<S, M, G>>() {
				public taboosearch.Generator<S, M, G> getInstance() {
					return new Generator<S, M, G>(dimensionality, moveFabric);
				}
			  };
		
		Fabric<Selector<S, M, G, Context<S, M, G>>> selectorFabric
			= new SelectorFabric(context) {
				@Override
				public Selector<S, M, G, Context<S, M, G>> getInstance() {
					Taboolator<S, M> taboolator	= new Taboolator<S, M>(new ConstantTenureStrategy(tabooTenure));
					
					Pair<FrequencyMemory<S, M>, Evaluator<S, M>> pair = evaluatorFabric.getInstance();
					Evaluator<S, M> evaluator = pair.getSecond();
					FrequencyMemory<S, M> frequencyMemory = pair.getFirst();
					
					AdmissibilityChecker<S, M> checker
						= new AdmissibilityChecker<S, M>(evaluator, taboolator);
					EliteCandidateList<S, M> eliteList
						= new EliteCandidateList<S, M>(eliteListSize, checker, evaluator);
					
					ArrayList<Tickable<S, M>> tickables = new ArrayList<Tickable<S, M>>();
					tickables.add(taboolator);
					tickables.add(frequencyMemory);
					tickables.add(eliteList);
					
					return new Selector<S, M, G, Context<S, M, G>>(
							evaluator, checker, eliteList, fabric, tickables, context);
				}
			  };
				
		TicksStoppingCriteria<S, G,	Context<S, M, G>> stoppingCriteria
			= new TicksStoppingCriteria<S, G, Context<S, M, G>>(numberOfIterations);
		
		
		return new taboosearch.permutations.ParallelTabooSearchAlgorithm<S, M, G, Context<S, M, G>>(
				dimensionality,
				initializator,
				generatorFabric,
				stoppingCriteria,
				selectorFabric,
				context);
	}
}
