package homework.chat




import akka.actor.typed.{ActorRef, ActorSystem}
import akka.actor.typed.scaladsl.Behaviors
import com.typesafe.config.ConfigFactory
import homework.chat.ChatDomain._




object ChatCluster {


  var memberName: String = "Some"
  var actorSystem : ActorSystem[AnyRef] = _
  var nameVisitor: ActorRef[Command] = _
  var nameV: String = _


   def startup(nickname: String, port: Int)  :Unit = {
    val config = ConfigFactory.parseString(s"akka.remote.artery.canonical.port=$port").withFallback(ConfigFactory.load("application"))

    actorSystem = ActorSystem[AnyRef](Behaviors.empty, "ClusterSystem", config)
    val chatActor = actorSystem.systemActorOf(ChatBehavior(nickname, port), "chatActor")
     nameV = nickname

    /*scala.io.Source.stdin.getLines().foreach { line =>
      chatActor ! UserMessage(line)

    }*/
  }

  def run(name:String ,port:Int): Unit = {
    // start cluster

    startup(name ,port)

  }

}