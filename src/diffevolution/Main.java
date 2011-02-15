package diffevolution;

import common.TicksStoppingCriteria;
import common.UnconditionalTransitionCriteria;

import diffevolution.tsp.TSPGenerator;
import diffevolution.tsp.TSPInitializator;
import diffevolution.tsp.TSPSolution;
import readers.graphs.CoordsGraphReader;
import readers.Graph;
import readers.graphs.GraphReader;
import diffevolution.DifferentialEvolutionAlgorithm;

import diffevolution.tsp.TSPGeneration;
import diffevolution.Context;
import diffevolution.tsp.TSPEvaluator;

public class Main {

	public static void main(String[] args) {
		GraphReader gr = new CoordsGraphReader();
		Graph graph = gr.readFromFile("graphs/burma14.txt");
		
		TSPInitializator init = new TSPInitializator(graph);
		TSPEvaluator evaluator = new TSPEvaluator(graph);
		Context<TSPGeneration, TSPSolution> context = new Context<TSPGeneration, TSPSolution>(evaluator);
		TSPGenerator<Context<TSPGeneration, TSPSolution>> generator = new TSPGenerator<Context<TSPGeneration, TSPSolution>>();
		Selector<TSPSolution, TSPGeneration, Context<TSPGeneration, TSPSolution>> selector = new Selector<TSPSolution, TSPGeneration, Context<TSPGeneration, TSPSolution>>(context);
		
		TicksStoppingCriteria<TSPSolution, TSPGeneration, Context<TSPGeneration, TSPSolution>> stoppingCriteria
		= new TicksStoppingCriteria<TSPSolution, TSPGeneration,Context<TSPGeneration, TSPSolution>>(context, 300);
		
		UnconditionalTransitionCriteria<TSPSolution,TSPGeneration,Context<TSPGeneration,TSPSolution>> transitionCriteria
		= new UnconditionalTransitionCriteria<TSPSolution, TSPGeneration, Context<TSPGeneration,TSPSolution>>();
	//	Generation generation = i.getInitialGeneration();
		DifferentialEvolutionAlgorithm<TSPSolution, TSPGeneration, Context<TSPGeneration,TSPSolution>> algorithm
		= new DifferentialEvolutionAlgorithm<TSPSolution, TSPGeneration, Context<TSPGeneration,TSPSolution>>(
			init, generator, stoppingCriteria, selector, transitionCriteria, context);
		TSPGeneration generation = algorithm.solve(); 
		System.out.println(generation.getRepresentation());
		System.out.println(context.getEvaluator().evaluate(generation.get(0)));
		//DifferentalEvolution de = new DifferentalEvolution(g, 15, 0.1, 1);
		//de.solve();
	}
}
