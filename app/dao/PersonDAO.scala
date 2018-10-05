package dao

import javax.inject.Inject
import models.Person
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile
import slick.lifted.ProvenShape

import scala.concurrent.{ExecutionContext, Future}

class PersonDAO @Inject() (protected val dbConfigProvider: DatabaseConfigProvider)
                          (implicit executionContext: ExecutionContext) extends HasDatabaseConfigProvider[JdbcProfile] {

  import profile.api._

  private val Persons = TableQuery[PersonTable]

  def all(): Future[Seq[Person]] = db.run(Persons.result)

  def insert(person: Person): Future[Unit] = db.run(Persons += person).map { _ => () }

  def delete (name: String): Future[Int] = db.run(Persons.filter(_.name === name).delete)


  private class PersonTable(tag: Tag) extends Table[Person](tag, "PERSON"){

    def name = column[String]("NAME")
    def surname = column[String]("SURNAME")

    //proyeccion mapeo de base de datos
    def * : ProvenShape[Person] = ( name, surname) <> ((Person.apply _).tupled, Person.unapply)
  }
}
