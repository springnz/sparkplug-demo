package springnz.sparkplug.demo

import org.scalatest.{ Inspectors, ShouldMatchers, WordSpec }
import springnz.sparkplug.core.SparkPimpers
import springnz.sparkplug.testkit._
import springnz.util.Logging

class WordCountPipelineTests extends WordSpec with ShouldMatchers with Logging with Inspectors {

  import SparkPimpers._

  trait TestWordCount extends WordCountPipeline {
  }

  "WordCount" should {

    "test linesOp" in new SimpleTestContext("test") with TestWordCount {
      println(execute(linesOp.collect()).get.toList)
    }

    "test wordsOp" in new SimpleTestContext("test") with TestWordCount {
      execute(wordsOp.collect())
    }

    "testCountOp" in new SimpleTestContext("test") with TestWordCount {
      println(execute(topWordsOp(100)).get.toList)
    }
  }
}

