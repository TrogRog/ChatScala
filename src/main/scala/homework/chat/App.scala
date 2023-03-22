package homework.chat


import javafx.application.Application

import javafx.fxml.FXMLLoader

import javafx.scene.Scene

import javafx.stage.Stage


object App{
  def main(args: Array[String]): Unit = {
    Application.launch(classOf[App], args: _*)
  }
}

class App extends Application {
  override def start(stage: Stage): Unit = {
    val fxmlLoader = new FXMLLoader(classOf[LogController].getResource("/log.fxml"))
    val scene = new Scene(fxmlLoader.load, 700, 400)
    val controller: LogController = fxmlLoader.getController
    stage.setTitle("MyChat")
    stage.setScene(scene)
    stage.show

  }


}