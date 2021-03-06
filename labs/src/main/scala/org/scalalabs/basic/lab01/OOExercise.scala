package org.scalalabs.basic.lab01
import scala.language.implicitConversions
/**
 * The goal of this exercise is to get familiar basic OO constructs in scala
 *
 * Fix the code so that the unit test 'CurrencyExerciseTest' passes.
 *
 * In order for the tests to pass you need to do the following:
 *
 * Exercise 1:
 * - Create a class Euro
 * - Provide it with two constructor parameters: euro:Int, cents:Int
 * - Provide the cents field with default value: 0
 * - Provide an immutable field named: inCents that converts euro + cents into cents.
 * - Create an object Euro with a factory method named: fromCents that creates an Euro based on cents.
 * - Create a method named: + to the Euro class that adds another Euro
 * - Create a method named: * to the Euro class that multiplies an Euro
 *
 * Exercise 2:
 * - Create an abstract class Currency
 * - Provide it with one constructor parameter: symbol:String
 * - Extend the previously created Euro class from Currency
 * - Override the toString method of Euro to represent the following String:
 *   -> symbol + ': ' + euro + ',' + cents.  E.g: EUR 200,05
 * - In case the cents are 0 use this representation:
 *   -> symbol + ': ' + euro + ',--. E.g.: EUR 200.--
 *
 * Exercise 3:
 * - Mix the Ordered trait in Euro
 * - Implement the compare method
 *
 * Exercise 4:
 * - Provide an implicit class that adds a *(euro:Euro) method to Int
 * - Create a new currency Dollar
 * - Provide a implicit conversion method that converts from Euro to Dollar using the
 *   [[org.scalalabs.basic.lab01.DefaultCurrencyConverter]]
 *
 * Exercise 5:
 * - Extend the conversion method from Euro to Dollar with an implicit parameter
 *   of type [[org.scalalabs.basic.lab01.CurrencyConverter]]
 * - Use the implicit CurrencyConverter to do the conversion.
 */
class Euro(euro: Int, cents: Int = 0, symbol:String = "EUR") extends Currency(symbol) with Ordered[Euro]{
  val InCents = euro*100 + cents

  def +(other: Euro): Euro = {
    val totalCents = other.InCents + this.InCents
    Euro.fromCents(totalCents)
  }

  def *(other: Euro): Euro = {
    val totalCents = other.InCents * this.InCents
    Euro.fromCents(totalCents)
  }

  override def compare(that: Euro): Int = {
    this.InCents - that.InCents
  }

  override def toString: Unit = {
    cents match {
      case 0 => println(symbol + ":" + euro + "--")
      case c if c > 0 => println(symbol + ":" + euro + "," + cents)
      case _ => println("wrong type")
    }
  }
}

object Euro {
  def apply(euro: Int, cents: Int = 0): Euro = new Euro(euro, cents)

  def fromCents(cents: Int) = Euro(cents/100, cents%100)

  implicit class IntTEuro(val x: Int) {
    def *(that: Euro) = Euro(x*that.InCents)
  }

  implicit def fromDollar(dollar: Dollar)(implicit converter: CurrencyConverter): Euro = {
    Euro.fromCents(converter.toEuroCents(dollar.InCents))  // dollar -> euro
  }

}

// create Currency Dollar
class Dollar(dollar: Int, cents: Int, symbol:String = "USD") extends Currency(symbol) {
  val InCents = dollar*100 + cents
}

abstract class Currency(symbol: String) {
  def toString: Unit
}


