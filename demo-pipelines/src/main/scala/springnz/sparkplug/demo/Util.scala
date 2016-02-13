package springnz.sparkplug.demo

import better.files._

object Util {
  def baseDir(projectFolder: String = "demo-pipelines"): String = {
    val userDir = System.getProperty("user.dir").toFile.pathAsString
    if (userDir.endsWith(projectFolder)) userDir.substring(0, userDir.length - projectFolder.length - 1) else userDir
  }
}
