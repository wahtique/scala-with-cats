package cats

trait Printable[A] :
  def format(a: A): String

object PrintableInstances :
    given Printable[Int] with
        def format(a: Int) = a.toString
    
    given Printable[String] with
        def format(a: String) = a

object Printable:
  def format[A](a: A)(using p: Printable[A]) = p.format(a)
  def print[A](a: A)(using p: Printable[A]) = println(p.format(a))

object PrintableSyntax:
  extension [A](a: A)(using p: Printable[A])
    def format = Printable.format(a)
    def print = Printable.print(a)
