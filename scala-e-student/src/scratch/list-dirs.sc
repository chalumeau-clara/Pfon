import java.io.File
def getListOfFiles(dir: String):List[String] = {
  val d = new File(dir)
  if (d.exists && d.isDirectory) {
    val content = d.listFiles
      .map(_.getName)
      .toList
      .filter(f => ! Set(".","..").contains(f))
    val pathNames = content.map(dir + "/" + _)
    dir  :: pathNames.flatMap(getListOfFiles)
  } else {
    List(dir + "/" + d.getName)
  }
}

for{ dir <- getListOfFiles("/users/jimka/Downloads")
     if new File(dir).exists
     if new File(dir).isDirectory
     if dir.contains("  ")
     newName = dir.replace("  "," ")
     }
  println(s"replace [$dir] with [$newName]")

Map("a"->3).get("a")
Map("a"->3).getOrElse("a",0)

new File("dir").renameTo(new File("newName"))