import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneServerPerSuite
import play.api.http.Status
import play.api.test.FakeRequest
import play.api.test.Helpers._

class CalculatorControllerFunctionalSpec extends PlaySpec with GuiceOneServerPerSuite {

  "Calculator routes#sum" should {
    "should return a succesful (200) text response" in {

      val request = FakeRequest(GET, "/calculator/sum?a=1&b=2")

      val result = route(app,request).get

      status(result) mustBe Status.OK
      contentType(result) mustBe Some("text/plain")
    }
  }
  "should return an unsuccessful response (400) when a parameter is missing" in {

    val request = FakeRequest(GET, "/calculator/sum?a=1")

    val result = route(app, request).get

    status(result) mustBe Status.BAD_REQUEST
  }

  "should sum the right amount" in {

    val request = FakeRequest(GET, "/calculator/sum?a=1&b=2")

    val result = route(app, request).get

    contentAsString(result) mustBe "la suma de 1 y 2 es 3"
  }

}
