package taboosearch;

import java.util.List;
import java.util.TreeMap;

import common.Evaluated;
import common.Pair;

public class Selector<S extends Solution,
					  M extends Move<S>,
					  G extends Generation<S>,
					  C extends Context<S, M, G>> {
	private C context;
	private Evaluator<S, M> evaluator;
	private Taboolator<S, M> taboolator;
	private FrequencyMemory<S, M> frequencyMemory;
	private AdmissibleChecker<S, M, G, C> admissibleChecker;
	
	public Selector(AdmissibleChecker<S, M, G, C> admissibleChecker, C context) {
		this.context = context;
		this.taboolator = context.getTaboolator();
		this.frequencyMemory = context.getFrequencyMemory();
		this.evaluator = context.getEvaluator();
		this.admissibleChecker = admissibleChecker;
	}
		
	public TreeMap<Double, M> getEvaluatedMoves(S solution) {		
		TreeMap<Double, M> qualities = new TreeMap<Double, M>();
		List<M>	moves = context.staticMoves;
		
		for (M move : moves) {
			if (admissibleChecker.isAdmissible(solution, move)) {
				double quality = evaluator.evaluateMove(solution, move);
				qualities.put(quality, move);
			}
		}
		return qualities;
	}
	
	public G keepTheBestSolutions(Pair<S, List<M>> boundMoves)  {	
		S currentSolution = boundMoves.getFirst();
		
		if (context.eliteList.needsToBeRebuilt()) {
			context.eliteList.rebuild(getEvaluatedMoves(currentSolution));
		}
		
		Evaluated<M> evaluatedMove = context.eliteList.getMove();
		M bestMove = evaluatedMove.getObject();
		double bestMoveCost = evaluatedMove.getCost();
		
		taboolator.tick(currentSolution, bestMove);
		context.eliteList.tick(currentSolution);
		frequencyMemory.tick(currentSolution, (M)bestMove);

		G result = context.getGenerationFabric().makeGeneration();
		S resultSolution = bestMove.operateOn(currentSolution);
		
		double resultSolutionCost = currentSolution.getCost() + bestMoveCost;
		
		resultSolution.setCost(resultSolutionCost);
		result.add(resultSolution);
		return result;
	}

}
