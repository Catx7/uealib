package taboosearch
import core._
import scala.annotation.unchecked.uncheckedVariance

abstract class Taboolator[+S <: Solution] {
	def setTabu(s : S @uncheckedVariance);
	def isTabu(s : S @uncheckedVariance) : Boolean;
	def tick();
}