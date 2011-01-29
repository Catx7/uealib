package core
import core._
import collection.mutable._
import collection.immutable.{ListSet}
import scala.annotation.unchecked.uncheckedVariance

// for the meaning of +Solution look for header "Variance" at http://www.rsdn.ru/article/philosophy/Scala.xml
class Generation[+S <: Solution] extends MutableList[S @uncheckedVariance]  {}