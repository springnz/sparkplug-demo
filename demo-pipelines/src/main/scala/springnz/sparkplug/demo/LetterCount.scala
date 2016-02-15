package springnz.sparkplug.demo

import com.typesafe.scalalogging.LazyLogging
import org.apache.spark.rdd.RDD
import springnz.sparkplug.core.{ SparkOperation, SparkPlugin }

class LetterCountPlugin extends LetterCount with SparkPlugin {
  override def apply(input: Option[Any]): SparkOperation[(Long, Long)] = super.apply()
}

class LetterCount extends LazyLogging {

  def apply(): SparkOperation[(Long, Long)] = SparkOperation { ctx ⇒

    val textRDDProvider = SparkOperation[RDD[String]] {
      ctx ⇒ ctx.makeRDD("There is nothing either good or bad, but thinking makes it so".split(' '))
    }

    val nums = for {
      // on-site decision what to plug in - different to VaultEmails for example
      logData ← textRDDProvider
      numAs = logData.filter(_.contains("a")).count()
      numBs = logData.filter(_.contains("b")).count()
    } yield {
      logger.info(s"$numAs 'a's, $numBs 'b's")
      (numAs, numBs)
    }

    nums.run(ctx)
  }
}
