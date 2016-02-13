package springnz.sparkplug

import springnz.sparkplug.testkit.ProjectName

package object demo {
  implicit val projectName = ProjectName("demo-client")
}
