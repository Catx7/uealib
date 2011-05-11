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

public class DummyMain {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		
		
		KnapsackDataReader kdr = new KnapsackTestReader();
		KnapsackTask problem = kdr.readFromFile("./knapsacks/2000.txt");
		


		IInitializator<ItemSet> i = new KnapsackInitializator(problem);
		IEvaluator<ItemSet> e = new KnapsackEvaluator(problem);
		IGenerator<ItemSet> g = new KnapsackGenerator(problem);

		SimulatedAnnealingAlgorithm<ItemSet> alg = new SimulatedAnnealingAlgorithm<ItemSet>(e, i, g);
		alg.setIterationsPerStage(problem.getItemsNumber());


		long time = System.currentTimeMillis();
		ItemSet r = alg.solve();
		System.out.println(System.currentTimeMillis()-time);

		System.out.println(e.evaluate(r));

		return;
	}
}
