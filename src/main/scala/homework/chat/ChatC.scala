package homework.chat

import akka.actor.{Address, AddressFromURIString}
import akka.actor.typed.{ActorRef, ActorSystem, Behavior}
import akka.actor.typed.scaladsl.Behaviors
import akka.cluster.typed.{Cluster, JoinSeedNodes}
import com.typesafe.config.ConfigFactory

object ChatC {
  var memberName: String = "Some"
  var actorSystem: ActorSystem[Nothing] = _


  def startup( port: Int): Unit = {
    // Override the configuration of the port and role
    val config = ConfigFactory
      .parseString(s"""
        akka.remote.artery.canonical.port=$port
        """)
      .withFallback(ConfigFactory.load("application"))

    val rootBehavior = Behaviors.setup[Nothing] { ctx =>
      val cluster = Cluster(ctx.system)


      Behaviors.empty
    }
    actorSystem = ActorSystem[Nothing](rootBehavior, "ClusterSystem", config)
  }

  def run(seedNode: String = "127.0.0.1:25251"): Unit = {
    // start cluster
    try {
      if (memberName == null) memberName = "User1"
      startup(25252)
    } catch {
      case _ =>
        try {
          if (memberName == null) memberName = "User2"
          startup( 25251)
        } catch {
          case _ =>
            if (memberName == null) memberName = "User3"
            startup( 25250)
        }
    }
    val seedNodes: List[Address] =
      List(s"akka://ClusterSystem@$seedNode").map(AddressFromURIString.parse)
    Cluster(actorSystem).manager ! JoinSeedNodes(seedNodes)

  }
}