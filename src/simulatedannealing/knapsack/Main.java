package simulatedannealing.knapsack;

import java.util.Date;

import core.Generator;
import core.Initializator;
import readers.Collection;
import readers.items.*;
import simulatedannealing.Evaluator;
import simulatedannealing.GenerationList;
import simulatedannealing.SimulatedAnnealingAlgorithm;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		KnapsackDataReader kdr = new TestReader();
		Collection problem = kdr.readFromFile("knapsacks/knap500.txt");

		Initializator<GenerationList> i = new KnapsackInitializator(problem);
		Evaluator e = new KnapsackEvaluator(problem);
		Generator<GenerationList> g = new KnapsackGenerator(problem);

		SimulatedAnnealingAlgorithm alg = new SimulatedAnnealingAlgorithm(e, i,
				g);

		long start = (new Date()).getTime();
		ItemSet sol = (ItemSet) alg.solve().get(0);
		System.out.println((new Date()).getTime() - start);

		System.out.println(e.evaluate(sol));

		return;

	}

}
