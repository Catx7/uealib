package core
import scala.annotation.unchecked.uncheckedVariance

abstract class Selector[+S <: Solution] {
	type G <: Generation[S]
	def keepTheBestSolutions(generation : Generation[S] @uncheckedVariance, //Generation[S] @uncheckedVariance,
							 ctx : Context[Solution] @uncheckedVariance) : Generation[S];//Generation[S];
}