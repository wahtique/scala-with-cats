package monoids

trait Semigroup[A]:
  def combine(x: A, y: A): A

trait Monoid[A] extends Semigroup[A]:
  def empty: A

object Monoid :
  def apply[A](using monoid: Monoid[A]) = monoid

object SetUnion :
    given setMonoid[A](using monoid: Monoid[A]): Monoid[Set[A]] with 
        def combine(a: Set[A], b: Set[A]) = a union b
        def empty = Set.empty[A]

object SetIntersection :
    given setSemigroup[A](using monoid: Semigroup[A]): Semigroup[Set[A]] with 
        def combine(a: Set[A], b: Set[A]) = a intersect b

// some base monoids

given Monoid[Int] with 
  def combine(a: Int, b: Int) = a + b
  def empty = 0

given Monoid[String] with 
  def combine(a: String, b: String) = a ++ b
  def empty = ""

@main def testProperties =
    import SetUnion.given
    val intSetMonoid = Monoid[Set[Int]]
    val strSetMonoid = Monoid[Set[String]]
    println(intSetMonoid.combine(Set(1, 2), Set(2, 3)))
    println(strSetMonoid.combine(Set("A", "B"), Set("B", "C")))