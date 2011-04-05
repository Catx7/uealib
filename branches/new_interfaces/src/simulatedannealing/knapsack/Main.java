package simulatedannealing.knapsack;

import java.io.File;
import java.util.Date;

import core.Generator;
import core.Initializator;
import readers.KnapsackTask;
import readers.items.*;
import simulatedannealing.ChartTracer;
import simulatedannealing.Evaluator;
import simulatedannealing.GenerationList;
import simulatedannealing.SimulatedAnnealingAlgorithm;
import simulatedannealing.ChartTracer.Type;

public class Main {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		File logsDir = new File("./logs/");
		logsDir.mkdir();
		
		ChartTracer tracer = new ChartTracer(Type.IterationToFitness);
		tracer.AddHeader("TaskName", "knapsack");
		
		
		KnapsackDataReader kdr = new KnapsackTestReader();
		KnapsackTask problem = kdr.readFromFile(args[0]);
		
		tracer.AddHeader("TaskDimension", Integer.toString(problem.getItemsNumber()));

		Initializator<GenerationList> i = new KnapsackInitializator(problem);
		Evaluator e = new KnapsackEvaluator(problem);
		Generator<GenerationList> g = new KnapsackGenerator(problem);

		SimulatedAnnealingAlgorithm alg = new SimulatedAnnealingAlgorithm(e, i,
				g);
		alg.setIterationsPerStage(problem.getItemsNumber());
		alg.setChartTracer(tracer);

		ItemSet sol = (ItemSet) alg.solve().get(0);

		tracer.serializeToFile(logsDir);
		System.out.println(e.evaluate(sol));

		return;
	}
}
