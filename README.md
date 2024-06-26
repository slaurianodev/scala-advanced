# scala-advanced

__Part 1 - Advanced Scala__ [here](/src/main/scala/lectures/part1as)
* Dark Syntax Sugar
  * Methods with single param - allows {}
  * Single abstract method - can reduce to lambdas
  * The :: and #:: method
    * scala spec: last char decides associativity of method
  * Multi-word method naming
  * Infix types
  * update() method
  * Setters for mutable containers
* Advanced Pattern Matching
  * unapply method
  * Infix patterns
  * Decomposing sequences
  * Custom return types for unapply (rare)

__Part 2 - Advanced Functional Programming__ [here](/src/main/scala/lectures/part2afp)
* Partial Functions
  * utilities: isDefinedAt, lift, orElse
  * Curried functions - functions returning other functions
  * Lifting - transform a method into a function (ETA-expansion)
  * PAF - Partial Function Applications 
* Lazy Evaluation
  * Call By Need
  * Filtering with lazy vals
  * withFilter
  * for-comprehensions use withFilter with guards
* Monads
  * List, Option, Try, Future, Stream, Set are all *monads*
  * Monad laws: left identity, right identity, associativity

__Part 3 - Functional Concurrent Programming__ [here](/src/main/scala/lectures/part3concurrency)  
* Concurrency
  * Executors - manage threads
  * Concurrency Problem on the JVM - Race condition
  * Thread communication 
    * Producer/Consumer problem
    * Wait and notify (synchronizes)
  * Futures and Promises
    * Functional composition
    * Fallbacks
    * Blocking futures - implicit conversions (Await) 

__Part 4 - Implicits__ [here](/src/main/scala/lectures/part4implicits) 
* ArrowAssoc class
* Organizing implicits
  * Implicit scope
* Type classes
  * AD-HOC polymorphism
  * Implicit classes and methods (Pimp my library)
  * Content bounds
  * Implicitly
  * Magnet Pattern
* Givens - Scala 3 implicits equivalent 
* Extension Methods

__Part 5 - Mastering the Type System__ [here](/src/main/scala/lectures/part5ts)
* Advanced Inheritance
  * Type linearization (super problem)  
* Variance
  * Covariance [+T]/ Invariance [T] / Contravariance [-T]
* Type Member
  * Alternative to generics
* Inner types and Path-Dependent Types
* Self Types
  * Cake Pattern => "Dependency Injection"
  * Cyclical dependencies
* F-Bounded Polymorphism
* Higher-Kinded Types