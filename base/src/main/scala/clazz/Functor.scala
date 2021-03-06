package scato
package clazz

abstract class Functor[F[_]] {
  def map[A, B](ma: F[A])(f: A => B): F[B]
}

object Functor {
  def apply[F[_]](implicit F: TC[F, Functor]): Functor[F] = F.instance
  implicit def functor[F[_]](implicit F: Functor[F]): TC[F, Functor] = TC[F, Functor](F)

  object syntax extends FunctorSyntax
}
