package org.scalalabs.basic.lab04

import org.joda.time.{ Duration, DateTime }
import scala.math._
import language.implicitConversions
import language.higherKinds
/**
 * @author arjan
 *
 * This excercise introduces you to Scala implicit conversion features.
 *
 * Scala has a nice feature that automatically lets you convert types and add methods to an existing class.
 * For instance, it is possible to write "Hello".toList, which yields List(H, e, l, l, o) even though
 * the implementation of the String class does not provide a toList method.
 * This is coined 'library pimping' and is achieved via implicit conversions.
 * In this exercise, you will among other try out some implicit conversions from integers to Joda's DateTime,
 * so we can write little DSL like statements like 1 day + 2 hours.
 *
 * Provide a suitable implementation in order to make the corresponding unittest work.
 *
 * Reference material to solve these exercises can be found here:
 * Implicit conversions: http://programming-scala.labs.oreilly.com/ch08.html#Implicits
 *
 */

object ImplicitConversionExercise01 {

  object Exercise01 {

    def stringToList(s: String): List[Char] = {
      //built in: our String will be converted to Scala's RichString, because this is defined a Scala
      //object called Predef. This is imported by the compiler by default.
      //
      s.toList
    }

  }

  /**============================================================================**/

  object Exercise02 {
    class Celsius(val degree: Double)
    class Fahrenheit(val fahrenheit: Double)

    object TemperaturPrinter {
      def printCelsius(c: Celsius): String = {
        "It's " + c.degree + " degree celsius"
      }

      def printFahrenheit(f: Fahrenheit): String = {
        "It's " + f.fahrenheit + " fahrenheit"
      }
    }

    /**
     * Use this conversion helper to convert fahrenheit values to degree celsius values
     * and vice versa in the implicit function you will define.
     */
    object ConversionHelper {
      def fahrenheit2CelsiusConversion(fahrenheit: Double) = {
        val converted = (fahrenheit - 32) / 1.8
        round(converted * 100).toDouble / 100
      }

      def celsius2FahrenheitConversion(degreeCelsius: Double) = {
        degreeCelsius * 1.8 + 32
      }
    }

    implicit def f2C(f: Fahrenheit) = new Celsius( ConversionHelper.fahrenheit2CelsiusConversion(f.fahrenheit) )
    implicit def c2F(c: Celsius) = new Fahrenheit( ConversionHelper.celsius2FahrenheitConversion(c.degree) )


  }

  /**============================================================================ */
  // Write here an implict class that adds a camelCase method to string.

  object Exercise03 {

    implicit class Camel(s: String) {
      // define method inside, all method will be called implicitly
    }

  }

  /**============================================================================ */
  object Exercise04 {

    //TODO define some implicits that convert integers and longs to durations and builders to make it all work

    implicit def long2duration(l: Long) = new Duration(l)
    implicit def int2duration(i: Int) = new Duration(i)

    object TimeUtils {
      case class DurationBuilder(timeSpan: Long) {
        def now = new DateTime().getMillis()

        def seconds = RichDuration(TimeUtils.seconds(timeSpan))  // call long2duration

        def minutes = RichDuration(TimeUtils.minutes(timeSpan))

        def hours = RichDuration(TimeUtils.hours(timeSpan))

        def days = RichDuration(TimeUtils.days(timeSpan))
      }

      def seconds(in: Long) = in * 1000L

      def minutes(in: Long) = seconds(in) * 60L

      def hours(in: Long) = minutes(in) * 60L

      def days(in: Long) = hours(in) * 24L
    }

    case class RichDuration(val duration: Duration) {
      def millis = duration.getMillis()

      def afterNow = new DateTime().plus(duration)

      def +(that: RichDuration) = RichDuration(this.duration.plus(that.duration))
    }
  }
}

