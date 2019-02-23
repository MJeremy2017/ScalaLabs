import scala.util.control._
import scala.collection.mutable.ListBuffer

val l = List(1, 2, 3)
l.head
l.last
l(1)
l.headOption

l.zipWithIndex

l ::: l
l.sorted

List(3, 4, 2) ::: 4 :: List(1)

l.exists(_ == 2)
l.contains(2)
l :: l.tail

val bl = ListBuffer(1, 2)
bl += 2
bl
3.3.max(2)

l.zip(l)

List(3, 4, 2, 1).partition(_ > 3)

val alpha = 'a' to 'z'
val input = "ejp mysljylc k"
input.filter(_ != ' ').filter(alpha.contains(_))

val m = "asda".zip("sdas").toMap ++ Map("a" -> "a")

"aaas".map(m(_)).mkString

Seq(("a", 1), ("a", 2), ("b", 3)).groupBy(_._1)

//List(1, 2, 3).foldLeft(0)( (a, b) => {
//  if (a < b) true else false
//})  // wrong

"asd dfs".split(" ")
Seq(1, 2) ++ Seq(3)
Seq(1, 2) :+ 3  // same return a list

val seq = Seq(1, 2, 3)
seq.map(x => (x%2==0, x)).groupBy(_._1)

m + ("a" -> 3)

for (i <- 1 to 3) yield i
21.toString.split("").reverse
System.currentTimeMillis()

val f = (x: Int) => x+1
f(3)

def highF(x: Int)(innerF: Int => Int) = {
  innerF(x)
}

highF(3)(x => x+1)
Some(12).get
Some(None).get

val sampleRooms = Map(1 -> Some("12"), 2 -> None, 3 -> Some("locked"), 4 -> Some("14"), 5 -> Some("8"), 6 -> Some("locked"))

sampleRooms.getOrElse(1, Some("not"))
Some("g").get == "g"
None.getOrElse("empty")  // return empty
sampleRooms.getOrElse(2, "dd")  // return None
Some(None).getOrElse("empty")
sampleRooms.values
sampleRooms.values.map(_.getOrElse(0))

Exception.allCatch.opt("asdf".toInt)

// Exception.allCatch.opt(None.toInt)
sampleRooms.values.map(x => Exception.allCatch.opt(x.get) )