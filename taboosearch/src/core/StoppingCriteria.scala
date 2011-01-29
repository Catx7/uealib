package core
import scala.annotation.unchecked.uncheckedVariance

abstract class StoppingCriteria[+S <: Solution] {	
	def isSatisfied(generation : Generation[S] @uncheckedVariance,
					context : Context[S] @uncheckedVariance) : Boolean;
}
