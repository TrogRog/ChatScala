package homework.chat



import akka.actor.typed.ActorRef
import homework.chat.ChatBehavior.membersList
import homework.chat.ChatCluster.nameV
import homework.chat.ChatDomain.ChatMessage
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.{ListView, TextArea, TextField}

import java.net.URL
import java.util.ResourceBundle


class MyChatController {
  @FXML private val resources: ResourceBundle = null
  @FXML private val location: URL = null
  @FXML private var messageVisitor: TextField = _
  @FXML private var text: TextArea = _
  @FXML private var visitors: ListView[_] = _


  @FXML private[chat] def sendButton(event: ActionEvent): Unit = {
    sendGroupMessage(messageVisitor.getText)

    messageVisitor.setText("")
  }

  def sendGroupMessage(message: String): Unit = {
    membersList.foreach(member =>
      /*if (member != ChatCluster.nameVisitor)*/
      member ! ChatMessage(nameV, message)
      )
      text.appendText(message)
  }



  @FXML private[chat] def initialize(): Unit = {

  }

}
