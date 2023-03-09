val data = Vector("hello","my","name","is","jim",
                "and","not","fred",
                "and","not","john")

data.groupBy(word => word.size)
data.groupBy(word => word(0))

val data2 = List(1,2,3,4,
                 13,12,11,10,
                 100,200,201)

data2.groupBy(n => n % 2)

data.find(word => word(0) == 'm')
data.find(word => word(0) == 'x')

import java.io.InputStream
import scala.io.Source
val nameTarget = "aaron"
val genderTarget = "M"

val genderTar = if (genderTarget == "M") 1 else 2
val s: InputStream = getClass.getResourceAsStream(s"/France-baby-names/nat2020.csv")
val fp = Source.createBufferedSource(s)
val triples = for {line <- fp.getLines().drop(1)
                   Vector(gender, name, year_raw, count_raw) = line.split(";").toVector
                   if name != "_PRENOMS_RARES"
                   if year_raw != "XXXX"
                   if gender.toInt == genderTar
                   if name.toLowerCase() == nameTarget.toLowerCase()
                   } yield (year_raw.toInt, name.toLowerCase(), count_raw.toInt)


//triples.foreach{ i =>
  //println( "Key = " + i )}
val data = for {
  (year,triples) <- triples.toList.groupBy(_._1) // group by year
  foundTriple <- triples.find(triple => {nameTarget == triple._2})
  bornCount = triples.map(triple => triple._3).sum // add up the counts of each name
} yield (year, 100 * foundTriple._3 / bornCount)

data