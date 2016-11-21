package ro.purecore.gss

import com.amazonaws.services.lambda.runtime.Context

import scala.concurrent.Future
import io.circe.generic.auto._
import io.github.yeghishe.lambda._

case class RequestPayload(name: String)
case class ResponsePayload(message: String)

// input {"name": "Valentin"}
class WelcomeToGssHandler extends Handler[RequestPayload, ResponsePayload] {
  def handler(r: RequestPayload, c: Context): ResponsePayload = {
    logger.info(s"Request payload: $r")
    ResponsePayload(s"Welcome to GSS, ${r.name}!")
  }
}

