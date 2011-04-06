package pso.function;

import common.TicksStoppingCriteria;
import common.UnconditionalTransitionCriteria;

import pso.Context;
import pso.PSOAlgorithm;
import pso.Selector;

import functions.*;

public class Main {

	public static void main(String[] args) {
		
		Functions f = new Brown1();
		FunctionEvaluator evaluator = new FunctionEvaluator(f);
		FunctionInitializator init = new FunctionInitializator(f);
		init.setGenerationSize(100);
		
		Context<FunctionGeneration, FunctionSolution> context = new Context<FunctionGeneration, FunctionSolution>(evaluator);
		FunctionGenerator generator = new FunctionGenerator();
		Selector<FunctionSolution, FunctionGeneration, Context<FunctionGeneration, FunctionSolution>> selector 
		= new Selector<FunctionSolution,FunctionGeneration, Context<FunctionGeneration, FunctionSolution>>(context);
		
		TicksStoppingCriteria<FunctionSolution, FunctionGeneration, Context<FunctionGeneration, FunctionSolution>> stoppingCriteria
		= new TicksStoppingCriteria<FunctionSolution, FunctionGeneration,Context<FunctionGeneration, FunctionSolution>>(300);
		
		UnconditionalTransitionCriteria<FunctionSolution,FunctionGeneration,Context<FunctionGeneration,FunctionSolution>> transitionCriteria
		= new UnconditionalTransitionCriteria<FunctionSolution, FunctionGeneration, Context<FunctionGeneration,FunctionSolution>>();
			PSOAlgorithm<FunctionSolution, FunctionGeneration, Context<FunctionGeneration,FunctionSolution>> algorithm
		= new PSOAlgorithm<FunctionSolution, FunctionGeneration, Context<FunctionGeneration,FunctionSolution>>(
			init, generator, stoppingCriteria, selector, transitionCriteria, context);
			
		long time = System.currentTimeMillis();
		FunctionSolution generation = algorithm.solve(); 
		long time1 = System.currentTimeMillis();
		time1 -=time; 
		System.out.println(generation.getRepresentation());
		System.out.println(context.getEvaluator().evaluate(generation));
		System.out.println(time1);
		
	}
	
}
