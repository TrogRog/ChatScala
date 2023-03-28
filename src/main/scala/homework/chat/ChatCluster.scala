package homework.chat


import akka.actor.typed.{ActorSystem, Behavior}
import akka.actor.typed.scaladsl.Behaviors
import akka.cluster.typed.Cluster
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

  def startup(port: String): Unit = {
    // Override the configuration of the port
    val config = ConfigFactory.parseString(
      s"""
      akka.remote.artery.canonical.port=$port
      """).withFallback(ConfigFactory.load("application"))
    /*val config = ConfigFactory.parseString(
      s"""
  akka.remote.artery.canonical.hostname = 127.0.0.1
  akka.remote.artery.canonical.port=$port
  """).withFallback(ConfigFactory.load("application"))*/
    // Create an Akka system
    ActorSystem[Nothing](RootBehavior(), "ClusterSystem", config)
  }

 /* def startup(port: String): Unit = {
    // Override the configuration of the port and role
    val config = ConfigFactory
      .parseString(
        s"""
      akka.remote.artery.canonical.port=$port
      """)
      .withFallback(ConfigFactory.load("application"))

    val rootBehavior = Behaviors.setup[Nothing] { ctx =>
      val cluster = Cluster(ctx.system)


      Behaviors.empty
    }
    actorSystem = ActorSystem[Nothing](rootBehavior, "ClusterSystem", config)
  }*/


}