package simulatedannealing.continous;

import java.io.File;

import readers.FunctionReader;
import simulatedannealing.ChartTracer;
import simulatedannealing.ChartTracer.Type;
import simulatedannealing.IEvaluator;
import simulatedannealing.IGenerator;
import simulatedannealing.IInitializator;
import simulatedannealing.SimulatedAnnealingAlgorithm;
import usablefunctions.Branin;
import usablefunctions.Brown1;
import usablefunctions.Brown3;
import usablefunctions.Function;
import usablefunctions.Shubert2;
import core.Generator;
import core.Initializator;

public class DummyMain {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		
		
		Function func = FunctionReader.getFunction("Brown1");
		
		IInitializator<Point> i = new FuncInitializator(func.getDomain());
		IEvaluator<Point> e = new FuncEvaluator(func);
		IGenerator<Point> g = new FuncGenerator(func.getDomain());

		SimulatedAnnealingAlgorithm<Point> alg = new SimulatedAnnealingAlgorithm<Point>(e, i, g);
		
		alg.setIterationsPerStage(800);
		
		long time = System.currentTimeMillis();
		Point r = alg.solve();
		System.out.println(System.currentTimeMillis()-time);

		System.out.print(e.evaluate(r));

	}

}
