import cats.*
import cats.implicits.*

// add uses monoids but it's not required
def add(items: List[Int]): Int = items.foldLeft(Monoid[Int].empty)(_ |+| _)

// add using monoids for generic types
def add[A: Monoid](items: List[A]): A = items.foldLeft(Monoid[A].empty)(_ |+| _)

case class Order(totalCost: Double, quantity: Double)

given Monoid[Order] with
  def empty: Order = Order(0, 0)
  def combine(x: Order, y: Order): Order =
    Order(x.totalCost |+| y.totalCost, x.quantity |+| y.quantity)

given Show[Order] with
  def show(o: Order): String = s"Total cost: ${o.totalCost}, quantity: ${o.quantity}"

@main def Test =
  println(add(List(1, 2, 3))) // should be 6
  println(add(List("a", "b", "c"))) // should be "abc"
  println(add(List(Order(1, 2), Order(3, 4))).show) // should be Order(4.0, 6.0)
