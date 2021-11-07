package functors

import cats._
import cats.implicits._

// Binary tree data type
// copied from the book
sealed trait Tree[+A]

final case class Branch[A](left: Tree[A], right: Tree[A]) extends Tree[A]

final case class Leaf[A](value: A) extends Tree[A]

object Tree:
  // define constructors for leaf and branch or the compiler will complain
  def branch[A](left: Tree[A], right: Tree[A]): Tree[A] = Branch(left, right)
  def leaf[A](value: A): Tree[A] = Leaf(value)

  // define a functor on the tree
  given Functor[Tree] with
    def map[A, B](tree: Tree[A])(f: A => B): Tree[B] =
      tree match
        case Branch(l, r) => Branch(map(l)(f), map(r)(f))
        case Leaf(v)      => Leaf(f(v))

  // Show the tree as a string
  given showTree[A: Show]: Show[Tree[A]] = Show[Tree[A]] {
    _ match
      case Branch(l, r) =>
        s"Branch(${Show[Tree[A]].show(l)}, ${Show[Tree[A]].show(r)})"
      case Leaf(v) => s"Leaf(${Show[A].show(v)})"
  }

// utility as they are not derived

@main def FunctorTest =
  val tree: Tree[Int] = Branch(Leaf(1), Branch(Leaf(2), Leaf(3)))
  println(tree.map(_ + 1).show) // Branch(Leaf(2), Branch(Leaf(3), Leaf(4)))
  val leaf: Tree[Int] = Tree.leaf(100)
  println(leaf.map(_ * 2).show) // Leaf(200)
  val stringTree: Tree[String] = Branch(Leaf("Hello"), Leaf("World"))
  println(stringTree.map(_.toUpperCase).show)// Branch(Leaf(HELLO), Leaf(WORLD))
