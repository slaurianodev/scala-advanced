package lectures.part4implicits

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by Sergio.
 */
object MagnetPattern extends App {

  // method overloading

  class P2PRequest
  class P2PResponse
  class Serialize[T]

  trait Actor {
    def receive(statusCode: Int): Int
    def receive(request: P2PRequest): Int
    def receive(response: P2PResponse): Int
    def receive[T : Serialize](message: T): Int
    def receive[T : Serialize](message: T, statusCode: Int): Int
    def receive(future: Future[P2PRequest]): Int
    // lots of overloads :O
  }

  /*
    1 - type erasure
    2 - lifting doesn't work for all overloads
      val receiveFV = receive _ // ?!

    3 - code duplication
    4 - type inference and default args
      actor.receive(?!)
  */

  trait MessageMagnet[Result] {
    def apply(): Result
  }

  def receive[R](magnet: MessageMagnet[R]): R = magnet()

  implicit class FromP2PRequest(request: P2PRequest) extends MessageMagnet[Int] {
    def apply(): Int = {
      // logic for handling a P2PRequest
      println("Handling P2P request")
      42
    }
  }

  implicit class FromP2PResponse(request: P2PResponse) extends MessageMagnet[Int] {
    def apply(): Int = {
      // logic for handling a P2PRequest
      println("Handling P2P response")
      24
    }
  }

  receive(new P2PRequest)
  receive(new P2PResponse)

  // 1 - no more type erasure
  implicit class FromResponseFuture(future: Future[P2PResponse]) extends MessageMagnet[Int] {
    override def apply(): Int = 2
  }

  implicit class FromRequestFuture(future: Future[P2PRequest]) extends MessageMagnet[Int] {
    override def apply(): Int = 3
  }

  println(receive(Future(new P2PRequest)))
  println(receive(Future(new P2PResponse)))

  // 2 - lifting works
  trait MathLib {
    def add1(x:Int): Int = x + 1
    def add1(s: String): Int = s.toInt + 1
  }

  // "magnetize"
  trait AddMagnet {
    def apply(): Int
  }

  def add1(magnet: AddMagnet): Int = magnet()

  implicit class AddInt(x: Int) extends AddMagnet {
    override def apply(): Int = x + 1
  }

  implicit class AddString(s: String) extends AddMagnet {
    override def apply(): Int = s.toInt + 1
  }

  val addFV = add1 _
  println(addFV(1))
  println(addFV("3"))

  /*
    Drawbacks
    1 - verbose
    2 - hard to read
    3 - you can't name or place default arguments
    4 - call by name doesn't work correctly
  */

  // Exercise to prove point 4
  class Handler {
    def handle(s: => String): Unit = {
      println(s)
      println(s)
    }
    // other overloads
  }

  trait HandleMagnet {
    def apply(): Unit
  }

  def handle(magnet: HandleMagnet) = magnet()

  implicit class StringHandle(s: => String) extends HandleMagnet {
    override def apply(): Unit = {
      println(s)
      println(s)
    }
  }

  def sideEfectMethod(): String = {
    println("Hello, Scala")
    "hahaha"
  }

  handle(sideEfectMethod())


}
