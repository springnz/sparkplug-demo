package springnz.sparkplug.demo

import org.scalatest._
import springnz.sparkplug.client.ClientExecutor
import springnz.util.Logging

import scala.concurrent.Await
import scala.concurrent.duration._

class ExecutorTests extends fixture.WordSpec with ShouldMatchers with Logging with Inspectors {
  implicit val ec = scala.concurrent.ExecutionContext.global

  override type FixtureParam = ClientExecutor

  override def withFixture(test: OneArgTest) = {
    var executor: ClientExecutor = null

    try {
      executor = ClientExecutor.create()
      withFixture(test.toNoArgTest(executor)) // "loan" the fixture to the test
    } finally {
      if (executor != null) executor.shutDown()
      Thread.sleep(1000)
    }
  }

  "client executor" should {
    "Calculate a single job" in { executor ⇒
      val future = executor.execute[Any]("springnz.sparkplug.examples.LetterCountPlugin", None)
      val result = Await.result(future, 30 seconds)
      result shouldBe ((2, 2))
    }

    "work for SolverIndexWriterTestPlugin" in { executor ⇒
    }
  }
}
