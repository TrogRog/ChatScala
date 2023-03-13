package homework.chat


import javafx.application.Application
import javafx.event.ActionEvent
import javafx.fxml.FXMLLoader
import javafx.geometry.{Insets, Pos}
import javafx.scene.{Parent, Scene}
import javafx.scene.control.{Button, Label, TextField}
import javafx.scene.layout.VBox
import javafx.stage.Stage


object App {
  def main(args: Array[String]):Unit= {
    Application.launch(classOf[LogController], args: _*)
  }




}
