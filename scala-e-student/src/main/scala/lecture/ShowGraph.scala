// Copyright (c) 2022 EPITA Research and Development Laboratory
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

package lecture

object ShowGraph {

  // return the name of a file (created as a temp file)
  //    e.g., in /tmp (depends on OS)
  // The content of the file is a PNG image corresponding to
  //    the output of the dot program with the given dot_string
  //    as input.
  def str_to_png(dot_string:String, title:String ="graph"):Option[String] = {
    import java.io.File
    import sys.process._
    val pngname = File.createTempFile(s"$title-",".png").toString
    val dotname = File.createTempFile(s"$title-",".dot").toString
    val cmd = Seq("dot", "-Tpng", dotname, "-o", pngname)
    //println(dotname)
    val stream = new java.io.FileOutputStream(new java.io.File(dotname))
    for{c <- dot_string}
      stream.write(c.toByte)
    stream.close()
    cmd.!

    Some(pngname)
  }

  // given a list of edges, display the graph
  // The display is achieved in an OS dependent way
  def show(n:Int,
           edges: Seq[(Int,Int)],
           directed: Boolean = false,
           title: String = "graph",
           colors:Map[Int,String]=Map().withDefaultValue("#ffffaa")) = {
    assert(n > 0)

    val gv = (if (directed) "digraph {" else "graph {") +
      "\nrankdir=LR\nnode[shape=circle,style=filled]\n" +
      (if (directed) "edge[arrowhead=vee, arrowsize=.7]\n" else "") +
      (for {s <- (0 until n)}
        yield s"$s [fillcolor=\"${colors{s}}\"]")
        .mkString("", "\n", "\n") +
      (for {(src, dst) <- edges}
        yield (if (directed) s"$src -> $dst" else s"$src -- $dst"))
        .mkString("", "\n", "\n") +
      "}\n"

    for {path_to_file <- str_to_png(gv, title)}
    locally {
      import java.awt.Desktop
      import java.net.URI
      if (Desktop.isDesktopSupported) {
        //println(path_to_file)
        Desktop.getDesktop.browse(new URI("file://" + path_to_file))
      }
    }
  }

  // like show() but the edge list is reconstructed from
  //  the given adjlist.
  def show_by_adjlist(adj: Map[Int, Set[Int]], directed: Boolean = false) = {
    val edges = for {a <- 0 until adj.size
                     b <- adj(a)
                     edge <- (if (directed) Seq((a, b)) else Seq((a, b), (b, a)))
                     } yield edge
    show(adj.size,
         edges.toList,
         directed = directed)
  }

  def main(argv:Array[String]):Unit = {
    //show(5, Seq((0, 1), (1, 2), (2, 4), (0, 2), (0, 3)), false)

    //show(5, Seq((0, 1), (1, 2), (2, 4), (0, 2), (0, 3)), true)
    val edges = Seq((0, 1), (1, 2), (2, 4), (0, 2), (0, 3),
                    (3,6), (4,5),(5,7),(5,1),
                    (6,7), (1,8), (4,9))
    show(10, edges
         , true,
         title="graph")
    show(10, edges
         , true,
         title="graph-0",
         colors=Map(0 -> "#ff2020").withDefaultValue("#ffffaa"))
    show(10, edges
         , true,
         title="graph-1",
         colors=Map(1 -> "#ff2020",
                    2 -> "#ff2020",
                    3 -> "#ff2020").withDefaultValue("#ffffaa"))
    show(10, edges
         , true,
         title="graph-2",
         colors=Map(4 -> "#ff2020",
                    8 -> "#ff2020",
                    6 -> "#ff2020").withDefaultValue("#ffffaa"))
    show(10, edges
         , true,
         title="graph-3",
         colors=Map(5 -> "#ff2020",
                    9 -> "#ff2020",
                    7 -> "#ff2020").withDefaultValue("#ffffaa"))

  }
}
