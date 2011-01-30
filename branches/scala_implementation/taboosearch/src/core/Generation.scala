package core
import core._
import collection.mutable._
import collection.immutable.{ListSet}
import scala.annotation.unchecked.uncheckedVariance

class Generation[+S <: Solution] extends MutableList[S @uncheckedVariance]  {}