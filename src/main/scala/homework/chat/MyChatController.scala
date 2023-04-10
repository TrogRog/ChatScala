package homework.chat



import akka.actor.typed.ActorRef
import homework.chat.ChatBehavior.membersList
import homework.chat.ChatCluster.nameV
import homework.chat.ChatDomain.{ChatMessage, Command}
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


  @FXML private[chat] def sendButton(event: ActionEvent): Unit = {
    val mes = messageVisitor.getText
    show(nameV, mes)
    membersList.foreach(member =>
       member ! ChatMessage(nameV, mes))
    messageVisitor.setText("")
  }

  def show(nickname:String, message: String): Unit = {

    val date = new Date
    val df = new SimpleDateFormat("dd-MM-yyyy HH:mm")
    val dateTimeString = df.format(date)
    val textLine = s"\n$dateTimeString [${nickname}]: ${message}\n"
    text.appendText(textLine)

  }

  @FXML private[chat] def initialize(): Unit = {

  }

}
