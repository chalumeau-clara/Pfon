import homework.Triptych._
import scala.annotation.tailrec

// Very simple iteration through the given set of cards,
// on each iteration we try to add the card to the cap.
// That either makes a valid cap or it doesn't.
// If it does, then use that card to extend the cap, and continue
// to the next iteration.  If adding the cards does not make a
// valid cap, then discard it and continue.
// The weakness of this algorithm is that it just iterates
// over the default order of the list returned from the .toList
// method.   But this order might be insufficient.  It might
// be that findCapFromStartingCap returns None, but some other
// order of the list would find a cap of the target size.
def findCapFromStartingCap(cards: Set[Card],
                           targetSize: Int,
                           startingCap:Set[Card]): Option[Set[Card]] = {
  require(findTriptych(startingCap).isEmpty)
  @tailrec
  def recur(thisSize: Int, remaining: List[Card], cap: Set[Card]): Option[Set[Card]] = {
    if (thisSize == targetSize)
      Some(cap)
    else remaining match {
      case Nil => None
      case c1 :: cs =>
        // can we add c1 to cap and still be a cap?
        // if not, then discard c1, and recur with cs
        // if so, then add c1 and recur.
        if (cap.exists(c2 => cap.contains(completeTriptych(c1, c2))))
          recur(thisSize, cs, cap)
        else
          recur(thisSize + 1, cs, cap + c1)
    }
  }

  recur(startingCap.size, (cards diff startingCap).toList, startingCap)
}

def findCap_v1(cards: Set[Card], targetSize: Int): Option[Set[Card]] = {
  val startingCap = Set(Set("red", "oval", "one", "solid"),
                        Set("red", "oval", "two", "solid"),
                        Set("green", "oval", "one", "solid"))
  val startingCap1 = Set(Set("red", "oval", "one", "solid"),
                        Set("purple", "oval", "two", "solid"),
                        Set("green", "oval", "one", "striped"))

  findCapFromStartingCap(cards,targetSize,startingCap)
}


// Iterate through every possible 3-cap.  For each 3-cap
// attempt to generate a cap of the target size.  If that
// fails then continue to the next 3-cap as a starting cap.
def findCap_v2(cards: Set[Card], targetSize: Int): Option[Set[Card]] = {
  def recur(startingCaps:List[Set[Card]]):Option[Set[Card]] = {
    startingCaps match {
      case Nil => None
      case startingCap::ps =>
        val maybeCap = findCapFromStartingCap(cards, targetSize, startingCap)
        maybeCap match {
          case None => recur(ps)
          case Some(_) => maybeCap
        }
    }
  }

  val startingCaps = for { c1 <- cards
                           c2 <- cards - c1
                           c3 = completeTriptych(c1,c2)
                           c4 <- cards diff Set(c1,c2,c3)
                           } yield Set(c1,c2,c4)
  recur(startingCaps.toList)
}

(3 to 20).foreach(n=> println((n,findCap_v2(deck, n))))

// Given a set of cards, and a target size between 3 and 18 inclusive,
// find a subset of the given size, consisting of cards which contains no
// Triptych.
def findCap_v3(cards: Set[Card], targetSize: Int): Option[Set[Card]] = {

  def extendToSize(capSize: Int, cap: List[Card], deck: Set[Card]): Option[Set[Card]] = {
    if (capSize == targetSize)
      Some(cap.toSet)
    else if (deck.isEmpty)
      None
    else {
      deck.foldLeft(None: Option[Set[Card]]) { (acc, c) =>
        acc match {
          case Some(_) => acc
          case None => extendToSize(capSize + 1, c :: cap, findCapExtensions(c, cap, deck - c))
        }
      }
    }
  }

  val startingCap = List(Set("red", "oval", "one", "solid"),
                         Set("red", "oval", "two", "solid"),
                         Set("green", "oval", "one", "solid"))

  val deck = cards diff startingCap.toSet
  extendToSize(startingCap.size,
               startingCap,
               findCapExtensions(startingCap, deck))
}

//(3 to 20).foreach(n=> println((n,findCap_v3(deck, n))))