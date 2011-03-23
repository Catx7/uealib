package antcolony.fabric;

import readers.graphs.CoordsGraphReader;
import readers.Graph;
import readers.graphs.GraphReader;

public class Main {

	public static void main(String[] args) {
		GraphReader gr = new CoordsGraphReader();
		Graph g = gr.readFromFile("graphs/burma14.txt");
		
		AntColonyAlgorithm task = new AntColonyAlgorithm(g);
		task.solve();
	}

}
