package org.scalalabs.basic.lab02

import scala.collection.mutable.ListBuffer
import sys._

object ListManipulationExercise02 {

  /**
   * Find the maximum element in a list, e.g. maxElementInList(List(1,9,3,5)) == 9
   * As usual, various ways exist: pattern matching, folding, ...
   */
  def maxElementInList(l: List[Int]): Int = {
    l.reduce((x, y) => {
      if (x>y) x else y
    })

    l match {
      case h :: t => if (h > maxElementInList(t)) h else maxElementInList(t)
      case h :: Nil => h
    }
  }

  /**
   * Calculate the sum of the equally position elements
   * of the two list
   */
  def sumOfTwo(l1: List[Int], l2: List[Int]): List[Int] = {
    l1.zip(l2).map( x => x._1+x._2 )
  }

  /**
   *  For this exercise preferably make use of the sumOfTwo
   * method above
   */
  def sumOfMany(l: List[Int]*): List[Int] = {
    // l now is an array
    l.toList.reduceLeft( (l1, l2) => sumOfTwo(l1, l2) )
  }

  case class Person(age: Int, firstName: String, lastName: String)

  /**
   * The following method is implemented in the most in-elegant way we could think of.
   * The idea is to re-write the method into more functional style. In the end, you
   * may be able to achieve the same functionality as implemented below
   * in a one-liner.
   */
  def separateTheMenFromTheBoys(persons: List[Person]): List[List[String]] = {
    var boys: ListBuffer[Person] = new ListBuffer[Person]()
    var men: ListBuffer[Person] = new ListBuffer[Person]()
    var validBoyNames: ListBuffer[String] = new ListBuffer[String]()
    var validMenNames: ListBuffer[String] = new ListBuffer[String]()

    for (person <- persons) {
      if (person.age < 18) {
        boys += person
      } else {
        men += person
      }
    }

    var sortedBoys = boys.toList.sortBy(_.age)
    var sortedMen = men.toList.sortBy(_.age)

    for (boy <- sortedBoys) {
      validBoyNames += boy.firstName
    }
    for (man <- sortedMen) {
      validMenNames += man.firstName
    }
    List(validBoyNames.toList, validMenNames.toList)
  }

  def separateTheMenFromTheBoys2(persons: List[Person]): List[List[String]] = {
    val (l_boy, l_man) = persons.partition(_.age <= 18)
    List(l_boy.map(_.firstName).sorted, l_man.map(_.firstName).sorted)
  }

}