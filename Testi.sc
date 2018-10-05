val r = List(1, 2, 3) match {
  case x :: y  ::  Nil ⇒ y // only matches a list with exactly two items
  case _ ⇒ 0
}

r

val rq = List(1, 2, 3) match {

  case x :: y :: z :: no ⇒ no
  case _ ⇒ 0
}

rq == Nil

def sum(a: Int, b: Int, c: Int) = a + b + c
val sum3 = sum _
sum3(1, 9, 8)


/*
def factorial(n: Int): Option[BigInt] = {
  // your code here

  if (n == 0) Some(1)
  else if (n < 0) None
  else {
    val valt = n * factorial(n - 1)
  }


}

factorial(5)
*/
/*
def factorial(n: Int): BigInt =
  if (n == 0) 1 else if (n < 0) 0 else n * factorial(n - 1)

factorial(5)

*/
import akka.actor.Actor
import akka.dispatch.Futures


class A extends Actor {
  import context.dispatcher
  val f = Future("hello")
  def receive = {
    case _ ⇒
  }
}