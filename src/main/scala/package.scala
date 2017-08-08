import fs2.{Stream, Task}

import scala.concurrent.Future
import scala.concurrent.duration._

package object Helpers {
  type Result[T] = Stream[Task, T]

  implicit class ReachStream[A](s: Result[A]){
    def get: Vector[A] = s.runLog.unsafeRun()
  }

  implicit class ReachFuture[A](f: Future[A]) {
    import scala.concurrent.Await
    def get: A = Await.result(f, 10.seconds)
  }
}
