package homework.chat



import java.net.URL
import java.util.ResourceBundle
import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.TextField
import javafx.application.Application
import javafx.event.{ActionEvent, EventHandler}
import javafx.fxml.{FXML, FXMLLoader}
import javafx.scene.{Parent, Scene}
import javafx.stage.Stage



class LogController{



  @FXML private val resources: ResourceBundle = null
  @FXML private val location: URL = null
  @FXML private var logSeedNode: TextField = _
  @FXML private var name: TextField = _


  @FXML private[chat] def authLogButton(event: ActionEvent): Unit = {
     name.getScene.getWindow.hide()
    val stage = new Stage
    val fxmlLoader = new FXMLLoader(classOf[MyChatController].getResource("/MyChat.fxml"))
    val scene = new Scene(fxmlLoader.load, 700, 400)
    stage.setTitle("MyChat")
    stage.setScene(scene)
    stage.show
    var po: String = logSeedNode.getText
    //ChatC.startup(logSeedNode)
    println(s"$po")
    ChatCluster.startup(s"$po")
    //ChatCluster.startup(25252)
    //ChatCluster.startup(0)


  }

  @FXML private[chat] def initialize(): Unit = {

  }
}














