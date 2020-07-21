package com.datastax

import scala.concurrent._
import scala.concurrent.duration._

package object workshop {

  implicit final class ToFutureSyntax[A](thunk: => A)(
      implicit
      ec: ExecutionContext
  ) {
    @inline def future: Future[A] = Future(thunk)
  }

  implicit final class ToSuccessfulFutureSyntax[A](value: A) {
    @inline def futureSuccess: Future[A] = Future.successful(value)
  }

  implicit final class FutureOps[A](private val future: Future[A]) {
    @inline def await(timeout: Duration = 5.seconds): A =
      Await.result(future, timeout)
  }
}
