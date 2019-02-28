package org.scalalabs.advanced.lab02

/**
 * Created by IntelliJ IDEA.
 * User: lieke
 * Date: Apr 9, 2010
 */

class ControlStructureExercise(val list: List[String]) {

  //Exercise 1
  def stringsMatching(f: String => Boolean) /*DO SOMETHING HERE*/ {
    for(st <- list; if f(st)) yield st
  }
  def stringsEnding(query: String): Unit = {
    stringsMatching(x => x.endsWith(query))
  } //TODO
  def stringsContaining(query: String): Unit = {
    stringsMatching(x => x.contains(query))
  } //TODO

  //Exercise 2
  def curriedStringConcat(first: String)(second: String) = /*DO SOMETHING HERE*/ {
    first + " " + second
  }
  val helloConcat = "" //TODO
  val goodByeConcat = "" //TODO

}