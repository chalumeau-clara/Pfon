// Copyright (c) 2022 EPITA Research and Development Laboratory
//
// Permission is hereby granted, free of charge, to any person obtaining
// a copy of this software and associated documentation
// files (the "Software"), to deal in the Software without restriction,
// including without limitation the rights to use, copy, modify, merge,
// publish, distribute, sublicense, and/or sell copies of the Software,
// and to permit persons to whom the Software is furnished to do so,
// subject to the following conditions:
//
// The above copyright notice and this permission notice shall be
// included in all copies or substantial portions of the Software.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
// EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
// MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
// NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
// LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
// OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
// WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

package homework

import lecture.BabyNamePlot.getClass
import lecture.VegaPlot
import ujson.IndexedValue.True

import java.io.InputStream
import scala.annotation.tailrec
import scala.io.Source

// Assignment name: French baby names
//
// This homework assignment accompanies section "for comprehensions".
// The test cases can be found in the file FrenchNamesTestSuite.scala
// You should complete the function, replacing ??? with correct Scala
// code so that the tests pass.
object FrenchNames {

  // replace characters having diacritical marks with simple characters
  // E.g.,  'æ' => "ae"
  //        'â' | 'ä' | 'à' => "a"
  //        'è' | 'é' | 'ë' | 'ê' => "e"
  // etc for every special character in the file
  def elideString(name:String):String = {
    name.flatMap{ c =>
      if (c.toInt >= 'a' && c.toInt <= 'z'
          || c.toInt >= 'A' && c.toInt <= 'Z'
          || c == '-'
          || c == '\'')
        c.toString
      else
        c match {
          case 'æ' => "ae"
          case 'â'
               | 'ä'
               | 'à' => "a"
          case 'ç' => "c"
          case 'é'
                | 'ë'
                | 'è'
                | 'ê' => "e"
          case 'ô'
               |  'ö' => "o"
          case 'ï'
                | 'î' => "i"
          case 'ÿ' => "y"
          case 'û'
                | 'ü'
                | 'ù' => "u"

          // ??? you will need to add more cases to handle more characters
          //    as you discover them.
          case _ => assert(false,s"unknown character [$c] [${c.toInt}]")
            ""
        }
    }
  }

