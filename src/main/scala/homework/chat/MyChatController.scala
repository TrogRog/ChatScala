package homework.chat

import akka.actor.{ActorSystem, Props}
import com.typesafe.config.ConfigFactory
import homework.chat.scala.docs.cluster.SimpleClusterListener

import javafx.application.Application
import javafx.event.ActionEvent
import javafx.fxml.{FXML, FXMLLoader}
import javafx.scene.Scene
import javafx.stage.Stage

import java.io.IOException
import java.net.URL
import java.util.ResourceBundle


class MyChatController {
  /*val config = ConfigFactory.load("application.conf")
 val system = ActorSystem("system", config)
 system.actorOf(Props[SimpleClusterListener], "SimpleClusterListener")*/

 /* startup(Seq("2551"))

  private def startup(port: Seq[String]): Unit = {
    val config = ConfigFactory.parseString(
      s"""
            akka.remote.netty.tcp.port=$port
            """).withFallback(ConfigFactory.load())

    // Create an Akka system
    val system = ActorSystem("ClusterSystem", config)
    // Create an actor that handles cluster domain events
    system.actorOf(Props[SimpleClusterListener], name = "clusterListener")
  }*/
  @FXML private val resources: ResourceBundle = null
  @FXML private val location: URL = null
  //@FXML private val TextField = readLine()

  @FXML private[chat] def TestButton(event: ActionEvent): Unit = {
    println("myChat")

  }

  @FXML private[chat] def initialize(): Unit = {
  }

}





