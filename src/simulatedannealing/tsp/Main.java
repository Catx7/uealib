package simulatedannealing.tsp;

import java.io.File;

import readers.Graph;
import readers.graphs.CoordsGraphReader;
import readers.graphs.GraphReader;
import simulatedannealing.ChartTracer;
import simulatedannealing.IEvaluator;
import simulatedannealing.IGenerator;
import simulatedannealing.IInitializator;
import simulatedannealing.SimulatedAnnealingAlgorithm;
import simulatedannealing.ChartTracer.Type;

public class Main {
	public static void main(String[] args) throws Exception {
		File logsDir = new File("./logs/");
		logsDir.mkdir();
		
		ChartTracer tracer = new ChartTracer(Type.IterationToFitness);
		tracer.AddHeader("TaskName", "TSP");
		
		
		
		GraphReader grr = new CoordsGraphReader();
		Graph gr = grr.readFromFile(args[0]);

		tracer.AddHeader("TaskDimension", Integer.toString(gr.getVertexesNumber()));
		
		IInitializator<TSPWay> i = new TSPInitializator(gr);
		IGenerator<TSPWay> g = new TSPGenerator();
		IEvaluator<TSPWay> e = new TSPEvaluator(gr);

		SimulatedAnnealingAlgorithm<TSPWay> alg = new SimulatedAnnealingAlgorithm<TSPWay>(e, i,
				g);
		alg.setIterationsPerStage(400*gr.getVertexesNumber());
		alg.setChartTracer(tracer);

		TSPWay s = alg.solve();
		System.out.println(e.evaluate(s));
		
		tracer.serializeToFile(logsDir);
	}
}
