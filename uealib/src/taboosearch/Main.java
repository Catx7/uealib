package taboosearch;

import readers.CoordsGraphReader;
import readers.Graph;
import readers.GraphReader;


public class Main {

	public static void main(String[] args) {
		GraphReader gr = new CoordsGraphReader();
		Graph g = gr.readFromFile("graphs/burma14.txt");
		Evaluator e = new Evaluator(g.getWeights());
		Initializator i = new Initializator(g.getWeights());
		
		Context ctx = Context.getInstance();
		ctx.e = e;
		
		Generation initGen = i.getInitialGeneration();
		Solution initSol = initGen.get(0);
		
		System.out.println(initSol.getFitness());
	}

}
