package diffevolution.knapsack;

import readers.KnapsackTask;
import readers.items.KnapsackDataReader;
import readers.items.KnapsackTestReader;
import common.TicksStoppingCriteria;
import common.UnconditionalTransitionCriteria;

import diffevolution.Context;
import diffevolution.DifferentialEvolutionAlgorithm;
import diffevolution.Selector;
import diffevolution.SimpleArrayCrossover;

// http://people.brunel.ac.uk/~mastjjb/jeb/orlib/mknapinfo.html


public class Main {

	public static void main(String[] args) {
		KnapsackDataReader reader = new KnapsackTestReader();
		
		KnapsackTask c = reader.readFromFile("src/diffevolution/knapsack/item.txt");
		KnapsackEvaluator evaluator = new KnapsackEvaluator(c);
		KnapsackInitializator init = new KnapsackInitializator(c);
		init.setGenerationSize(500);
		init.setVariaty01KnapsackProblem();
	//	KnapsackGeneration generation = init.getInitialGeneration();
		SimpleArrayCrossover<KnapsackSolution> strategy = new SimpleArrayCrossover<KnapsackSolution>();
		Context<KnapsackGeneration, KnapsackSolution, SimpleArrayCrossover<KnapsackSolution>> context = new Context<KnapsackGeneration, KnapsackSolution, SimpleArrayCrossover<KnapsackSolution>>(evaluator, strategy);
		KnapsackGenerator generator = new KnapsackGenerator(context);
		Selector<KnapsackSolution, KnapsackGeneration, SimpleArrayCrossover<KnapsackSolution>, Context<KnapsackGeneration, KnapsackSolution, SimpleArrayCrossover<KnapsackSolution>>> selector 
		= new Selector<KnapsackSolution,KnapsackGeneration, SimpleArrayCrossover<KnapsackSolution>, Context<KnapsackGeneration, KnapsackSolution, SimpleArrayCrossover<KnapsackSolution>>>(context);
		
		TicksStoppingCriteria<KnapsackSolution, KnapsackGeneration, Context<KnapsackGeneration, KnapsackSolution, SimpleArrayCrossover<KnapsackSolution>>> stoppingCriteria
		= new TicksStoppingCriteria<KnapsackSolution, KnapsackGeneration,Context<KnapsackGeneration, KnapsackSolution, SimpleArrayCrossover<KnapsackSolution>>>(context, 300);
		
		UnconditionalTransitionCriteria<KnapsackSolution,KnapsackGeneration,Context<KnapsackGeneration,KnapsackSolution, SimpleArrayCrossover<KnapsackSolution>>> transitionCriteria
		= new UnconditionalTransitionCriteria<KnapsackSolution, KnapsackGeneration, Context<KnapsackGeneration,KnapsackSolution, SimpleArrayCrossover<KnapsackSolution>>>();
	//	Generation generation = i.getInitialGeneration();
		DifferentialEvolutionAlgorithm<KnapsackSolution, KnapsackGeneration, SimpleArrayCrossover<KnapsackSolution>, Context<KnapsackGeneration,KnapsackSolution, SimpleArrayCrossover<KnapsackSolution>>> algorithm
		= new DifferentialEvolutionAlgorithm<KnapsackSolution, KnapsackGeneration, SimpleArrayCrossover<KnapsackSolution>, Context<KnapsackGeneration,KnapsackSolution, SimpleArrayCrossover<KnapsackSolution>>>(
			init, generator, stoppingCriteria, selector, transitionCriteria, context);
		KnapsackGeneration generation = algorithm.solve(); 
		System.out.println(generation.getBest().getRepresentation());
	//	for (int i = 0; i < generation.size(); ++i)
		System.out.println(evaluator.getFitness(generation.get(0))); 
	}
	
}
