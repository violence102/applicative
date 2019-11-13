import simulacrum.typeclass

@typeclass
trait Functor[F[_]] {
  def mapF[A, B](fa: F[A])(f: A => B): F[B]

  def lift[A, B](f: A => B): F[A] => F[B] =
    fa => mapF(fa)(f)
}

@typeclass
trait Applicative[F[_]] extends Functor[F]

object ApplicativeOps {

}