package controllers

import javax.inject.Inject
import play.api.mvc.{AbstractController, ControllerComponents}
import services.Calculator


class CulculatorController @Inject()(cc: ControllerComponents, calculator: Calculator)
  extends AbstractController(cc) {

  def sum(a: Int, b: Int) = Action {
    val sum =  calculator.sum(a,b)
    Ok(s"la suma de $a y $b es $sum")
  }

  def rest(a: Int, b: Int) = Action {
    val resta = calculator.resta(a,b)
    Ok(s"la resta de $a y $b es $resta")
  }

  def div(a: Int, b: Int) = Action {
    val div = calculator.divide(a,b)
    Ok(s"la divisin de $a y $b es $div")
  }

  def multiply(a: Int, b: Int) = Action {
    val mult = calculator.multiply(a,b)
    Ok(s"la multiplicación de $a y $b es $mult")
  }

}
