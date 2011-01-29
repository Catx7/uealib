package common
import core._

class ConstantTenureStrategy(private val tenure : Int) extends TenureStrategy {
		
	def getTenure() : Int = tenure;
	
	def tick() = {}
	
}