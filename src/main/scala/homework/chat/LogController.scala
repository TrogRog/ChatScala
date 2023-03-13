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
import javafx.application.Application
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.TextField
import javafx.stage.Stage


class LogController extends Application {


  @throws[IOException]
  override def start(stage: Stage): Unit = {
    val fxmlLoader = new FXMLLoader(classOf[LogController].getResource("log.fxml"))
    val scene = new Scene(fxmlLoader.load, 700, 400)
    stage.setTitle("MyChat")
    stage.setScene(scene)
    stage.show()


  }

  @FXML private val name: TextField = null
  @FXML private val logSeedNode: TextField = null

  @FXML private[chat] def authLogButtom(event: ActionEvent): Unit = {

  }

}