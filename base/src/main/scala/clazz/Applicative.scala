package scato
package clazz

abstract class Applicative[F[_]] {
  def apply: Apply[F]
  def pure[A](a: A): F[A]
}

object Applicative {
  def apply[F[_]](implicit F: TC[F, Applicative]): Applicative[F] = F.instance
  implicit def applicative[F[_]](implicit F: Applicative[F]): TC[F, Applicative] = TC[F, Applicative](F)

  object syntax extends ApplicativeSyntax
}
