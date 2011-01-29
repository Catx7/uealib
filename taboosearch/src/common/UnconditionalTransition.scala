package common
import core._
import scala.annotation.unchecked.uncheckedVariance

class UnconditionalTransition[S <: Solution] extends TransitionCriteria[S] {
	def isSatisfied(current : Generation[S], next : Generation[S]) = true
}