package controllers

import javax.inject.Inject
import play.Logger
import play.api.mvc._


class RetosController @Inject()(cc: ControllerComponents) (implicit assetsFinder: AssetsFinder)
  extends AbstractController(cc) {

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def pdf = Action {
    Ok("")
  }

  def logging[A](action: Action[A])= Action.async(action.parser) { request =>
    Logger.info("Calling action")
    action(request)
  }

  def actionsComposition = Action { request =>
    val value: Option[String] = request.headers.get("tmk")
    value.fold{
      Forbidden("The request did not have a value on the headers")
    }{ value =>
      Ok(s"The value id is $value")
    }
  }

  def clientError = Action { implicit request =>
    throw new IllegalStateException("Exception thrown");
    Ok(s"")
  }

}