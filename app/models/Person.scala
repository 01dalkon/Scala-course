package models

import models.PersonList.list

case class Person ( name: String, surname: String)

object Person {

  def save(person: Person) = {
    list = list ::: List(person)
  }

  def HelloActor(): String = "Hello Actor!"

}

object PersonList {

  var list: List[Person] =

    List(
      Person("Andres", "Munoz"),
      Person("Jonathan", "Lopez"),
      Person("Maria", "Lopez")
    )
}



