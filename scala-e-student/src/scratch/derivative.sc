import lesson1.Lesson1._
import Math._

val a0 = almostEqual(0.01)

val a = almostEqual(0.01)(1.0, 2.0)

val d = derivative(sin, 0.01, almostEqual(0.001))


