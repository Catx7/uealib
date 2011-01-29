package common
import core.{Context, Generation, Solution, StoppingCriteria}

class TicksStoppingCriteria[S <: Solution](val ticksThreshold : Int) extends StoppingCriteria[S] {
	
	def isSatisfied(g : Generation[S], context : Context[S]) : Boolean = {
		return context.ticks == ticksThreshold;
	}
	
}