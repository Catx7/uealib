package diffevolution.functions;

import common.TicksStoppingCriteria;
import common.UnconditionalTransitionCriteria;
import functions.*;
import diffevolution.Context;
import diffevolution.DifferentialEvolutionAlgorithm;
import diffevolution.Selector;
import diffevolution.SimpleArrayCrossover;

public class Main1 {

	public static void main(String[] args) {
		F1 fun = new F1();
		FInitializator init = new FInitializator(fun);
		init.setGenerationSize(255);
		
		FEvaluator evaluator = new FEvaluator(fun);
		SimpleArrayCrossover<FSolution> strategy = new SimpleArrayCrossover<FSolution>();
		Context<FGeneration, FSolution, SimpleArrayCrossover<FSolution>> context = new Context<FGeneration, FSolution, SimpleArrayCrossover<FSolution>>(evaluator, strategy);
		FGenerator generator = new FGenerator(context);
		Selector<FSolution, FGeneration, SimpleArrayCrossover<FSolution>,Context<FGeneration, FSolution, SimpleArrayCrossover<FSolution>>> selector 
		= new Selector<FSolution,FGeneration, SimpleArrayCrossover<FSolution>,Context<FGeneration, FSolution, SimpleArrayCrossover<FSolution>>>(context);
		
		TicksStoppingCriteria<FSolution, FGeneration, Context<FGeneration, FSolution, SimpleArrayCrossover<FSolution>>> stoppingCriteria
		= new TicksStoppingCriteria<FSolution, FGeneration,Context<FGeneration, FSolution, SimpleArrayCrossover<FSolution>>>(context, 300);
		
		UnconditionalTransitionCriteria<FSolution,FGeneration,Context<FGeneration, FSolution, SimpleArrayCrossover<FSolution>>> transitionCriteria
		= new UnconditionalTransitionCriteria<FSolution, FGeneration, Context<FGeneration, FSolution, SimpleArrayCrossover<FSolution>>>();
	//	Generation generation = i.getInitialGeneration();
		DifferentialEvolutionAlgorithm<FSolution, FGeneration,SimpleArrayCrossover<FSolution>, Context<FGeneration, FSolution, SimpleArrayCrossover<FSolution>>> algorithm
		= new DifferentialEvolutionAlgorithm<FSolution, FGeneration,SimpleArrayCrossover<FSolution>, Context<FGeneration, FSolution, SimpleArrayCrossover<FSolution>>>(
			init, generator, stoppingCriteria, selector, transitionCriteria, context);
		FGeneration generation = algorithm.solve(); 
		System.out.println(generation.get(0).getRepresentation());
	//	for (int i = 0; i < generation.size(); ++i)
		 System.out.println(evaluator.evaluate(generation.get(0)));
	}

}
