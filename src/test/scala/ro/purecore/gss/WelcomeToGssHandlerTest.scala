package ro.purecore.gss

import com.amazonaws.services.lambda.runtime.Context
import org.scalatest._
import org.scalamock.scalatest.MockFactory

class WelcomeToGssHandlerTest extends FlatSpec with Matchers with MockFactory {

  "WelcomeToGssHandlerTest" should " respond with expected Welcome message" in {
    val actual = new WelcomeToGssHandler().handler(
      RequestPayload("Ogg"),
      mock[Context]
    )
    val expected = ResponsePayload("Welcome to GSS, Ogg!")
    actual should be(expected)
  }

}
