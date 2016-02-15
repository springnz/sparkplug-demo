package springnz.sparkplug.demo

import org.apache.spark.{ SparkConf, SparkContext }

object WordCountOneLine {
  def main(args: Array[String]): Unit = {
    val sparkContext = new SparkContext(
      new SparkConf().setMaster("local[2]").setAppName("WordCount"))

    sparkContext.textFile(Util.baseDir() + "/TXT/*")
      .flatMap { line ⇒ line.split("\\W+") }
      .map(_.toLowerCase)
      .filter(!_.isEmpty)
      .map((_, 1)).reduceByKey(_ + _)
      .takeOrdered(100)(Ordering.by(-_._2))

    sparkContext.stop()
  }
}
