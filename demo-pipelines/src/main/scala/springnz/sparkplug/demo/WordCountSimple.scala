package springnz.sparkplug.demo

import org.apache.spark.{ SparkConf, SparkContext }

trait WordCountSimple {
  val sparkContext = new SparkContext(
    new SparkConf().setMaster("local[2]").setAppName("WordCount"))

  def lines = sparkContext.textFile(Util.baseDir() + "/TXT/*")

  def words = lines.flatMap { line ⇒ line.split("\\W+") }
    .map(_.toLowerCase)
    .filter(!_.isEmpty)

  def count = words.map((_, 1)).reduceByKey(_ + _)

  def topWords(n: Int) = count.takeOrdered(n)(Ordering.by(-_._2))

  def stop(): Unit = sparkContext.stop()
}
