import mill._, scalalib._

trait Scala3Module extends ScalaModule {
  def scalaVersion = "3.1.0"
}

object PrintableLib extends Scala3Module


// object test extends Tests {
//   def ivyDeps = Agg(ivy"org.scalameta::munit:1.0.0-M1")
//   def testFrameworks = Seq("munit.Framework")
// }