package simulatedannealing.continous;

import simulatedannealing.Evaluator;
import simulatedannealing.GenerationList;
import simulatedannealing.SimulatedAnnealingAlgorithm;
import core.Generator;
import core.Initializator;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Initializator<GenerationList> i = new FuncInitializator();
		Evaluator e = new FuncEvaluator();
		Generator<GenerationList> g = new FuncGenerator();

		SimulatedAnnealingAlgorithm alg = new SimulatedAnnealingAlgorithm(e, i,
				g);

		Point a = (Point) alg.solve().get(0);

		System.out.print(a.x + " " + a.y);

	}

}
