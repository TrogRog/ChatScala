package homework.chat


import homework.chat.ChatDomain._
import javafx.application.Platform
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.{ListView, TextArea, TextField}

import java.net.URL
import java.text.SimpleDateFormat
import java.util.{Date, ResourceBundle}


class MyChatController {


  @FXML private val resources: ResourceBundle = null
  @FXML private val location: URL = null
  @FXML private var messageVisitor: TextField = _
  @FXML private var text: TextArea = _
  @FXML private var visitors: ListView[_] = _

  var memberName: String = _

  @FXML private[chat] def sendButton(event: ActionEvent): Unit = {
    val mes = messageVisitor.getText
    showV(memberName, mes)
    ChatCluster.chatActor ! UserMessage(mes)
    messageVisitor.setText("")

  } 

  def showV(nickname: String, message: String): Unit = {
    Platform.runLater(() => {
      val date = new Date
      val df = new SimpleDateFormat("dd-MM-yyyy HH:mm")
      val dateTimeString = df.format(date)
      val textLine = s"\n$dateTimeString [${nickname}]: ${message}\n"
      text.appendText(textLine)
    })
  }

  @FXML private[chat] def initialize(): Unit = {

  }

}
