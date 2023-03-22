package homework.chat


import javafx.event.ActionEvent
import javafx.fxml.FXML
import java.net.URL
import java.util.ResourceBundle


class MyChatController {

  @FXML private val resources: ResourceBundle = null
  @FXML private val location: URL = null


  @FXML private[chat] def TestButton(event: ActionEvent): Unit = {
    println("myChat")

  }

  @FXML private[chat] def initialize(): Unit = {
  }

}





