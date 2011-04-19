package simulatedannealing.continous;

import java.io.File;

import readers.FunctionReader;
import simulatedannealing.ChartTracer;
import simulatedannealing.ChartTracer.Type;
import simulatedannealing.Evaluator;
import simulatedannealing.GenerationList;
import simulatedannealing.SimulatedAnnealingAlgorithm;
import usablefunctions.Branin;
import usablefunctions.Brown1;
import usablefunctions.Brown3;
import usablefunctions.Function;
import usablefunctions.Shubert2;
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
		
		Function func = FunctionReader.getFunction("Brown1");
		
		Initializator<GenerationList> i = new FuncInitializator(func.getDomain());
		Evaluator e = new FuncEvaluator(func);
		Generator<GenerationList> g = new FuncGenerator(func.getDomain());

		SimulatedAnnealingAlgorithm alg = new SimulatedAnnealingAlgorithm(e, i,
				g);
		
		alg.setIterationsPerStage(800);
		
		alg.setChartTracer(tracer);
		

		Point a = (Point) alg.solve().get(0);
		
		tracer.serializeToFile(logDir);

		System.out.print(a.toString());

	}

}
