package springnz.sparkplug.demo

import springnz.sparkplug.core.{ LocalConfigurer, SparkExecutor }

object WordCountPipelineRunner {
  def main(args: Array[String]): Unit = new WordCountPipeline with SparkExecutor {
    override def configurer = new LocalConfigurer("WordCount")
    println(execute(topWordsOp(50)).get.mkString("\n"))
  }
}
