package homework.chat


import javafx.event.ActionEvent
import javafx.fxml.{FXML, FXMLLoader}
import javafx.scene.Scene
import javafx.scene.control.{Button, TextField}
import javafx.stage.Stage

import java.net.URL
import java.util.ResourceBundle


class LogController {


  @FXML private val resources: ResourceBundle = null
  @FXML private val location: URL = null
  @FXML private var logSeedNode: TextField = _
  @FXML private var nameLog: TextField = _
  private var ok: Boolean = false


  @FXML private[chat] def authLogButton(event: ActionEvent): Unit = {
    //    nameLog.getScene.getWindow.hide()
    //
    //    val nameVisitor: String  = nameLog.getText
    //    val nameV: String  = nameLog.getText
    //    val portLog: Int = logSeedNode.getText.toInt
    //
    //    val primaryStage = new Stage
    //    val fxmlLoader = new FXMLLoader(classOf[MyChatController].getResource("/MyChat.fxml"))
    //    val scene = new Scene(fxmlLoader.load, 700, 400)
    //    val controller: MyChatController = fxmlLoader.getController
    //    primaryStage.setTitle("MyChat")
    //    primaryStage.setScene(scene)
    //    primaryStage.show
    //
    //
    //    ChatCluster.run(nameVisitor , portLog, controller)
    //ChatCluster.startup(nameVisitor, portLog)
    ok = true
    event.getSource.asInstanceOf[Button].getScene.getWindow.hide()
  }

  def getCredentials: Option[Credentials] = {
    if (ok) Some(Credentials(nameLog.getText, logSeedNode.getText.toInt))
    else None
  }

  @FXML private[chat] def initialize(): Unit = {

  }
}














