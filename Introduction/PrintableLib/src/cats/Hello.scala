package cats

import PrintableSyntax.*

object HelloApp:
    def main(args: Array[String]): Unit = {
        val cat = Cat("Duchesse", 10, "brown")
        // use the extension syntax
        // possible as a Printable[Cat] is defined in this package
        cat.print
    }