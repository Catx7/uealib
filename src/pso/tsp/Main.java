package pso.tsp;

import common.TicksStoppingCriteria;
import common.UnconditionalTransitionCriteria;
import pso.Selector;

import readers.graphs.CoordsGraphReader;
import readers.Graph;
import readers.graphs.GraphReader;

import pso.PSOAlgorithm;
import pso.Context;

public class Main {

	public static void main(String[] args) {
		GraphReader gr = new CoordsGraphReader();
		Graph graph = gr.readFromFile("graphs/gr48.txt");
		
		TSPInitializator init = new TSPInitializator(graph);
		init.setGenerationSize(48);
		TSPEvaluator evaluator = new TSPEvaluator(graph);
		
		Context<TSPGeneration, TSPSolution> context = new Context<TSPGeneration, TSPSolution>(evaluator);
		TSPGenerator generator = new TSPGenerator();
		Selector<TSPSolution, TSPGeneration, Context<TSPGeneration, TSPSolution>> selector = new Selector<TSPSolution, TSPGeneration, Context<TSPGeneration, TSPSolution>>(context);
		
		TicksStoppingCriteria<TSPSolution, TSPGeneration, Context<TSPGeneration, TSPSolution>> stoppingCriteria
		= new TicksStoppingCriteria<TSPSolution, TSPGeneration,Context<TSPGeneration, TSPSolution>>(100);
		
		UnconditionalTransitionCriteria<TSPSolution,TSPGeneration,Context<TSPGeneration,TSPSolution>> transitionCriteria
		= new UnconditionalTransitionCriteria<TSPSolution, TSPGeneration, Context<TSPGeneration,TSPSolution>>();
		PSOAlgorithm<TSPSolution, TSPGeneration, Context<TSPGeneration,TSPSolution>> algorithm
		= new PSOAlgorithm<TSPSolution, TSPGeneration, Context<TSPGeneration,TSPSolution>>(
			init, generator, stoppingCriteria, selector, transitionCriteria, context);
		
		long time = System.currentTimeMillis();
		TSPSolution generation = algorithm.solve(); 
		long time1 = System.currentTimeMillis();
		time1 -=time; 
		System.out.println(generation.getRepresentation());
		System.out.println(context.getEvaluator().evaluate(generation));
		System.out.println(time1);
		
	}

}
