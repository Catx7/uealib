package simulatedannealing.tsp;

import readers.graphs.CoordsGraphReader;
import readers.Graph;
import readers.graphs.GraphReader;
import simulatedannealing.Evaluator;
import simulatedannealing.GenerationList;
import simulatedannealing.SimulatedAnnealingAlgorithm;
import core.Generator;
import core.Initializator;

public class Main {
	public static void main(String[] args) {
		GraphReader grr = new CoordsGraphReader();
		
		Graph gr = grr.readFromFile("graphs/burma14.txt");
		
		Initializator<GenerationList> i = new TSPInitializator(gr);
		Generator<GenerationList> g = new TSPGenerator();
		Evaluator e = new TSPEvaluator(gr);
		
		SimulatedAnnealingAlgorithm alg = new SimulatedAnnealingAlgorithm(e, i, g);
		
		GenerationList r = alg.solve();
		TSPWay s = (TSPWay)r.get(0);
		System.out.println(e.evaluate(s));
	}
}
