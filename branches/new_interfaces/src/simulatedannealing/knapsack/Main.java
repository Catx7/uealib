package simulatedannealing.knapsack;

import java.io.File;

import readers.KnapsackTask;
import readers.items.KnapsackDataReader;
import readers.items.KnapsackTestReader;
import simulatedannealing.ChartTracer;
import simulatedannealing.ChartTracer.Type;
import simulatedannealing.IEvaluator;
import simulatedannealing.IGenerator;
import simulatedannealing.IInitializator;
import simulatedannealing.SimulatedAnnealingAlgorithm;
import core.Generator;
import core.Initializator;

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

		IInitializator<ItemSet> i = new KnapsackInitializator(problem);
		IEvaluator<ItemSet> e = new KnapsackEvaluator(problem);
		IGenerator<ItemSet> g = new KnapsackGenerator(problem);

		SimulatedAnnealingAlgorithm<ItemSet> alg = new SimulatedAnnealingAlgorithm<ItemSet>(e, i, g);
		alg.setIterationsPerStage(problem.getItemsNumber());
		alg.setChartTracer(tracer);

		ItemSet sol = alg.solve();

		tracer.serializeToFile(logsDir);
		System.out.println(e.evaluate(sol));

		return;
	}
}
