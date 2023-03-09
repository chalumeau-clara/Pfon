
val m1 = Map("a" -> 1).withDefaultValue(-1)
val m2 = Map("b" -> 2).withDefaultValue(0)
m1("b")
m2("c")

val m3 = m1 ++ m2
m3("c")
m3.get("c")