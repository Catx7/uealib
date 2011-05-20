package simulatedannealing.continous;

import readers.FunctionReader;
import simulatedannealing.IEvaluator;
import simulatedannealing.IGenerator;
import simulatedannealing.IInitializator;
import simulatedannealing.SimulatedAnnealingAlgorithm;
import usablefunctions.Function;

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
