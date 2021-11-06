package cats
// reinventing the cat wheel
// interface to extend when a Printable is needed
trait Printable[A] :
  def format(a: A): String

// some instances of Printable
object PrintableInstances :
    given Printable[Int] with
        def format(a: Int) = a.toString
    
    given Printable[String] with
        def format(a: String) = a

// Our Printable API
object Printable:
  def format[A](a: A)(using p: Printable[A]) = p.format(a)
  def print[A](a: A)(using p: Printable[A]) = println(p.format(a))

// Printable syntax with extension for any type A
// avoid calling the API directly
object PrintableSyntax:
  extension [A](a: A)(using p: Printable[A])
    def format = Printable.format(a)
    def print = Printable.print(a)
