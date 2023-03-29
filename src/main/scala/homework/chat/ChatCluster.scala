package homework.chat


import akka.actor.{Address, AddressFromURIString}
import akka.actor.typed.{ActorSystem, Behavior}
import akka.actor.typed.scaladsl.Behaviors
import akka.cluster.typed.{Cluster, JoinSeedNodes}
import com.typesafe.config.ConfigFactory


object ChatCluster {

  var memberName: String = "Some"
  var actorSystem: ActorSystem[Nothing] = _

  object RootBehavior {
    def apply(): Behavior[Nothing] = Behaviors.setup[Nothing] { context =>
      // Create an actor that handles cluster domain events
      context.spawn(ClusterListener(), "ClusterListener")

      Behaviors.empty
    }
  }

  def startup(port: Int): Unit = {
    // Override the configuration of the port
    val config = ConfigFactory.parseString(
      s"""
      akka.remote.artery.canonical.port=$port
      """).withFallback(ConfigFactory.load("application"))

    // Create an Akka system
    ActorSystem[Nothing](RootBehavior(), "ClusterSystem", config)
  }

  def run(port:Int): Unit = {
    // start cluster

    startup(port)

  }
}