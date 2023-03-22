package homework.chat


import akka.actor.typed.scaladsl.Behaviors
import akka.actor.typed.ActorSystem
import akka.actor.typed.Behavior
import akka.cluster.typed.Cluster
import com.typesafe.config.ConfigFactory


object ChatCluster {


    object RootBehavior {
      def apply(): Behavior[Nothing] = Behaviors.setup[Nothing] { context =>
        // Create an actor that handles cluster domain events
        context.spawn(ClusterListener(), "ClusterListener")

        Behaviors.empty
      }
    }

    def main(args: Array[String]): Unit = {
      val ports =
        if (args.isEmpty)
          Seq(25251, 25252, 0)
        else
          args.toSeq.map(_.toInt)
      ports.foreach(startup)
    }

    def startup(port: Int): Unit = {
      // Override the configuration of the port
      val config = ConfigFactory.parseString(
        s"""
        akka.remote.artery.canonical.port=$port
        """).withFallback(ConfigFactory.load())

      val rootBehavior = Behaviors.setup[Nothing] { ctx =>
        val cluster = Cluster(ctx.system)


        Behaviors.empty
      }
      // Create an Akka system
      ActorSystem[Nothing](RootBehavior(), "ClusterSystem", config)
    }
}

