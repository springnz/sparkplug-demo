package springnz.sparkplug.demo

import org.scalatest._
import springnz.sparkplug.client.ClientExecutor

import scala.concurrent.Await
import scala.concurrent.duration._

class ExecutorTests extends fixture.WordSpec with ShouldMatchers with Inspectors {
  implicit val ec = scala.concurrent.ExecutionContext.global

  override type FixtureParam = ClientExecutor

  override def withFixture(test: OneArgTest) = {
    var executor: ClientExecutor = null

    try {
      executor = ClientExecutor.create()
      withFixture(test.toNoArgTest(executor))
    } finally {
      if (executor != null) executor.shutDown()
      Thread.sleep(1000)
    }
  }

  "client executor" should {
    "Calculate a single job" in { executor ⇒
      val future = executor.execute[Any]("springnz.sparkplug.demo.LetterCountPlugin", None)
      val result = Await.result(future, 30 seconds)
      result shouldBe ((2, 2))
    }

    "Calculate word counts using SparkPlug" in { executor ⇒
      pending
    }
  }
}
