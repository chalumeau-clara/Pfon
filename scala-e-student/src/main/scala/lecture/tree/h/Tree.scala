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

// implement apply()

package lecture.tree.h

sealed abstract class Tree[A] {
  def leafData():List[A]

  def +(that:A):TreeNode[A]

  def ++(that:Tree[A]):TreeNode[A]

  def apply(a:Int):Int = {
    a
  }
}

case class TreeNode[A](branches:List[Tree[A]]) extends Tree[A] {
  override def toString:String = {
    branches.map(_.toString).mkString("[",", ","]")
  }

  def leafData():List[A] = {
    branches.flatMap(b => b.leafData()) // or    branches.flatMap(_.leafData())
  }

  def +(that:A):TreeNode[A] = {
    TreeNode(branches ++ List(TreeLeaf(that)))
  }

  def ++(t:Tree[A]):TreeNode[A] = {
    t match {
      case TreeNode(br2) => TreeNode(branches ++ br2)
      case TreeLeaf(a:A) => this + a
    }
  }
}

case class TreeLeaf[A](data:A) extends Tree[A] {
  override def toString:String = data.toString

  def leafData():List[A] = List(data)

  def +(that:A):TreeNode[A] = {
    TreeNode(List(this, TreeLeaf(that)))
  }

  def ++(that:Tree[A]):TreeNode[A] = {
    that match {
      case TreeNode(branches) => TreeNode(this :: branches)
      case TreeLeaf(a) => TreeNode(List(this,that))
    }
  }
}

object Tree {
  def contains[A](t:Tree[A],target:A):Boolean = {
    t match {
      case TreeLeaf(data) => data == target
      case TreeNode(branches) =>
        branches.exists(b => contains(b,target))
    }
  }

  def main(argv:Array[String]):Unit = {
    val l3 = TreeLeaf(3.0)
    val l4 = TreeLeaf(4.0)
    val l5 = TreeLeaf(5.0)
    val l6 = TreeLeaf(6.0)

    val t3456 = TreeNode(List(
      TreeNode(List(l3,l4)),
      TreeNode(List(l5,l6))))

    val t = TreeNode(List(t3456,t3456,l6,l3))

    println(t)
    println(t ++ t)
    println(l3 ++ l4)
    println(l3 + 1.1)
    println(t + 1.2)
    println(t ++ l3)
    println(l3 ++ t)
  }
}
