package simulatedannealing.continous;

import java.io.File;

import simulatedannealing.ChartTracer;
import simulatedannealing.Evaluator;
import simulatedannealing.GenerationList;
import simulatedannealing.SimulatedAnnealingAlgorithm;
import simulatedannealing.ChartTracer.Type;
import core.Generator;
import core.Initializator;

public class Main {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		File logDir = new File("./logs/");
		logDir.mkdir();
		
		ChartTracer tracer = new ChartTracer(Type.IterationToFitness);
		tracer.AddHeader("TaskName", "continous");
		tracer.AddHeader("TaskDimension", Integer.toString(2));
		
		Initializator<GenerationList> i = new FuncInitializator();
		Evaluator e = new FuncEvaluator();
		Generator<GenerationList> g = new FuncGenerator();

		SimulatedAnnealingAlgorithm alg = new SimulatedAnnealingAlgorithm(e, i,
				g);
		
		alg.setChartTracer(tracer);
		

		Point a = (Point) alg.solve().get(0);
		
		tracer.serializeToFile(logDir);

		System.out.print(a.x + " " + a.y);

	}

}
