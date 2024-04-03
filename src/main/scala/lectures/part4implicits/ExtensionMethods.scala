package lectures.part4implicits

/**
 * Created by Sergio.
 */
object ExtensionMethods extends App {

  case class Person(name: String) {
    def greet(): String = s"HI, I'm $name, how can I help?"
  }

  extension (string: String) {
    def greetAsPerson(): String = Person(string).greet()
  }

  val sergiosGreeting = "Sergio".greetAsPerson()

  // extension methods <=> implicits classes
  object Scala2ExtenstionMethods {
    implicit class RichInt(val value: Int) extends AnyVal {
      def isEven: Boolean = value % 2 == 0

      def sqrt: Double = Math.sqrt(value)

      def times(function: () => Unit): Unit = {
        def timesAux(n: Int): Unit =
          if (n <= 0) ()
          else {
            function()
            timesAux(n - 1)
          }

        timesAux(value)
      }
    }
  }

  val is3Even = 3.isEven // new RichInt(3).isEven

  extension (value: Int) {
    // define all methods
    def isEven: Boolean = value % 2 == 0

    def sqrt: Double = Math.sqrt(value)

    def times(function: () => Unit): Unit = {
      def timesAux(n: Int): Unit =
        if (n <= 0) ()
        else {
          function()
          timesAux(n - 1)
        }

      timesAux(value)
    }
  }

  // generic extensions
  extension [A](list: List[A]) {
    def ends: (A, A) = (list.head, list.last)
    def extremes(using ordering: Ordering[A]): (A, A) = list.sorted.ends // <-- can call an extension method here
  }
}
