package core
import scala.annotation.unchecked.uncheckedVariance

abstract class Evaluator[+S <: Solution] {
	def evaluate(solution : S @uncheckedVariance) : Double;
}