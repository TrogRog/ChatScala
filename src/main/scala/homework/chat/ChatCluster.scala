package homework.chat

import akka.actor.typed.{ActorRef, ActorSystem}
import akka.actor.typed.scaladsl.Behaviors
import com.typesafe.config.ConfigFactory
import homework.chat.ChatDomain._
import javafx.scene.control.TextArea

object ChatCluster {
  var memberName: String = "Some"
  var actorSystem: ActorSystem[AnyRef] = _
  var chatActor: ActorRef[Command] = _

  //  var textVis: MyChatController = _

  def startup(nickname: String, port: Int, controller: MyChatController): ActorSystem[AnyRef] = {
    val config = ConfigFactory.parseString(s"akka.remote.artery.canonical.port=$port").withFallback(ConfigFactory.load("application"))

    actorSystem = ActorSystem[AnyRef](Behaviors.empty, "ClusterSystem", config)
    chatActor = actorSystem.systemActorOf(ChatBehavior(nickname, port, controller), "chatActor")

    actorSystem
  }

  def run(name: String, port: Int, controller: MyChatController): ActorSystem[AnyRef] = {
    // start cluster
    startup(name, port, controller)
  }

}