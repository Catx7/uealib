package core
import scala.annotation.unchecked.uncheckedVariance

class Context[+S <: Solution](val evaluator : Evaluator[S],
							  var bestSolutionEver : S @uncheckedVariance,
							  var bestSolutionEverFitness : Double) {

	var ticks : Int = 0;
	def tick() = {
		ticks += 1;
	}
	
}