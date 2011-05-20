package simulatedannealing.tsp;

import readers.Graph;
import readers.graphs.CoordsGraphReader;
import readers.graphs.GraphReader;
import simulatedannealing.Adaptive2AnnealProvider;
import simulatedannealing.AdaptiveAnnealProvider;
import simulatedannealing.DefaultInitialTemperatureProvider;
import simulatedannealing.IAnnealProvider;
import simulatedannealing.IEvaluator;
import simulatedannealing.IGenerator;
import simulatedannealing.IInitialTemperatureProvider;
import simulatedannealing.IInitializator;
import simulatedannealing.ITemperatureShedule;
import simulatedannealing.SimulatedAnnealingAlgorithm;
import simulatedannealing.SimulatedAnnealingContext;
import simulatedannealing.TemperatureShedule;

public class DummyMain {
	public static void main(String[] args) throws Exception {

		GraphReader grr = new CoordsGraphReader();
		Graph gr = grr.readFromFile("./graphs/coordsgraphs/pr107.txt");

		
		IInitializator<TSPWay> i = new TSPInitializator(gr);
		IGenerator<TSPWay> g = new TSPGenerator();
		IEvaluator<TSPWay> e = new TSPEvaluator(gr);
		
		SimulatedAnnealingContext ctx = new SimulatedAnnealingContext();
		IInitialTemperatureProvider temperatureProvider = new DefaultInitialTemperatureProvider(i, g, e);
		IAnnealProvider annealProvider = new Adaptive2AnnealProvider(ctx, 0.2);
		ITemperatureShedule shedule = new TemperatureShedule(temperatureProvider, annealProvider);

		SimulatedAnnealingAlgorithm<TSPWay> alg = new SimulatedAnnealingAlgorithm<TSPWay>(e, i, g, shedule, ctx);
		alg.setIterationsPerStage(400*gr.getVertexesNumber());

		long time = System.currentTimeMillis();
		TSPWay s = alg.solve();
		System.out.println(System.currentTimeMillis()-time);
		System.out.println(e.evaluate(s));
		
	}
}
