package simulatedannealing.continous;

import java.io.File;

import readers.FunctionReader;
import simulatedannealing.ChartTracer;
import simulatedannealing.ChartTracer.Type;
import simulatedannealing.IEvaluator;
import simulatedannealing.IGenerator;
import simulatedannealing.IInitializator;
import simulatedannealing.SimulatedAnnealingAlgorithm;
import usablefunctions.Function;

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
		
		Function func = FunctionReader.getFunction("Brown1");
		
		IInitializator<Point> i = new FuncInitializator(func.getDomain());
		IEvaluator<Point> e = new FuncEvaluator(func);
		IGenerator<Point> g = new FuncGenerator(func.getDomain());

		SimulatedAnnealingAlgorithm<Point> alg = new SimulatedAnnealingAlgorithm<Point>(e, i, g);
		
		alg.setIterationsPerStage(800);
		
		alg.setChartTracer(tracer);
		

		Point a = alg.solve();
		
		tracer.serializeToFile(logDir);

		System.out.print(a.toString());

	}

}
