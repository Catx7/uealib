package taboosearch.utils;

import readers.Graph;
import taboosearch.Evaluator;
import taboosearch.permutations.FrequencyMemory;
import taboosearch.permutations.Move;
import taboosearch.permutations.Solution;

import common.Fabric;
import common.Pair;

public abstract class EvaluatorFabric
				implements Fabric<Pair<FrequencyMemory<Solution, Move<Solution>>, Evaluator<Solution, Move<Solution>>>> {
	protected Graph graph;
	protected double diversityCoef;

	public EvaluatorFabric(Graph graph, double diversityCoef) {
		this.graph = graph;
		this.diversityCoef = diversityCoef;
	}		
}
