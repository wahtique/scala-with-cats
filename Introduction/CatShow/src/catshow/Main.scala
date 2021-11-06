package catshow

import cats.implicits.*
import cats.*

// Define a cat
case class Cat(name: String, age: Int, color: String)

// companion object with the cat show type 
// not necessary as teh Show[Cat] could be declared at top level
object Cat:
    given Show[Cat] = Show.show[Cat](c => s"${c.name} is a ${c.age} yo ${c.color} cat.")

// print a cat
@main def main() = println(Cat("Black", 3, "white").show)