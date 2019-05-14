package withcats.part1.printable

import cats.instances.int

object PrintableSyntax {
  implicit class PrintableOps[A](value:A){
    def format(implicit p:Printable[A]) = p.format(value)

    def print(implicit p:Printable[A]) = println(p.format(value))
  }
}
