package cats

import PrintableSyntax.*

object HelloApp:
    def main(args: Array[String]): Unit = {
        val cat = Cat("Duchesse", 10, "brown")
        cat.print
    }