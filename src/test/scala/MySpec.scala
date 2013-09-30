import akka.actor.Actor
import akka.actor.Props
import org.specs2.mock.Mockito
import org.specs2.mutable.Specification
import utils.Specs2AkkaSupport

object MySpec {
  class EchoActor extends Actor {
    def receive = {
      case x ⇒ sender ! x
    }
  }
}

case class MySpec() extends Specification with Mockito {

  import MySpec._

  "An Echo actor" should {

    "send back messages unchanged" in new Specs2AkkaSupport {
      val echo = system.actorOf(Props[EchoActor])
      echo ! "hello world"
      expectMsg("hello world")
    }

  }
}