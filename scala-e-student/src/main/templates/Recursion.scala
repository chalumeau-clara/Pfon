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

import scala.annotation.tailrec

// Assignment name: Recursion exercise
//
// This homework assignment accompanies section "Recursion".
// The test cases can be found in the file RecursionTestSuite.scala
// You should complete the functions, replacing ??? with correct Scala
// code so that the tests pass.
//
// In the lecture we developed three functions for summing a list of
// integers.  Three different techniques were used.   In this assignment
// you should use similar techniques to create three functions to sum a
// list of Double, and also to find the product of a list of Double.

object Recursion {
  // follow the examples from the lecture to create the following functions
  // sumDoublesBySimpleRecursion()
  // sumDoublesByTailRecursion()
  // sumDoublesByFold()
  //
  // and also
  //
  // productDoublesBySimpleRecursion()
  // productDoublesByTailRecursion()
  // productDoublesByFold()

  def sumDoublesBySimpleRecursion(doubles: List[Double]): Double = {
    doubles match {
      case List() => 0.0
      case d :: ds => d + sumDoublesBySimpleRecursion(ds)
    }
  }

  def sumDoublesByTailRecursion(doubles: List[Double]): Double = {
    @scala.annotation.tailrec
    def sumRest(acc: Double, rest: List[Double]): Double = {
      rest match {
        case List() => acc
        case i :: is => sumRest(acc + i, is)
    }

      sumRest(0.0, doubles)
  }

  def sumDoublesByFold(doubles: List[Double]): Double = {
    doubles.fold(0.0)(_+_)

  }


  def productDoublesBySimpleRecursion(doubles: List[Double]): Double = {
    doubles match {
      case List() => 1.0
      // common variable names are also h::t or head::tail  or first::rest
      case i :: is => i * productDoublesBySimpleRecursion(is)
    }
  }

  def productDoublesByTailRecursion(doubles: List[Double]): Double = {
    @scala.annotation.tailrec
    def prodRest(acc: Double, rest: List[Double]): Double = {
      rest match {
        case List() => acc
        case i :: is => prodRest(acc * i, is)
      }
    }
    prodRest(1, doubles)
  }

  def productDoublesByFold(doubles: List[Double]): Double = {
    doubles.fold(1.0)(_*_)
  }

}
