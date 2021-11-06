package cateq

import cats.implicits.*
import cats.*

final case class Cat(name: String, age: Int, color: String)

object Cat :
    given Eq[Cat] = Eq.instance[Cat] { (a,b) =>
        a.name === b.name && a.age === b.age && a.color === b.color
    }

@main def main = 
    val cat1 = Cat("Garfield",   38, "orange and black")
    val cat2 = Cat("Heathcliff", 33, "orange and black")


    println("Should be true, false true")
    println(cat1 === cat1)
    println(cat1 === cat2)
    println(cat1 =!= cat2)

    val optionCat1 = Option(cat1)
    val optionCat2 = Option.empty[Cat]

    println("Should be true, false true")
    println(optionCat2 === optionCat2)
    println(optionCat1 === optionCat2)
    println(optionCat1 =!= optionCat2)