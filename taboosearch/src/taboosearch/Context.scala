package taboosearch
import core._
import scala.annotation.unchecked.uncheckedVariance

class Context[+S <: Solution](evaluator : Evaluator[S],
							  val taboolator : Taboolator[S],
							  bestSolutionEver : S @uncheckedVariance,
							  bestSolutionEverFitness : Double)
							  extends core.Context(evaluator, bestSolutionEver, bestSolutionEverFitness) {

	override def tick() = {
		super.tick();
		taboolator.tick();
	}
	
}