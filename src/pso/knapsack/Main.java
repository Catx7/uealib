package pso.knapsack;

import readers.Collection;
import readers.items.KnapsackDataReader;
import readers.items.TestReader;
import common.TicksStoppingCriteria;
import common.UnconditionalTransitionCriteria;

import pso.Context;
import pso.PSOAlgorithm;
import pso.Selector;

public class Main {

	public static void main(String[] args) {
		KnapsackDataReader reader = new TestReader();
		
		Collection c = reader.readFromFile("knapsacks/100.txt");
		KnapsackEvaluator evaluator = new KnapsackEvaluator(c);
		KnapsackInitializator init = new KnapsackInitializator(c);
		init.setGenerationSize(100);
		init.setVariaty01KnapsackProblem(1);
		
		Context<KnapsackGeneration, KnapsackSolution> context = new Context<KnapsackGeneration, KnapsackSolution>(evaluator);
		KnapsackGenerator generator = new KnapsackGenerator();
		Selector<KnapsackSolution, KnapsackGeneration, Context<KnapsackGeneration, KnapsackSolution>> selector 
		= new Selector<KnapsackSolution,KnapsackGeneration, Context<KnapsackGeneration, KnapsackSolution>>(context);
		
		TicksStoppingCriteria<KnapsackSolution, KnapsackGeneration, Context<KnapsackGeneration, KnapsackSolution>> stoppingCriteria
		= new TicksStoppingCriteria<KnapsackSolution, KnapsackGeneration,Context<KnapsackGeneration, KnapsackSolution>>(100);
		
		UnconditionalTransitionCriteria<KnapsackSolution,KnapsackGeneration,Context<KnapsackGeneration,KnapsackSolution>> transitionCriteria
		= new UnconditionalTransitionCriteria<KnapsackSolution, KnapsackGeneration, Context<KnapsackGeneration,KnapsackSolution>>();
		PSOAlgorithm<KnapsackSolution, KnapsackGeneration, Context<KnapsackGeneration,KnapsackSolution>> algorithm
		= new PSOAlgorithm<KnapsackSolution, KnapsackGeneration, Context<KnapsackGeneration,KnapsackSolution>>(
			init, generator, stoppingCriteria, selector, transitionCriteria, context);
		long time = System.currentTimeMillis();
		KnapsackSolution generation = algorithm.solve(); 
		long time1 = System.currentTimeMillis();
		time1 -=time; 
		System.out.println(generation.getRepresentation());
		//context.getEvaluator().evaluate(generation);
		System.out.println(context.getEvaluator().evaluate(generation));
		System.out.println(time1);
		
	}
	
}
