package simulatedannealing.knapsack;

import java.io.File;

import readers.Graph;
import readers.KnapsackTask;
import readers.graphs.CoordsGraphReader;
import readers.graphs.GraphReader;
import readers.items.KnapsackDataReader;
import readers.items.KnapsackTestReader;
import simulatedannealing.ChartTracer;
import simulatedannealing.ChartTracer.Type;
import simulatedannealing.IEvaluator;
import simulatedannealing.IGenerator;
import simulatedannealing.IInitializator;
import simulatedannealing.SimulatedAnnealingAlgorithm;

public class ResearchMain {
	public static void main(String[] args) throws Exception {
		File logsDir = new File("./logs/");
		logsDir.mkdir();
		
		ChartTracer tracer = new ChartTracer(Type.IterationToFitness);
		tracer.AddHeader("TaskName", "KnapsackResearch");
		
		
		
		KnapsackDataReader reader = new KnapsackTestReader();
		KnapsackTask task = reader.readFromFile("./knapsacks/500.txt");

		tracer.AddHeader("TaskDimension", Integer.toString(task.getItemsNumber()));
		
		IInitializator<ItemSet> i = new KnapsackInitializator(task);
		IGenerator<ItemSet> g = new KnapsackGenerator(task);
		IEvaluator<ItemSet> e = new KnapsackEvaluator(task);

		for(double c = 0.01; c<1; c +=0.02)
		{
			double sum = 0;
			int count = 5;
			for(int j=0; j<count; ++j) 
			{
				SimulatedAnnealingAlgorithm<ItemSet> alg = new SimulatedAnnealingAlgorithm<ItemSet>(e, i, g, c);
				alg.setIterationsPerStage(task.getItemsNumber());
	
				ItemSet s = alg.solve();
				
				sum += e.evaluate(s);
			}
			sum = sum / count;
			System.out.println(c+"============");
			tracer.AddPoint(c, sum);
		}
		tracer.serializeToFile(logsDir);
	}
}
