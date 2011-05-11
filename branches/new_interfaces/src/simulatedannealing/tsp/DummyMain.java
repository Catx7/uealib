package simulatedannealing.tsp;

import readers.Graph;
import readers.graphs.CoordsGraphReader;
import readers.graphs.GraphReader;
import simulatedannealing.IEvaluator;
import simulatedannealing.IGenerator;
import simulatedannealing.IInitializator;
import simulatedannealing.SimulatedAnnealingAlgorithm;

public class DummyMain {
	public static void main(String[] args) throws Exception {

		GraphReader grr = new CoordsGraphReader();
		Graph gr = grr.readFromFile("./graphs/coordsgraphs/pr107.txt");

		
		IInitializator<TSPWay> i = new TSPInitializator(gr);
		IGenerator<TSPWay> g = new TSPGenerator();
		IEvaluator<TSPWay> e = new TSPEvaluator(gr);

		SimulatedAnnealingAlgorithm<TSPWay> alg = new SimulatedAnnealingAlgorithm<TSPWay>(e, i, g);
		alg.setIterationsPerStage(400*gr.getVertexesNumber());

		long time = System.currentTimeMillis();
		TSPWay s = alg.solve();
		System.out.println(System.currentTimeMillis()-time);
		System.out.println(e.evaluate(s));
		
	}
}
