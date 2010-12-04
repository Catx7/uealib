package taboosearch;

import readers.CoordsGraphReader;
import readers.Graph;
import readers.GraphReader;

public class Main {

	public static void main(String[] args) {
		GraphReader gr = new CoordsGraphReader();
		Graph g = gr.readFromFile("/home/rrhu/burma14.txt");
		
		TabooSearchAlgorithm ts = new TabooSearchAlgorithm(g);
		ts.solve();
	}

}
