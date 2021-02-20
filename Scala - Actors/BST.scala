import scala.io.StdIn
import akka.actor._

case class Wstaw(n: Int)
case class Znajdź(n: Int)

class Węzeł extends Actor {

  def liść(wartość: Int): Receive = {
    case Wstaw(n: Int) => {
      if (n > wartość) {
        val pp = context.system.actorOf(Props[Węzeł]())
        pp ! Wstaw(n)
        context.become(
          zPrawymPoddrzewem(wartość, pp)
        )
      } else if (n < wartość) {
        val lp = context.system.actorOf(Props[Węzeł]())
        lp ! Wstaw(n)
        context.become(
          zLewymPoddrzewem(lp, wartość)
        )
      }
    }

    case Znajdź(n: Int) => {
      if (n == wartość) {
        println(s"${self.path.name} has value ${n}")
      } else {
        println(s"value ${n} not found in tree")
      }
    }
  }

  def zLewymPoddrzewem(lewe: ActorRef, wartość: Int): Receive = {
    case Wstaw(n: Int) => {
      if (n > wartość) {
        val pp = context.system.actorOf(Props[Węzeł]())
        pp ! Wstaw(n)
        context.become(zPoddrzewami(lewe, wartość, pp))
      } else if (n < wartość) {
        lewe ! Wstaw(n)
      }
    }

    case Znajdź(n: Int) => {
      if (n > wartość) {
        println(s"value ${n} not found in tree")
      } else if (n == wartość) {
        println(s"${self.path.name} has value ${n}")
      } else {
        lewe ! Znajdź(n)
      }
    }
  }

  def zPrawymPoddrzewem(wartość: Int, prawe: ActorRef): Receive = {
    case Wstaw(n: Int) => {
      if (n > wartość) {
        prawe ! Wstaw(n)
      } else if (n < wartość) {
        val lp = context.system.actorOf(Props[Węzeł]())
        lp ! Wstaw(n)
        context.become(zPoddrzewami(lp, wartość, prawe))
      }
    }

    case Znajdź(n: Int) => {
      if (n > wartość) {
        prawe ! Znajdź(n)
      } else if (n == wartość) {
        println(s"${self.path.name} has value ${n}")
      } else {
        println(s"value ${n} not found in tree")
      }
    }
  }

  def zPoddrzewami(lewe: ActorRef, wartość: Int, prawe: ActorRef): Receive = {
    case Wstaw(n: Int) => {
      if (n > wartość) {
        prawe ! Wstaw(n)
      } else if (n < wartość) {
        lewe ! Wstaw(n)
      }
    }

    case Znajdź(n: Int) => {
      if (n > wartość) {
        prawe ! Znajdź(n)
      } else if (n == wartość) {
        println(s"${self.path.name} has value ${n}")
      } else {
        lewe ! Znajdź(n)
      }
    }

  }

  def receive: Receive = { case Wstaw(n: Int) =>
    context.become(liść(n))
  }
}

object BST extends App {
  val system = ActorSystem("system")
  val root = system.actorOf(Props[Węzeł]())
  val rand = new util.Random()
  for {
    i <- 1 to 10
  } {
    root ! Wstaw(rand.nextInt(10))
  }
  for {
    i <- 1 to 10
  } root ! Znajdź(rand.nextInt(10))

  println("-" * 50 + ":)" + "-" * 50)
  StdIn.readLine()
  system.terminate()
}
