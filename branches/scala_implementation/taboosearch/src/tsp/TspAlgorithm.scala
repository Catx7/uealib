package tsp
import core._

class TspAlgorithm(initializator : TspInitializator,
                   generator : Generator[TspGeneration],
                   stoppingCriteria : StoppingCriteria[TspSolution],
                   transitionCriteria : TransitionCriteria[TspSolution],
                   selector : TspSelector,
				   context : Context[TspSolution])
  extends Algorithm[TspSolution](initializator, generator, stoppingCriteria, transitionCriteria, selector, context) {

}