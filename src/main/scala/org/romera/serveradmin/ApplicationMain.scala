package org.romera.serveradmin

import akka.actor.{ActorSystem, Props}
import org.romera.serveradmin.core.MainActor
import org.romera.serveradmin.core.MainActor.{ConfigFile, CreatorConfig}

object ApplicationMain {
  def main(args: Array[String]): Unit = {

    val system = ActorSystem("InitApplication")
    val core = system.actorOf(Props[MainActor], "main")
    core ! ConfigFile
    core ! CreatorConfig("config/config-dev.conf")



//    val env = new ConfigFile(args).app
//    Logger.getLogger(this.getClass.getName).info("Init application\n" +
//      s"${List.fill(100)("-").mkString("")}\n" +
//      s"\t- Name: ${env.name} \n" +
//      "\t- Host: \n" +
//      s"\t\t name: ${env.host.name} \n" +
//      s"\t\t local-ip: ${env.host.ip} \n" +
//      "\n")
  }
}
