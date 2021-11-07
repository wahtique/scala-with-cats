import mill._, scalalib._

// Each chapter are used to group exercises together
// each exercise is a self contained runnable project
object Introduction extends Chapter {
  object PrintableLib extends Exercise
  object CatShow extends Exercise
  object Eq extends Exercise
}

object MonoidsAndSemigroups extends Chapter {
  object BooleanMonoids extends Exercise
  object SetMonoids extends Exercise
  object AddingMonoids extends Exercise
}

object Functors extends Chapter {
  object BranchingOut extends Exercise
}

// utility type to group exercises by chapter
type Chapter = Module

// all exercises will have the same dependencies
trait Exercise extends ScalaModule {
  def scalaVersion = "3.1.0"
  def ivyDeps = Agg(ivy"org.typelevel::cats-core:2.6.1")
  object test extends Tests {
    def ivyDeps = Agg(ivy"org.scalameta::munit:1.0.0-M1")
    def testFrameworks = Seq("munit.Framework")
  }
}
