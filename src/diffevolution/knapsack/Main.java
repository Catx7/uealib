package diffevolution.knapsack;

import readers.Collection;
import readers.items.KnapsackDataReader;
import readers.items.TestReader;
import common.TicksStoppingCriteria;
import common.UnconditionalTransitionCriteria;

import diffevolution.Context;
import diffevolution.DifferentialEvolutionAlgorithm;
import diffevolution.Selector;

// http://people.brunel.ac.uk/~mastjjb/jeb/orlib/mknapinfo.html


public class Main {

	public static void main(String[] args) {
		KnapsackDataReader reader = new TestReader();
		
		Collection c = reader.readFromFile("src/diffevolution/knapsack/item.txt");
		KnapsackEvaluator evaluator = new KnapsackEvaluator(c);
		KnapsackInitializator init = new KnapsackInitializator(c);
		init.setGenerationSize(50);
	//	init.setVariaty01KnapsackProblem();
	//	KnapsackGeneration generation = init.getInitialGeneration();
		
		Context<KnapsackGeneration, KnapsackSolution> context = new Context<KnapsackGeneration, KnapsackSolution>(evaluator);
		KnapsackGenerator<Context<KnapsackGeneration, KnapsackSolution>> generator = new KnapsackGenerator<Context<KnapsackGeneration,KnapsackSolution>>();
		Selector<KnapsackSolution, KnapsackGeneration, Context<KnapsackGeneration, KnapsackSolution>> selector 
		= new Selector<KnapsackSolution,KnapsackGeneration, Context<KnapsackGeneration, KnapsackSolution>>(context);
		
		TicksStoppingCriteria<KnapsackSolution, KnapsackGeneration, Context<KnapsackGeneration, KnapsackSolution>> stoppingCriteria
		= new TicksStoppingCriteria<KnapsackSolution, KnapsackGeneration,Context<KnapsackGeneration, KnapsackSolution>>(context, 300);
		
		UnconditionalTransitionCriteria<KnapsackSolution,KnapsackGeneration,Context<KnapsackGeneration,KnapsackSolution>> transitionCriteria
		= new UnconditionalTransitionCriteria<KnapsackSolution, KnapsackGeneration, Context<KnapsackGeneration,KnapsackSolution>>();
	//	Generation generation = i.getInitialGeneration();
		DifferentialEvolutionAlgorithm<KnapsackSolution, KnapsackGeneration, Context<KnapsackGeneration,KnapsackSolution>> algorithm
		= new DifferentialEvolutionAlgorithm<KnapsackSolution, KnapsackGeneration, Context<KnapsackGeneration,KnapsackSolution>>(
			init, generator, stoppingCriteria, selector, transitionCriteria, context);
		KnapsackGeneration generation = algorithm.solve(); 
		System.out.println(generation.getRepresentation());
		for (int i = 0; i < generation.size(); ++i)
		System.out.println(evaluator.getFitness(generation.get(i))); 
	}
	
}
