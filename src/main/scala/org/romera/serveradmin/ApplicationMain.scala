package org.romera.serveradmin

import org.romera.serveradmin.config.ConfigFile

object ApplicationMain {
  def main(args: Array[String]): Unit = {
    val env = new ConfigFile(args).app
  }
}
