// Copyright (c) 2020,21 EPITA Research and Development Laboratory
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

import homework.Histogram.fromSet

// You must complete the case class definition so that all the
//   tests pass.
// Use the file HistogramTestSuite to test your code.
// You may implement the internals of the class any way you like,
//   but without performing any mutating operation.

case class Histogram[A](items:Seq[A]) {

  // implement as many vals and defs as necessary to make the implementation of
  //  Histogram understandable and well structured.
  //  Avoid using mutable variables or mutable data structures.

  // A Histogram instance may be created using either of the following syntactical forms:
  //    Histogram(List(...))
  //    Histogram(Vector(...))

  // When a Histogram is printed, it is surrounded by square brackets [...]
  // Histogram(List("a")) printed as "[1 of a]"
  // Histogram(List("a","b","b")) printed as "[1 of a & 2 of b]"  ;; ordered by INCREASING frequency
  // Histogram(List("a","b","b","a")) printed as "[2 of a & 2 of b]"  ;; if the same frequency appears multiple times,
  //                                                            print in any order
  // Histogram(List("a","b","b","a","c")) printed as "[1 of c & 2 of a & 2 of b]"
  // Between the brackets is 0 or more occurrences of the form x of y, where x is an integer,
  //      and y is the printed representation of the object of type A

  // is the set of given elements of this histogram the same as for
  //    another, that, histogram.  I.e., the same elements with the
  //    same frequencies, but the order might be different.
  def sameElements(that:Histogram[A]):Boolean = {
    val item_hist = items.groupBy(identity).filter( nb => {
      that.frequency(nb._1) != nb._2.size})
    item_hist.isEmpty
  }

  // The set of elements occurring most often. This is a set of items, each of type A,
  //   all of which appear the same number of times, and no other element appears more often or as often.
  //   e.g., Set("a","c")

  def getMost():Set[A] = {
    val most = items.groupBy(identity).maxBy(_._2.size)._2.size
    items.groupBy(identity).filter(_._2.size == most).keySet
  }

  val mostFrequent:Set[A] = if (items.isEmpty) Set.empty else getMost()


  def getLeast():Set[A] = {
    val most = items.groupBy(identity).minBy(_._2.size)._2.size
    items.groupBy(identity).filter(_._2.size == most).keySet
  }
  // The set of elements occurring least often. This is a set of items, each of type A,
  //   all of which appear the same number of times, and no other element appears less often or as often.
  //   e.g., Set("d")
  val leastFrequent:Set[A] = if (items.isEmpty) Set.empty else getLeast()

  // given an item, return an Int >= 0 designating how many times that
  //  item appears in the items
  // e.g., frequency("a") = 3,   frequency("z") = 0
  def frequency(item:A):Int = {
    items.count(x => x == item)
  }

  // ++ appends a given Histogram with the original one
  //  Histogram(List("a","b")) ++ Histogram(List("a","c"))
  //
  def ++(that:Histogram[A]):Histogram[A] = Histogram(items ++ that.items)

  def +(that:A):Histogram[A] = {
    Histogram(items ++ Seq(that))
  }

  override def toString: String = {
    items.groupBy(identity).toSeq.sortWith(_._2.size < _._2.size).map{case (k, v) => v.size + " of " + k}.mkString("[", " & ", "]")
  }
}

object Histogram {
  def fromSet[A](items:Set[A]):Histogram[A] = {
    Histogram(items.toSeq)
  }

  def main(argv:Array[String]):Unit = {

  }
}
