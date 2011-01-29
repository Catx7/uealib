package core
import scala.annotation.unchecked.uncheckedVariance

abstract class Generator[+G <: Generation[Solution]] {
	def getNext(generation : G @uncheckedVariance) : G;
}