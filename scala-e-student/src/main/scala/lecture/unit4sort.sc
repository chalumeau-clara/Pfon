val data = List("hello","Hello","HELLO",
                "world","WORLD","World",
                "my", "name", "is", "jim", "not", "john")

data.sorted

data.sortBy(str => str.toLowerCase)

data.sortWith((str1,str2) => if (str1.toLowerCase == str2.toLowerCase)
  str1 < str2
else
  str1.toLowerCase < str2.toLowerCase)

