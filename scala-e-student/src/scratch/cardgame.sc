import homework.Triptych._

import scala.annotation.tailrec

val c1 = Set("green", "two", "diamond", "solid")
val c2 = Set("green", "one", "diamond", "solid")

val c3 = Set("green", "three", "squiggle", "solid")
val c4 = Set("purple", "two", "oval", "striped")

c1 intersect colors
c1 intersect shadings
colors
numbers

(c1 intersect colors) union (c2 intersect colors)
(c1 intersect numbers) union (c2 intersect numbers)
numbers diff ((c1 intersect numbers) union (c2 intersect numbers))

// val features = Set(colors, shapes, numbers, shadings)

features.foldLeft(Set[String]()){case (acc,feature) =>
  val tmp = c1.intersect(feature) union c2.intersect(feature)
  acc union (if (tmp.size == 1)
    tmp
  else
    feature diff tmp)
}

// isTriptych and featureCompatible

val cards = Set(c1,c2,c3)

cards.map(c => c.intersect(colors))
cards.map(c => c.intersect(shapes))
cards.map(c => c.intersect(numbers))
cards.map(c => c.intersect(shadings))


cards.flatMap(c => c.intersect(colors))
cards.flatMap(c => c.intersect(shapes))
cards.flatMap(c => c.intersect(numbers))
cards.flatMap(c => c.intersect(shadings))

featureCompatible(colors,cards)
featureCompatible(numbers,cards)
featureCompatible(shapes,cards)

// completeTriptych

val pair = Set(c4,c1)

pair.flatMap(c => c.intersect(colors))
c4.intersect(colors) union c1.intersect(colors)

colors.diff(pair.flatMap(c => c.intersect(colors)))

pair.flatMap(c => c.intersect(numbers))

pair.flatMap(c => c.intersect(shapes))
shapes diff (c4.intersect(shapes) union c1.intersect(shapes))

for{f <- features
    t = pair.flatMap(c => c.intersect(f))
    predict <- if (t.size == 1) t else f.diff(t)
    } yield predict


features.flatMap { f =>
  val t = pair.flatMap(c => c.intersect(f))
  if (t.size == 1)
    t
  else
    f.diff(t)
}

for{ c1 <- cards
     c2 <- cards
     c3 <- cards
     tr = Set(c1,c2,c3)
     if isTriptych(tr)} yield tr

for {c1 <- cards
     c2 <- cards
     if c1 != c2
     c3 = completeTriptych(c1, c2)
     if cards.contains(c3)
     } yield Set(c1,c2,c3)