  // this function returns a Map which maps year to count, where count is
  // the number of babies named nameTarget in that year.  E.g., if
  // fred = babyNamesPerYear("fred","M"), then fred(2012)==100 means
  // there were 100 boy babies born named fred in 2012.
  // SUGGESTION: implement the 3-argument version of babyNamesPerYear
  //    first, and then implement the 2-argument babyNamesPerYear
  //    as a call to the 3-argument version.
  def babyNamesPerYear(nameTarget: String, genderTarget: String): Map[Int,Int] = {
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
      bornCount = triples.map(triple => triple._3).sum // add up the counts of each name
      } yield (year, bornCount)
    fp.close()
    data
  }

  // Some French names can be spelled using different characters.
  // E.g., jerome vs jérôme vs jérome
  // This function has a Boolean flag indicating whether to elide different
  // spellings into one.  If elide=false, then "jerome" and "jérôme" are
  // considered different names, thus the Map returned might have keys
  // "jerome" and "jérôme".  If elide=true, then "jerome" and "jérôme"
  // are considered the same, in which case the Map returned will have
  // key "jerome" but not "jérôme" ... babies named "jérôme" are considered
  // to have the name "jerome" when elide=true.
  def babyNamesPerYear(nameTarget: String, genderTarget: String, elide:Boolean): Map[Int,Int] = {
    assert(nameTarget == nameTarget.toLowerCase,
           s"babyNamesPerYear must be called with lower case name, not $nameTarget")
    val s: InputStream = getClass.getResourceAsStream(s"/France-baby-names/nat2020.csv")
    val genderTar = if (genderTarget == "M") 1 else 2
    val fp = Source.createBufferedSource(s)
    // remember to drop the first line of file: sexe;preusuel;annais;nombre

    // skip lines where name is _PRENOMS_RARES
    // skip lines where the year is XXXX
    // names are in caps (e.g., FABIENNE) but nameTarget will be given as lower case (e.g., fabienne)
    //   you should convert the name read from the file to lower case using .toLowerCase
    val pair_it = for {line <- fp.getLines().drop(1) // HINT: fp.getLines().??? // drop the line sexe;preusuel;annais;nombre

            Vector(gender_raw, name_raw, year_raw, count_raw ) = line.split(";").toVector
            // You will need to add several (5 to 10 at least) lines
            // to transform gender_raw, name_raw, year_row, and count_raw
            // into the exact data you'll need.  You'll will also need to
            // include some conditionals (if ...) to filter away lines you
            // want to thow away.
            if name_raw != "_PRENOMS_RARES"
            if year_raw != "XXXX"
            if gender_raw.toInt == genderTar
            name = if (elide) elideString(name_raw.toLowerCase()) else name_raw.toLowerCase()
            if name.toLowerCase() == nameTarget
            year = year_raw.toInt
            count = count_raw.toInt
            } yield (name,year) -> count

    // you'll need to come up with some transform of pair_it
    // E.g., pair_it.toMap will create a Map[(String,Int),Int], but that won't be the exact
    //   type you need.  You need to compute and return a Map[Int,Int]
    val data = for {
      (year,pair_it) <- pair_it.toList.groupBy(_._1._2) // group by year
      bornCount = pair_it.map(triple => triple._2).sum // add up the counts of each name
    } yield (year, bornCount)
    fp.close()
    data
  }

  // count the number of babies born between yearMin and yearMax (including both yearMin and yearMax)
  // of the given gender and name.
  // the parameter elide, indicates (as above) whether to consider names
  //  such as "jerome" and "jérôme" as the same or different.
  //  If elide is true, then "jerome" and "jérôme" are considered the same.
  //  if elide is false, then "jerome" and "jérôme" are considered to be different names.
  def countNamesInYearRange(name:String, gender:String, yearMin:Int, yearMax:Int, elide:Boolean):Int = {
    val names = babyNamesPerYear(name, gender, elide)
    // now add up the counts of only the years you are interested in
    (yearMin to yearMax).foldLeft(0)((acc,year) => { acc + names.getOrElse(year, 0)})
  }

  // Many French names are hyphenated such as "jean-albert" and "anne-marie".
  // Given a name such as "jean" return all other names X such that "jean-X" or "X-jean"
  // is a name registered in the resource data base.
  // We only care about exact names.  for example "jean-jerome" and "jean-jérôme" are different.
  // Do not attempt to elide names.
  // Careful!  If the given baseName never appears as some hyphenated form,
  //   then the empty Set should be returned.
  // Careful!  Some names have multiple hyphens:  e.g., abd-el-kader
  // Ignore any line for which year = XXXX.
  // WARNING some name might be hyphenated with itself.  E.g. jean-jean or marie-marie.
  //  Read the instructions carefully to understand what to do in this case.
  def hyphenatedNames(baseName:String):Set[String] = {
    assert(baseName == baseName.toLowerCase,
           s"hyphenatedNames must be called with lower case name, not $baseName")
    val s: InputStream = getClass.getResourceAsStream(s"/France-baby-names/nat2020.csv")
    val fp = Source.createBufferedSource(s)
    val it = for {line <- fp.getLines().drop(1) // HINT: fp.getLines().??? // drop the line sexe;preusuel;annais;nombre
                  Vector(_, name_raw, year_raw, count_raw ) = line.split(";").toVector
                  // You'll need to fill in several lines of converting *_raw
                  // and filtering, and string manipulation to find and correctly
                  // parse hyphenated names.
                  if year_raw != "XXXX"
                  if name_raw.toLowerCase() != baseName
                  name = name_raw.toLowerCase().split("-")
                  num = name.count(nom => {
                    nom.toLowerCase() == baseName})
                  nom = name.toSet

                  nam = if (nom.contains(baseName) && num > 1) nom else if (nom.contains(baseName)) nom diff Set(baseName) else Set.empty

                  n <- nam
         } yield n
    it.toSet
  }

  def main(argv: Array[String]): Unit = {

  }
}
