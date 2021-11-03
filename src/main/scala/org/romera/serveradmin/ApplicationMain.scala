package org.romera.serveradmin

import org.romera.serveradmin.config.ConfigFile

import java.util.logging.Logger

object ApplicationMain {
  def main(args: Array[String]): Unit = {
    val env = new ConfigFile(args).app
    Logger.getLogger(this.getClass.getName).info("Init application\n" +
      s"${List.fill(100)("-").mkString("")}\n" +
      s"\t- Name: ${env.name} \n" +
      "\t- Host: \n" +
      s"\t\t name: ${env.host.name} \n" +
      s"\t\t local-ip: ${env.host.ip} \n" +
      "\n")
  }
}
