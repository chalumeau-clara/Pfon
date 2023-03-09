package lecture

object FastPower {

  def power_slow(base:Double,p:Int):Double = {
    (1 to p).foldLeft(1.0)((acc,item) => acc * base)
  }

  def power_slow[A](base:A,p:Int,one:A,times:(A,A)=>A):A = {
    (1 to p).foldLeft(one)((acc,item) => times(acc, base))
  }

  def power(base:Double,p:Int):Double = {
    power(base,p,1,(a:Double,b:Double)=>a*b)
  }

  def power(base:Int,p:Int):Int = {
    power(base,p,1,(a:Int,b:Int)=>a*b)
  }

  def power(base:String,p:Int):String = {
    power(base,p,"",(a:String,b:String)=>a++b)
  }

  def power[A](base:A,p:Int,
               one:A,
               times:(A,A)=>A):A = {
    p match {
      case 0 => one
      case 1 => base
      case p if p % 2 == 0 =>
        val x = power(base,p/2,one,times)
        times(x,x)
      case p =>
        times(base, power(base,p-1,one,times))
    }
  }

  def main(argv:Array[String]):Unit = {
    println(power(2.0,3))
    println(power(2.0,1))
    println(power(1.2, 101))
    println(power(2.0,0))
    println(power(2,30))
    println(power("abc-",20))
    println(power("-",80,"",(a:String,b:String)=>a++b))
    println(power(List(1),80,List[Int](),(a:List[Int],b:List[Int])=>a++b))
  }
}


case class Matrix(dim:Int,
                  tabulate:(Int,Int)=>Double) {
  val elements: Vector[Vector[Double]] =
    for {row <- (0 until dim).toVector}
      yield for {col <- (0 until dim).toVector}
        yield tabulate(row, col)

  def identity():Matrix = Matrix.identity(dim)

  override def toString:String = (for{row <- 0 until dim}
    yield elements(row).mkString(", ")).mkString("[","\n "," ]")

  def times(that:Matrix):Matrix = {
    assert(that.dim == dim,
           s"cannot multiply ${dim}x${dim} by ${that.dim}x${that.dim}")

    Matrix(dim,
           (i:Int,j:Int)=>(0 until dim).foldLeft(0.0)(
             (acc:Double,k:Int) => acc + elements(i)(k) * that.elements(k)(j)))
  }

  def power(p:Int):Matrix = {
    FastPower.power(this,p,
                    identity(),
                    (a:Matrix,b:Matrix)=>a.times(b))
  }

  def power_slow(p:Int):Matrix = {
    FastPower.power_slow(this, p,
                         identity(),
                         (a: Matrix, b: Matrix) => a.times(b))
  }
}

object Matrix {
  def identity(dim: Int): Matrix = {
    Matrix(dim,
           (i: Int, j: Int) => if (i == j) 1.0 else 0.0)
  }

  def fromSeq(elements: Seq[Seq[Double]]): Matrix = {
    elements.foreach(e => assert(e.size == elements.size,
                                 s"expecting ${elements.size} elements in each row, invalid=$e"))

    Matrix(elements.size,
           (i: Int, j: Int) => elements(i)(j))
  }

  def main(argv: Array[String]): Unit = {
    println(identity(4))
    println(identity(4).times(identity(4)))
    val m1 = fromSeq(Seq(Seq(1.0, 11.1, 1.2, 1.0),
                         Seq(-2.1, 2.2, -1.5, 1.0),
                         Seq(0.5, -1.2, 1.0, -1.0),
                         Seq(0.0, 1.0, 0.0, -1.0)))
    val m2 = fromSeq(Seq(Seq(2.0, 2.1, 2.1, -1.0),
                         Seq(3.1, -3.2, -3.5, 1.0),
                         Seq(0.0, 0.0, 1.0, 0.0),
                         Seq(0.5, -1.2, 1.0, -1.0)))
    println(m1)
    println(m1.times(identity(4)))
    println(identity(4).times(m1))
    println(m1.times(m2))
    println(m1.power(163))
    println(m1.power_slow(163))
  }
}
