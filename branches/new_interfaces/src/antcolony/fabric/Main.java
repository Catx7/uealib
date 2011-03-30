package antcolony.fabric;

import readers.graphs.CoordsGraphReader;
import readers.Graph;
import readers.graphs.GraphReader;
import functions.*;

public class Main {

	public static void main(String[] args) {
		GraphReader gr = new CoordsGraphReader();
		Graph g = gr.readFromFile("graphs/berlin52.txt");
		
		int time =(int)System.currentTimeMillis();
		
		Functions f = new F10n();
		//AntColonyAlgorithm task = new AntColonyAlgorithm(g);
		AntColonyAlgorithm task = new AntColonyAlgorithm(f);
		task.solve();
		System.out.println((double)((int)System.currentTimeMillis()-time)/1000);
		
	///	double[][] dd = new double[20][10];
	//	System.out.println(dd.length);
	}

}
