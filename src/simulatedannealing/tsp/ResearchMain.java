package simulatedannealing.tsp;

import java.io.File;

import readers.Graph;
import readers.graphs.CoordsGraphReader;
import readers.graphs.GraphReader;
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
		tracer.AddHeader("TaskName", "TSPResearch");
		
		
		
		GraphReader grr = new CoordsGraphReader();
		Graph gr = grr.readFromFile("./graphs/coordsgraphs/pr107.txt");

		tracer.AddHeader("TaskDimension", Integer.toString(gr.getVertexesNumber()));
		
		IInitializator<TSPWay> i = new TSPInitializator(gr);
		IGenerator<TSPWay> g = new TSPGenerator();
		IEvaluator<TSPWay> e = new TSPEvaluator(gr);

		for(double c = 0.01; c<1; c +=0.02)
		{
			double sum = 0;
			int count = 5;
			for(int j=0; j<count; ++j) 
			{
				SimulatedAnnealingAlgorithm<TSPWay> alg = new SimulatedAnnealingAlgorithm<TSPWay>(e, i,	g, c);
				alg.setIterationsPerStage(400*gr.getVertexesNumber());
	
				TSPWay s = alg.solve();
				
				sum += e.evaluate(s);
			}
			sum = sum / count;
			System.out.println(c+"============");
			tracer.AddPoint(c, sum);
		}
		tracer.serializeToFile(logsDir);
	}
}
