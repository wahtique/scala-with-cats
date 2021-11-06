package cats

import PrintableInstances.given

// define a cat
final case class Cat(name: String, age: Int, color: String)

// define the printable implicit
given Printable[Cat] with
    def format(cat: Cat) = s"${Printable.format(cat.name)} is a ${Printable.format(cat.age)} yo ${Printable.format(cat.color)} cat."
