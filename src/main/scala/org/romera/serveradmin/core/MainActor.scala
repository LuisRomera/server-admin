package org.romera.serveradmin.core

import akka.actor.{Actor, ActorRef, Props}
import org.romera.serveradmin.core.config.FileConfiguration
import org.romera.serveradmin.core.config.FileConfiguration.NAME_ACTOR

object MainActor {
  case class ConfigFile()
  case class CreatorConfig(path: String)
}

class MainActor extends Actor {

  import MainActor._

  def createConfig(childRef: ActorRef): Receive = {
    case CreatorConfig(path) => childRef forward path
  }

  override def receive: Receive = {
    case ConfigFile =>
      val configFileRef = context.actorOf(Props[FileConfiguration], NAME_ACTOR)
      context.become(createConfig(configFileRef))
  }
}
