package springnz.sparkplug.demo

object WordCountSimpleRunner {
  def main(args: Array[String]): Unit = new WordCountSimple {
    println(topWords(50).mkString("\n"))
    stop()
  }
}

