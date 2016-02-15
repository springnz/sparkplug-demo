package springnz.sparkplug.demo

import org.scalatest.{ Inspectors, ShouldMatchers, WordSpec }
import springnz.sparkplug.testkit._

class WordCountSimpleTests extends WordSpec with ShouldMatchers with Inspectors {

  trait TestWordCount extends WordCountSimple {
  }

  "WordCount" should {

    "test lines" in new SimpleTestContext("test") with TestWordCount {
      println(lines.take(10).toList)
      stop()
    }

    "test words" in new SimpleTestContext("test") with TestWordCount {
      println(words.take(10).toList)
      stop()
    }

    "testCount" in new SimpleTestContext("test") with TestWordCount {
      println(topWords(100).toList)
      stop()
    }
  }
}
