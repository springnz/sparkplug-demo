package springnz.sparkplug.demo

import springnz.sparkplug.core.SparkOperation
import springnz.util.Logging

trait WordCountPipeline extends Logging {

  def linesOp = SparkOperation { ctx ⇒
    ctx.textFile(Util.baseDir() + "/TXT/*")
  }

  def wordsOp = for (lines ← linesOp) yield {
    lines.flatMap { line ⇒ line.split("\\W+") }
      .map(_.toLowerCase)
      .filter(!_.isEmpty)
  }

  def countOp = for (words ← wordsOp) yield words.map((_, 1)).reduceByKey(_ + _)

  def topWordsOp(n: Int) = countOp.map(_.takeOrdered(n)(Ordering.by(-_._2)))
}

