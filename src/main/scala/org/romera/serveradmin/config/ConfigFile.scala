package org.romera.serveradmin.config

import com.typesafe.config.{Config, ConfigFactory}
import org.romera.serveradmin.config.ConfigFile.{Application, Host, LOGGER, Mail, getHost}

import java.net.{InetAddress, InterfaceAddress, NetworkInterface}
import java.util.logging.Logger

class ConfigFile(args: Array[String]) {

  LOGGER.info("Init configuration")
  private val path: String = args(0)
  val config: Config = ConfigFactory.parseResources(path)



  final val app = Application(config.getString("app.name"),
    Host(InetAddress.getLocalHost.getHostName, getHost),
    Mail(config.getString("mail.host"), config.getInt("mail.port"),
      config.getString("mail.username"), config.getString("mail.password"),
      config.getBoolean("mail.auth"), config.getBoolean("mail.starttls"))
  )
}

object ConfigFile {
  private final val LOGGER = Logger.getLogger(this.getClass.getName)

  protected case class Host(name: String, ip: String)

  protected case class Mail(host: String, port: Int, username: String, password: String, auth: Boolean,
                            starttls: Boolean)

  protected case class Application(name: String, host: Host, mail: Mail)

  def getHost: String = NetworkInterface
    .getNetworkInterfaces.nextElement().getInterfaceAddresses.toArray()
    .filter(_.toString.contains("192"))(0).asInstanceOf[InterfaceAddress].getAddress.getHostAddress


}
