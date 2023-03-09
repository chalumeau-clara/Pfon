[Back to Unit 6](unit-6.md)  &nbsp;&nbsp;&nbsp;&nbsp; [Forward to Unit 8](unit-8.md)

# Unit 7 -- Object orientation

In this lecture we will develop a Scala program to manipulate graphs and trees.

The idea of this lecture is to learn as we go.  Rather than lecturing
on lots of disjoint topics, we will implement an application, and talk
about the concepts as we need them.  The small program will cover the
following topics:

- Classes and case classes
- Inheritance
- ADT, Algebraic Data Types
- Singleton objects
- Polymorphism
- Printed representation of objects
- Overriding operators such as `++` e.g., `a ++ b`
- `this` vs `that`
- Pattern matching of objects
- Testing your code incrementally
- Make instance of a class callable, `apply`


# Tree

- a -- simple `Tree` supporting `Int`
- b -- generalize to support any type using a type parameter
- c -- implement the `toString` method
- d -- implement `contains` in companion object (with run-time error because of non-exhaustive pattern match)
- e -- `sealed abstract class` to issue compile-time warning
- f -- implement `leafData` using `flatMap` and `for` comprehension
- g -- implement `+` and `++` (possibly `::`)
- h -- `apply` function

# FastPower

- In this section we develop the fast `power` function to raise a base to an integer
power, `p`, by performing approx `log(p)` (base 2) number of multiplications.

- Begin by using `foldLeft` to implement multiplication

- Extend to use recursive function with `p/2` and `p - 1`

- Then we develop a simply functional-style matrix class which supports matrix multiplication.

- Then we use fast-power to implement the matrix power function.


# Support files

## Lecture files
- `src/main/scala/lecture/tree/a/Tree.scala`
- `src/main/scala/lecture/tree/b/Tree.scala`
- `src/main/scala/lecture/tree/c/Tree.scala`
- `src/main/scala/lecture/tree/d/Tree.scala`
- `src/main/scala/lecture/tree/e/Tree.scala`
- `src/main/scala/lecture/tree/f/Tree.scala`
- `src/main/scala/lecture/tree/g/Tree.scala`
- `src/main/scala/lecture/tree/h/Tree.scala`
- `src/main/scala/lecture/tree/Tree.scala`
- `src/main/scala/lecture/FastPower.scala`

## Homework files
- `src/main/templates/Histogram.scala`
- `src/test/waiting/HistogramTestSuite.scala`



