val data = List(1, 1.0, "hello", Vector("hello",1.0))
//
//val List(c1,c2,c3,c4) = data.map(_.getClass)
//
//c1.isAssignableFrom(c2)
//c4.isAssignableFrom(classOf[Vector[String]])

import scala.reflect.runtime.universe._
def getType[A: TypeTag](a: A): Type = typeOf[A]

val List(c1,c2,c3,c4) = data.map(getType)

c1 <:< c2 // true

(1 to 12 by 2).foreach(println)




