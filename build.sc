import mill._, scalalib._

type Chapter = Module

trait Exercise extends ScalaModule {
  def scalaVersion = "3.1.0"
  def ivyDeps = Agg(ivy"org.typelevel::cats-core:2.6.1")
}

object Introduction extends Chapter {
  object PrintableLib extends Exercise
  object CatShow extends Exercise
}


// object test extends Tests {
//   def ivyDeps = Agg(ivy"org.scalameta::munit:1.0.0-M1")
//   def testFrameworks = Seq("munit.Framework")
// }