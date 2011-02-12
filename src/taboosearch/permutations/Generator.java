package taboosearch.permutations;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import common.Pair;

import readers.Graph;
import taboosearch.Generation;
import taboosearch.tsp.TSPGeneration;
import taboosearch.tsp.TSPSolution;
import taboosearch.tsp.TSPSwapMove;

abstract public class Generator<S extends Solution, M extends Move<S>, G extends Generation<S>>
		extends taboosearch.Generator<S, M, G> {
	private List<M> moves;
	
	public Generator(Graph graph) {	
		this.moves = getMoves(graph.getVertexesNumber());
	}
	
	abstract protected M makeMove(int i, int j);
	
	private ArrayList<M> getMoves(int n) {
		ArrayList<M> result = new ArrayList<M>((n - 2) * (n - 1) / 2);
		
		for (int i = 1; i < n - 1; ++i)
			for (int j = i + 1; j < n; ++j) {
				result.add(makeMove(i, j));
			}
		
		return result;	
	}

	public Pair<S, List<M>> getNext(G generation) {
		assert generation.size() == 1;

		S solution = generation.get(0);		
		return new Pair<S, List<M>>(solution, moves);
	}

}
