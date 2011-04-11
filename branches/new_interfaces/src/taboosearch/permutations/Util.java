package taboosearch.permutations;

import java.util.ArrayList;

import common.Fabric;
import common.Pair;
import common.alternative.TicksStoppingCriteria;

import taboosearch.AdmissibilityChecker;
import taboosearch.EliteCandidateList;
import taboosearch.Initializator;
import taboosearch.Context;
import taboosearch.Evaluator;
import taboosearch.ParallelTabooSearchAlgorithm;
import taboosearch.Selector;
import taboosearch.TabooSearchAlgorithm;
import taboosearch.Taboolator;
import taboosearch.Tickable;
import taboosearch.tenures.ConstantTenureStrategy;

public class Util<S extends Solution, M extends Move<S>>  {
	
	public TabooSearchAlgorithm<S, M, Context<S, M>> getAlgorithm(			
			AbstractMoveFabric<S, M> moveFabric,
			Initializator<S> initializator,
			Evaluator<S, M> evaluator,
			FrequencyMemory<S, M> frequencyMemory,
			int dimensionality,
			int tabooTenure,
			int eliteListSize,
			int numberOfIterations) {
		
		Context<S, M> context = new Context<S, M>();
		
		Taboolator<S, M> taboolator
			= new Taboolator<S, M>(new ConstantTenureStrategy(tabooTenure));
		
		AdmissibilityChecker<S, M> checker
			= new AdmissibilityChecker<S, M>(evaluator, taboolator);
	
		EliteCandidateList<S, M> eliteList
			= new EliteCandidateList<S, M>(eliteListSize, checker, evaluator);
	
		Generator<S, M> generator = new Generator<S, M>(dimensionality, moveFabric);
		
		TicksStoppingCriteria<S, Context<S, M>> stoppingCriteria
			= new TicksStoppingCriteria<S, Context<S, M>>(numberOfIterations);
		
		ArrayList<Tickable<S, M>> tickables = new ArrayList<Tickable<S, M>>();
		tickables.add(taboolator);
		tickables.add(frequencyMemory);
		tickables.add(eliteList);
		
		Selector<S, M, Context<S, M>> selector =  new Selector<S, M, Context<S, M>>
				(evaluator, checker, eliteList, tickables, context);
		
		return new TabooSearchAlgorithm<S, M, Context<S, M>>(
				initializator,
				generator,
				stoppingCriteria,
				selector,
				context);
	}
	
	abstract class SelectorFabric implements Fabric<Selector<S, M, Context<S, M>>> {
		protected Context<S, M> context;
		public SelectorFabric(Context<S, M> context) {
			this.context = context;
		}		
	}
	
	public ParallelTabooSearchAlgorithm<S, M, Context<S, M>> getParallelAlgorithm(			
			final AbstractMoveFabric<S, M> moveFabric,
			final Initializator<S> initializator,
			final Fabric<Pair<FrequencyMemory<S, M>, Evaluator<S, M>>> evaluatorFabric,
			final int dimensionality,
			final int tabooTenure,
			final int eliteListSize,
			final int numberOfIterations) {
		
		Context<S, M> context = new Context<S, M>();
		
		Fabric<taboosearch.Generator<S, M>> generatorFabric
			= new Fabric<taboosearch.Generator<S, M>>() {
				public taboosearch.Generator<S, M> make() {
					return new Generator<S, M>(dimensionality, moveFabric);
				}
			  };
		
		Fabric<Selector<S, M, Context<S, M>>> selectorFabric
			= new SelectorFabric(context) {
				@Override
				public Selector<S, M, Context<S, M>> make() {
					Taboolator<S, M> taboolator	= new Taboolator<S, M>(new ConstantTenureStrategy(tabooTenure));
					
					Pair<FrequencyMemory<S, M>, Evaluator<S, M>> pair = evaluatorFabric.make();
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
					
					return new Selector<S, M, Context<S, M>>(
							evaluator, checker, eliteList, tickables, context);
				}
			  };
				
		TicksStoppingCriteria<S, Context<S, M>> stoppingCriteria
			= new TicksStoppingCriteria<S, Context<S, M>>(numberOfIterations);
		
		
		return new taboosearch.permutations.ParallelTabooSearchAlgorithm<S, M, Context<S, M>>(
				dimensionality,
				initializator,
				generatorFabric,
				stoppingCriteria,
				selectorFabric,
				context);
	}
}
