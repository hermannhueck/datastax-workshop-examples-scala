package com.datastax

import scala.concurrent._
import scala.concurrent.duration._

package object workshop {

  implicit final class FutureOps[A](private val future: Future[A]) {
    @inline def await(timeout: Duration = 5.seconds): A =
      Await.result(future, timeout)
  }
}
