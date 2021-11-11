package contramap

trait Printable[A]:
  def format(value: A): String
  def contramap[B](func: B => A): Printable[B] = (value: B) =>
    (func andThen format)(value)

def format[A](value: A)(implicit p: Printable[A]): String = p.format(value)

// some instances of Printable for testing
given Printable[String] = (s: String) => s"$s"
given Printable[Boolean] = (b: Boolean) => if b then "yes" else "no"

final case class Box[A](value: A)

// define a Printable using another one contramap
// here we define Printable[Box[A]] as the "get the A inside then use its Printable[A]"
given boxPrintable[A](using p: Printable[A]): Printable[Box[A]] =
  p.contramap(_.value)

@main def Test =
  println(format(Box("hello")))
  println(format(Box(true)))