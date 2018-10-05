package controllers

import akka.actor.ActorSystem
import com.google.inject.name.Named
import dao.PersonDAO
import models.{Hello, Person, PersonList}
import javax.inject.Inject
import play.api.cache._
import play.api.libs.functional.syntax.unlift
import play.api.mvc.{Action, _}
import play.api.libs.json._
import play.api.libs.functional.syntax._
import play.api.libs.ws.WSClient

import scala.concurrent.{ExecutionContext, Future}


class PersonController @Inject()(hello: Hello,
                                 personDao: PersonDAO,
                                 parser: PlayBodyParsers,
                                 cache: AsyncCacheApi,
                                 ws: WSClient,
                                 system: ActorSystem,
                                 cc:ControllerComponents)
                                (implicit executionContext: ExecutionContext) extends AbstractController(cc){

  implicit val personWrites: Writes[Person] = (
    (JsPath \ "name").write[String] and
      (JsPath \ "surname").write[String]
    )(unlift(Person.unapply))

  implicit val personReads: Reads[Person] = (
      (JsPath \ "name").read[String] and
      (JsPath \ "surname").read[String]
    )(Person.apply _)



  def validateJson[A : Reads] = parser.json.validate(
    _.validate[A].asEither.left.map(e => BadRequest(JsError.toJson(e)))
  )

  def listPerson = Action {
    val json = Json.toJson(PersonList.list)
    Ok(json)
  }

  def listPersonAsync = Action.async {
    val json = Json.toJson(PersonList.list)
    Future.successful(
      Ok(json)
    )
  }

  def savePerson = Action(parse.json) { request =>
    val PersonaResult = request.body.validate[Person]
    PersonaResult.fold(
      errors => {
        BadRequest(Json.obj("status" ->"KO", "message" -> JsError.toJson(errors)))
      },
      person => {
        Person.save(person)
        Ok(Json.obj("status" ->"OK", "message" -> ("The name '"+person.name+"' saved.") ))
      }
    )
  }

  //CRUD

  def list = Action.async {
    personDao.all().map { person =>
      Ok(Json.toJson(person))
    }
  }

  def insert = Action.async(validateJson[Person]) {

    request => {
      personDao.insert(request.body).map( code => {
        Ok(s"El CRUD FUNCIONA e INSERTA")
      }
      )
    }
  }

  def delete(name: String) = Action.async {

    personDao.delete(name).map(code => {
      Ok(s"El CRUD FUNCIONA Y ELIMINA !")
    })
  }

  def simpleCache = Action.async {

    val complexResult: Future[Int] = cache.getOrElseUpdate[Int]("complex-calc") {
      println("take notes, this wont print a second time !")
      Future.successful(complexCalculation())
    }

    complexResult
      .map(value => {
        Ok(s"the complex calculation result is $value")
      }).recover {
      case _ => InternalServerError("this wont execute")
    }
  }

  private def complexCalculation() = 1 + 1

  def simpleWS = Action.async {

    val steinsGateUrl = "https://api.jikan.moe/v3/anime/9253/episodes"
    ws.url(steinsGateUrl).get().map {
      response => {
        Ok(response.json)
      }
    }
  }

  def depend(name: String)  = Action {
    val valor = hello.sayHello(name)
      Ok(valor)
  }

}
