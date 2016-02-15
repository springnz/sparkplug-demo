package springnz.sparkplug.demo

import org.scalatest.{ Inspectors, ShouldMatchers, WordSpec }
import springnz.sparkplug.testkit._

class WordCountPipelineTests extends WordSpec with ShouldMatchers with Inspectors {

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

