import scala.concurrent.Future
import scala.concurrent.duration._

package object Helpers{
  implicit class ReachFuture[A](f: Future[A]) {
    import scala.concurrent.Await
    def get: A = Await.result(f, 10.seconds)
  }
}