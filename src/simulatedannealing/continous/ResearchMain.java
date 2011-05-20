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

public class ResearchMain {
	public static void main(String[] args) throws Exception {
		File logsDir = new File("./logs/");
		logsDir.mkdir();
		
		ChartTracer tracer = new ChartTracer(Type.IterationToFitness);
		tracer.AddHeader("TaskName", "FunctionResearch");
		
		
		
		Function func = FunctionReader.getFunction("Brown1");
		
		tracer.AddHeader("TaskDimension", Integer.toString(func.getDomain().length));
		
		IInitializator<Point> i = new FuncInitializator(func.getDomain());
		IGenerator<Point> g = new FuncGenerator(func.getDomain());
		IEvaluator<Point> e = new FuncEvaluator(func);

		for(double c = 0.01; c<1; c +=0.02)
		{
			double sum = 0;
			int count = 5;
			for(int j=0; j<count; ++j) 
			{
				SimulatedAnnealingAlgorithm<Point> alg = new SimulatedAnnealingAlgorithm<Point>(e, i, g, c);
				alg.setIterationsPerStage(400*func.getDomain().length);
	
				Point s = alg.solve();
				
				sum += e.evaluate(s);
			}
			sum = sum / count;
			System.out.println(c+"============");
			tracer.AddPoint(c, sum);
		}
		tracer.serializeToFile(logsDir);
	}
}
