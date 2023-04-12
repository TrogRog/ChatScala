package homework.chat

import homework.chat.MyChatController
import akka.actor.typed.{ActorRef, Behavior}
import akka.actor.typed.receptionist.{Receptionist, ServiceKey}
import akka.actor.typed.scaladsl.{ActorContext, Behaviors}
import homework.chat.ChatDomain.{ChatMessage, Command, ListingResponse, UserMessage}

object ChatDomain {
  trait Command extends CborSerializable

  case class ChatMessage(nickname: String, contents: String) extends Command //отправь сообщение всем

  case class UserMessage(contents: String) extends Command

  case class EnterRoom(fullAddress: String, nickname: String) extends Command

  case class ListingResponse(listing: Receptionist.Listing) extends Command
}

object ChatBehavior {

  var textVis: MyChatController = _

  private case class ActorState(nickname: String, port: Int, members: Set[ActorRef[Command]])

  val ChatServiceKey: ServiceKey[Command] = ServiceKey[Command]("ChatServiceKey")
  var membersList: Set[ActorRef[Command]] = Set.empty

  def apply(nickname: String, port: Int, controller: MyChatController): Behavior[Command] = Behaviors.setup[Command] { ctx =>
    textVis = controller
    val listingResponseAdapter = ctx.messageAdapter[Receptionist.Listing](ListingResponse.apply)
    ctx.system.receptionist ! Receptionist.subscribe(ChatServiceKey, listingResponseAdapter)
    ctx.system.receptionist ! Receptionist.register(ChatServiceKey, ctx.self)
    behavior(ActorState(nickname, port, Set.empty), ctx)
  }

  private def behavior(state: ActorState, ctx: ActorContext[Command]): Behaviors.Receive[Command] =
    Behaviors.receiveMessage[Command] { message =>
      var resultState = state
      message match {
        case UserMessage(contents) => //inner message: GUI -> ChatActor
          println(s"send message: $contents")
          state.members.foreach(_ ! ChatMessage(state.nickname, contents))

        case ListingResponse(ChatServiceKey.Listing(list)) =>
          membersList = list
          resultState = state.copy(members = list.filter(_.ref != ctx.self))
          println(s"List of members(${list.size}) changed: ${list.mkString(", ")}")
        case ChatMessage(nickname, contents) =>
          textVis.showV(nickname, contents)
          println(s"$nickname say $contents")
        case m => println(s"Skip: $m")
      }
      behavior(resultState, ctx)
    }
}
