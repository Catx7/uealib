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
		Graph graph = gr.readFromFile("graphs/att48.txt");
		
		TSPInitializator init = new TSPInitializator(graph);
		init.setGenerationSize(200);
		TSPEvaluator evaluator = new TSPEvaluator(graph);
		SimpleArrayCrossover strategy = new SimpleArrayCrossover();
		Context<TSPGeneration, TSPSolution, SimpleArrayCrossover<TSPSolution>> context = new Context<TSPGeneration, TSPSolution, SimpleArrayCrossover<TSPSolution>>(evaluator, strategy);
		TSPGenerator generator = new TSPGenerator(context);
		Selector<TSPSolution, TSPGeneration, SimpleArrayCrossover<TSPSolution>, Context<TSPGeneration, TSPSolution, SimpleArrayCrossover<TSPSolution>>> selector = new Selector<TSPSolution, TSPGeneration, SimpleArrayCrossover<TSPSolution>, Context<TSPGeneration, TSPSolution, SimpleArrayCrossover<TSPSolution>>>(context);
		
		TicksStoppingCriteria<TSPSolution, TSPGeneration, Context<TSPGeneration, TSPSolution, SimpleArrayCrossover<TSPSolution>>> stoppingCriteria
		= new TicksStoppingCriteria<TSPSolution, TSPGeneration,Context<TSPGeneration, TSPSolution, SimpleArrayCrossover<TSPSolution>>>(context, 300);
		
		UnconditionalTransitionCriteria<TSPSolution,TSPGeneration,Context<TSPGeneration,TSPSolution, SimpleArrayCrossover<TSPSolution>>> transitionCriteria
		= new UnconditionalTransitionCriteria<TSPSolution, TSPGeneration, Context<TSPGeneration,TSPSolution, SimpleArrayCrossover<TSPSolution>>>();
	//	Generation generation = i.getInitialGeneration();
		DifferentialEvolutionAlgorithm<TSPSolution, TSPGeneration, SimpleArrayCrossover<TSPSolution>, Context<TSPGeneration,TSPSolution, SimpleArrayCrossover<TSPSolution>>> algorithm
		= new DifferentialEvolutionAlgorithm<TSPSolution, TSPGeneration, SimpleArrayCrossover<TSPSolution>, Context<TSPGeneration,TSPSolution, SimpleArrayCrossover<TSPSolution>>>(
			init, generator, stoppingCriteria, selector, transitionCriteria, context);
		TSPGeneration generation = algorithm.solve(); 
		System.out.println(generation.getRepresentation());
		System.out.println(context.getEvaluator().evaluate(generation.get(0)));
		//DifferentalEvolution de = new DifferentalEvolution(g, 15, 0.1, 1);
		//de.solve();
	}
}
