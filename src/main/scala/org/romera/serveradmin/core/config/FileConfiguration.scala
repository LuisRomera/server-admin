package org.romera.serveradmin.core.config

import akka.actor.Actor
import akka.event.{Logging, LoggingAdapter}
import com.typesafe.config.{Config, ConfigFactory}

import java.net.{InetAddress, InterfaceAddress, NetworkInterface}

object FileConfiguration {

  val NAME_ACTOR = "FileConfiguration"

  case class FilePath(string: String)

  protected case class Host(name: String, ip: String)

  protected case class Mail(host: String, port: Int, username: String, password: String, auth: Boolean,
                            starttls: Boolean)

  protected case class Application(name: String, host: Host, mail: Mail)

  def getHost: String = NetworkInterface
    .getNetworkInterfaces.nextElement().getInterfaceAddresses.toArray()
    .filter(_.toString.contains("192"))(0).asInstanceOf[InterfaceAddress].getAddress.getHostAddress
}


class FileConfiguration extends Actor {

  import FileConfiguration._

  val log: LoggingAdapter = Logging(context.system, this)
  var applicationConfig: Application = _


  override def receive: Receive = {

    case filePath: String => if (applicationConfig == null) {
      val config: Config = ConfigFactory.parseResources(filePath)

      log.info("Create config")

      applicationConfig = Application(config.getString("app.name"),
        Host(InetAddress.getLocalHost.getHostName, getHost),
        Mail(config.getString("app.mail.host"), config.getInt("app.mail.port"),
          config.getString("app.mail.username"), config.getString("app.mail.password"),
          config.getBoolean("app.mail.auth"), config.getBoolean("app.mail.starttls"))
      )
    }
  }
}
