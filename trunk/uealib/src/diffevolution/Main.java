package diffevolution;

import readers.CoordsGraphReader;
import readers.Graph;
import readers.GraphReader;

public class Main {

	public static void main(String[] args) {
		GraphReader gr = new CoordsGraphReader();
		Graph g = gr.readFromFile("graphs/burma14.txt");
		
		DifferentalEvolution de = new DifferentalEvolution(g, 15, 0.1, 1);
		de.solve();
	}
}
