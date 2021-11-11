package imap

import cats.syntax.apply

trait Codec[A] :
  def encode(value: A): String
  def decode(value: String): A
  def imap[B](dec: A => B, enc: B => A): Codec[B] = new Codec[B] {
    def encode(value: B): String = Codec.this.encode(enc(value))
    def decode(value: String): B = dec(Codec.this.decode(value))
  }

object Codec:
    def apply[A](implicit codec: Codec[A]): Codec[A] = codec

def encode[A](value: A)(implicit c: Codec[A]): String =
  c.encode(value)

def decode[A](value: String)(implicit c: Codec[A]): A =
  c.decode(value)

given Codec[String] = new Codec[String] {
  def encode(value: String): String = value
  def decode(value: String): String = value
}

given Codec[Int] = Codec[String].imap(_.toInt, _.toString)

given Codec[Boolean] = Codec[String].imap(_.toBoolean, _.toString)

given Codec[Double] = Codec[String].imap(_.toDouble, _.toString)

final case class Box[A](value: A)

given boxCodec[A](using codec: Codec[A]): Codec[Box[A]] =
  codec.imap(Box(_), _.value)

@main def imapTest =

    println(encode(true))
    println(decode[Boolean]("false"))

    println(encode(123))
    println(decode[Int]("666"))

    println(encode(123.4))
    println(decode[Double]("666.7"))

    println(encode(Box(123)))
    println(decode[Box[Int]]("666"))



