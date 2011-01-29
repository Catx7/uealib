package core
import scala.annotation.unchecked.uncheckedVariance

abstract class TransitionCriteria[+S <: Solution] {	
	def isSatisfied(current : Generation[S] @uncheckedVariance,
					next : Generation[S] @uncheckedVariance) : Boolean;
}
