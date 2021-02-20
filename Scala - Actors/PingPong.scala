import scala.io._
import akka.actor.{ActorSystem, Actor, Props, ActorRef}

object Player {
  case class PlayWith(a: ActorRef)
  case object Ping
  case object Pong

  case class Play(a: ActorRef, max: Int)
  case class PingLimit(current: Int, max: Int)
  case class PongLimit(current: Int, max: Int)

  case class PlayCircle(players: List[ActorRef], max: Int)
  case class PingCircular(
      all: List[ActorRef],
      remaining: List[ActorRef],
      current: Int,
      max: Int
  )
  case class PongCircular(
      all: List[ActorRef],
      remaining: List[ActorRef],
      current: Int,
      max: Int
  )
}

class Player extends Actor {
  import Player._
  def receive: Receive = {
    case PlayWith(a) =>
      println(s"${self.path.name}: Playing ${a.path.name}")
      println(s"${self.path.name}: Ping")
      a ! Pong

    case Ping =>
      println(s"${self.path.name}: Ping")
      sender() ! Pong

    case Pong =>
      println(s"${self.path.name}: Pong")
      sender() ! Ping

    case Play(a, max) =>
      if (max == 0) context.system.terminate()
      else {
        println(s"${self.path.name}: Playing ${a.path.name} for $max exchanges")
        println(s"${self.path.name}: Ping")
        a ! PongLimit(0, max)
      }

    case PingLimit(current, max) =>
      if (current == max) context.system.terminate()
      else {
        println(s"${self.path.name}: Ping")
        sender() ! PongLimit(current, max)
      }

    case PongLimit(current, max) => {
      println(s"${self.path.name}: Pong")
      sender() ! PingLimit(current + 1, max)
    }

    case PlayCircle(players, max) =>
      if (max == 0) context.system.terminate()
      else {
        println(
          s"${self.path.name}: Playing ${players.length} others for $max exchanges"
        )
        println(s"${self.path.name}: Ping")
        players.tail.head ! PongCircular(players, players.tail.tail, 0, max)
      }

    case PingCircular(all, remaining, current, max) =>
      if (current == max) context.system.terminate()
      else {
        println(s"${self.path.name}: Ping")
        if (remaining.isEmpty) {
          all.head ! PongCircular(all, all.tail, current + 1, max)
        } else {
          remaining.head ! PongCircular(all, remaining.tail, current, max)
        }
      }

    case PongCircular(all, remaining, current, max) =>
      if (current == max) context.system.terminate()
      else {
        println(s"${self.path.name}: Pong")
        if (remaining.isEmpty) {
          all.head ! PingCircular(all, all.tail, current + 1, max)
        } else {
          remaining.head ! PingCircular(all, remaining.tail, current, max)
        }
      }

  }
}

object PingPong extends App {
  import Player._
  val sys = ActorSystem("sys")
  val addrOfJohn = sys.actorOf(Props[Player](), "John")
  val addrOfKate = sys.actorOf(Props[Player](), "Kate")
  val addrOfTom = sys.actorOf(Props[Player](), "Tom")

  // addrOfJohn ! PlayWith(addrOfKate)
  // addrOfJohn ! Play(addrOfKate, 5)
  def playInCircle(n: Int, max: Int) = {
    val players =
      (1 to n).toList.map(i => sys.actorOf(Props[Player](), i.toString()))
    players.head ! PlayCircle(players, max)
  }
  playInCircle(10, 3)

  println("-" * 50 + "press ANY key to quit" + "-" * 50)
  StdIn.readLine()
  sys.terminate()
}
