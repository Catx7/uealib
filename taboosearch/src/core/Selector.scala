package core
import scala.annotation.unchecked.uncheckedVariance

abstract class Selector[+S <: Solution] extends GenerationBuilder[S] {
	
	def keepTheBestSolutions(generation : Generation[S] @uncheckedVariance,
							 ctx : Context[S] @uncheckedVariance) : Generation[S] = {
		
		var taboolator = ctx.taboolator;
		var evaluator = ctx.evaluator;
		
		var candidateFitness = Double.MaxValue;
		var candidate : Option[S] = None;

		for (solution <- generation) {
			if (!taboolator.isTabu(solution)) {
				if (evaluator.evaluate(solution) < candidateFitness) {
					candidateFitness = evaluator.evaluate(solution);
					candidate = Some(solution);
				}
			}
		}

		val result = createGeneration(); // method from GenerationBuilder trait
		candidate match {
			case Some(solution) => {
				taboolator.setTabu(solution);
				result += solution;
			}
			case None => println("Please decrease taboo tenure!")
		}
		return result;
	}

}