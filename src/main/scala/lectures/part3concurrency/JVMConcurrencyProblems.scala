package lectures.part3concurrency

object JVMConcurrencyProblems {

  def runInParallel(): Unit = {
    var x = 0

    val thread1 = new Thread(() => {
      x = 1
    })

    val thread2 = new Thread(() => {
      x = 2
    })

    thread1.start()
    thread2.start()
    println(x) // race condition
  }

  case class BankAccount(var amount: Int)

  def buy(bankAccount: BankAccount, thing: String, price: Int): Unit = {
    bankAccount.amount -= price
  }

  def demoBankingProblem(): Unit = {
    (1 to 10000).foreach { _ =>
      val account = BankAccount(50000)
      val thread1 = new Thread(() => buy(account, "shoes", 3000))
      val thread2 = new Thread(() => buy(account, "iphone", 4000))
      thread1.start()
      thread2.start()
      thread1.join()
      thread2.join()
      if (account.amount != 43000) println(s"AHA! I've just broken the bank: ${account.amount}")
    }
  }

  def buySafe(bankAccount: BankAccount, thing: String, price: Int): Unit = {
    bankAccount.synchronized { // does not allow multiple thread to run the critical section AT THE SAME TIME
      bankAccount.amount -= price // critical section
    }
  }

  /**
    Exercises
    1 - create "inception threads"
      thread 1
        -> thread 2
          -> thread 3
            ...
      each thread prints "hello from thread $i"
      print all messages IN REVERS ORDER

    2 - what's the max/min value of x

    3 - "sleep fallacy": what's the value of message?
   */

  // 1 - inception threads
  def inceptionThreads(maxThreads: Int, i: Int = 1): Thread = {
    new Thread(() => {
      if (i < maxThreads) {
        val newThread = inceptionThreads(maxThreads, i + 1)
        newThread.start()
        newThread.join()
      }
      println(s"Hello from thread $i")
    })
  }


  // 2
  /*
    max value = 100 - each thread increases x by 1
    min value = 1
      all threads read x = 0 at the same time
      all thread (in parallel) compute 0 + 1 = 1
      all thread try to write x = 1
  */
  def minMaxX(): Unit = {
    var x = 0
    val threads = (1 to 100).map(_ => new Thread(() => x += 1))
    threads.foreach(_.start())
  }

  // 3
  /*
    almost always, message = "Scala is awesome"
    is it guaranteed? NO

  */
  def demoSleepFallacy(): Unit = {
    var message = ""
    val awesomeThread = new Thread(() => {
      Thread.sleep(1000)
      message = "Scala is awesome"
    })

    message = "Scala sucks"
    awesomeThread.start()
    Thread.sleep(1000)
    // solution: join the worker thread
    // awesomeThread.join()
    println(message)
  }

  def main(args: Array[String]): Unit = {
//    inceptionThreads(50).start()
    demoSleepFallacy()
  }
}
