import org.scalatestplus.play.PlaySpec
import services.Calculator

class CalculatorServiceUnitSpec extends PlaySpec {

  "CalculatorService#sum" should {

    "Suma dos numeros" in {

      val a = 1
      val b = 2
      val calculatorService = new Calculator()

      val result = calculatorService.sum(a, b)

      result mustBe 3
    }
  }

  "CalculatorService#Resta" should {

    "Resta dos numeros" in {

      val a = 2
      val b = 1
      val calculatorService = new Calculator()

      val result = calculatorService.resta(a, b)

      result mustBe 1
    }
  }

  "CalculatorService#Divi" should {

    "Divide dos numeros" in {

      val a = 4
      val b = 2
      val calculatorService = new Calculator()

      val result = calculatorService.divide(a, b)

      result mustBe 2
    }
  }

  "CalculatorService#Multi" should {

    "Multiplica dos numeros" in {

      val a = 2
      val b = 1
      val calculatorService = new Calculator()

      val result = calculatorService.multiply(a, b)

      result mustBe 2
    }
  }
}
