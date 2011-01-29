package core
import scala.annotation.unchecked.uncheckedVariance

class Algorithm[+S <: Solution](val initializator : Initializator[S],
                                val generator : Generator[Generation[S]],
                                val stoppingCriteria : StoppingCriteria[S],
                                val transitionCriteria : TransitionCriteria[S],
								val selector : Selector[S],
								val context : Context[S]) {
	
	def solve(initialGeneration : Generation[S] @uncheckedVariance) : Generation[S] = {
		var currentGeneration = initialGeneration;
		val evaluator = context.evaluator;
		
		while (!stoppingCriteria.isSatisfied(currentGeneration, context)) {
			var g = generator.getNext(currentGeneration);			
			var h = selector.keepTheBestSolutions(g, context);

			if (transitionCriteria.isSatisfied(currentGeneration, h)) {
				currentGeneration = h;
			}
			
			var currentSolution = currentGeneration.head;
			val currentSolutionFitness = evaluator.evaluate(currentSolution);
			
			if (currentSolutionFitness < context.bestSolutionEverFitness) {
				context.bestSolutionEver = currentSolution;
				context.bestSolutionEverFitness = currentSolutionFitness;
			}
			context.tick();
		}
		return currentGeneration;
	}
	
}