package simulatedannealing.tsp;

import java.io.File;

import readers.Graph;
import readers.graphs.CoordsGraphReader;
import readers.graphs.GraphReader;
import simulatedannealing.ChartTracer;
import simulatedannealing.Evaluator;
import simulatedannealing.GenerationList;
import simulatedannealing.SimulatedAnnealingAlgorithm;
import simulatedannealing.ChartTracer.Type;
import core.Generator;
import core.Initializator;

public class Main {
	public static void main(String[] args) throws Exception {
		File logsDir = new File("./logs/");
		logsDir.mkdir();
		
		ChartTracer tracer = new ChartTracer(Type.IterationToFitness);
		tracer.AddHeader("TaskName", "TSP");
		
		
		
		GraphReader grr = new CoordsGraphReader();
		Graph gr = grr.readFromFile(args[0]);

		tracer.AddHeader("TaskDimension", Integer.toString(gr.getVertexesNumber()));
		
		Initializator<GenerationList> i = new TSPInitializator(gr);
		Generator<GenerationList> g = new TSPGenerator();
		Evaluator e = new TSPEvaluator(gr);

		SimulatedAnnealingAlgorithm alg = new SimulatedAnnealingAlgorithm(e, i,
				g);
		alg.setIterationsPerStage(400*gr.getVertexesNumber());
		alg.setChartTracer(tracer);

		GenerationList r = alg.solve();
		TSPWay s = (TSPWay) r.get(0);
		System.out.println(e.evaluate(s));
		
		tracer.serializeToFile(logsDir);
	}
}
