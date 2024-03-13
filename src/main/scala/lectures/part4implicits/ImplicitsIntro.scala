package lectures.part4implicits

import scala.language.implicitConversions

object ImplicitsIntro extends App {

  val pair = "Sergio" -> "555"
  val intPair = 1 -> 2

  case class Person(name: String) {
    def greet = s"Hi, my name is $name!"
  }

  implicit def fromStringToPerson(str: String): Person = Person(str)

  println("Earl".greet) // println(fromStringToPerson("Peter").greet)

  // implicit parameters
  def increment(x: Int)(implicit amount: Int): Int = x + amount

  // for variables, need to declare the type
  implicit val defaultAmount: Int = 10

  increment(2)
}
