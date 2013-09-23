import scala.concurrent.{ExecutionContext, Future}
import ExecutionContext.Implicits.global

object FutureExamples {
  def main = {
    futureMap
    futureFlatMap
    futureFilter
    futureForComp
  }

  // A map is fine if modifying a single Future
  def futureMap = {
    val f1 = Future("Hello" + "World")
    val f2 = f1 map(x => x.length)
    f2 foreach(x => println("futureMap: " + x)) // concatenate strings, calculate length
  }

  // If two or more Futures need modifying then a flatMap should be used
  def futureFlatMap = {
    val f1 = Future("Hello" + "World")
    val f2 = Future(3)
    val f3 = f1 flatMap { x ⇒
      f2 map { y =>
        x.length * y
      }
    }
    f3 foreach(x => println("futureFlatMap: " + x)) // concatenate strings, evaluate 3, multiply length by 3
  }

  // If conditional propagation is needed, use a filter
  def futureFilter = {
    val f1 = Future(4)
    val f2 = f1.filter(_ % 2 == 0)
    f2 foreach(x => println("futureFilterA: " + x)) // evaluate 4, ensure it is divisible by 2

    val f3 = f1.filter(_ % 2 == 1).recover {
      // When filter fails, it will have a java.util.NoSuchElementException
      case m: NoSuchElementException => 0
    }
    f3 foreach(x => println("futureFilterB: " + x)) // evaluate 4, ensure it is not divisible by 2 (with recovery value)
  }

  // Use of map, flatMap and filter can be made more readable by using a for comprehension
  def futureForComp = {
    val f1 = Future("Hello" + "World")
    val f2 = Future(3)
    val f3 = (a: String, b: Int) => Future(a.length * b)
    val f4 = for {
      a <- f1
      b <- f2
      c <- f3(a, b)
      if (c % 2 == 0)
    } yield c
    f4 foreach(x => println("futureForComp: " + x)) // concatenate strings, evaluate 3, multiply length by 3, ensure it is divisible by 2
  }
}
