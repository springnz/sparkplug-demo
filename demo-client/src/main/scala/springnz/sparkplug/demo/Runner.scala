package springnz.sparkplug.demo

import better.files._
import springnz.sparkplug.client.ClientExecutor

import scala.util.{ Failure, Success }

object Runner {
  import ClientExecutor._

  def main(args: Array[String]) {

    val clientExecutorParams = ClientExecutorParams(jarPath = Some(Util.baseDir("demo-client")))
    val executor: ClientExecutor = ClientExecutor.create(clientExecutorParams)

    val pluginName = ""

    import scala.concurrent.ExecutionContext.Implicits.global
    executor.execute(pluginName, None) onComplete {
      case Success(result) ⇒
        val msg = s"Finished running SparkPlug plugin '$pluginName'"
        log.info(msg)
      case Failure(failure) ⇒
        val msg = s"Failed running SparkPlug plugin '$pluginName'"
        log.error(msg)
    }
  }

}
