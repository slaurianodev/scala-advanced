package lectures.part5ts

/**
 * Created by Sergio.
 */
object TypeMembers extends App {

  class Animal
  class Dog extends Animal
  class Cat extends Animal

  class AnimalCollection {
    type AnimalType // abstract type member
    type BoundedAnimal <: Animal
    type SuperBoundedAnimal >: Dog <: Animal
    type AnimalC = Cat
  }

  val ac = new AnimalCollection
//  val dog: ac.AnimalType = ???

//  val cat: ac.BoundedAnimal = new Cat // won't compile

  val pup: ac.SuperBoundedAnimal = new Dog
  val cat: ac.AnimalC = new Cat

  type CatAlias = Cat
  val anotherCat: CatAlias = new Cat

  // alternative to generics
  trait MyList {
    type T
    def add(element: T): MyList
  }

  class NonEmptyList(value: Int) extends MyList {
    override type T = Int
    override def add(element: Int): MyList = ???
  }

  // .type
  type CatsType = cat.type
  val newCat: CatsType = cat

  /*
    Exercise - enforce a type to be applicable to SOME TYPES only
  */
  // LOCKED
  trait MList {
    type A
    def head: A
    def tail: MList
  }

  trait ApplicableToNumbers {
    type A <: Number
  }
  // NOK
//  class CustomList(hd: String, tl: CustomList) extends MList with ApplicableToNumbers {
//    override type A = String
//    override def head: String = hd
//    override def tail: CustomList = tl
//  }

  // OK
  class IntList(hd: Int, tl: IntList) extends MList {
    override type A = Int

    override def head: Int = hd

    override def tail: IntList = tl
  }
}
