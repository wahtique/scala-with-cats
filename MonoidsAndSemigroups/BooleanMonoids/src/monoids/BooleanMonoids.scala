package monoids

trait Semigroup[A]:
  def combine(x: A, y: A): A

trait Monoid[A] extends Semigroup[A]:
  def empty: A

object BooleanAndMonoid:
  given Monoid[Boolean] with
    def combine(a: Boolean, b: Boolean) = a && b
    def empty = true

object BooleanOrMonoid:
  given Monoid[Boolean] with
    def combine(a: Boolean, b: Boolean) = a || b
    def empty = false

object BooleanEitherMonoid:
  given Monoid[Boolean] with
    def combine(a: Boolean, b: Boolean) = (a && !b) || (!a && b)
    def empty = false

object BooleanXnorMonoid:
  given Monoid[Boolean] with
    def combine(a: Boolean, b: Boolean) = (a || !b) && (!a || b)
    def empty = true

def testBooleanMonoid(using m: Monoid[Boolean]) =
  println(m.toString)
  // identity
  assert(m.combine(true, m.empty) == true)
  assert(m.combine(false, m.empty) == false)
  // associativity
  assert(m.combine(true, false) == m.combine(false, true))

@main def tests =
    import BooleanAndMonoid.given
    testBooleanMonoid
    import BooleanOrMonoid.given
    testBooleanMonoid
    import BooleanEitherMonoid.given
    testBooleanMonoid
    import BooleanXnorMonoid.given
    testBooleanMonoid
