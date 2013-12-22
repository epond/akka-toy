package coursera

import org.specs2.mutable.Specification
import akka.actor.{Props, ActorSystem}
import akka.testkit.{ImplicitSender, TestKit, TestProbe}
import scala.concurrent.duration._
import org.specs2.time.NoTimeConversions

/**
 * Coursera Reactive Programming Week 5 Lecture 5
 *
 * Mixing in NoTimeConversions to get around ambiguity of duration described here:
 * http://doc.akka.io/docs/akka/snapshot/scala/testing.html
 */
case class ToggleSpec() extends Specification with NoTimeConversions {
  "A Toggle" should {
    "be testable with TestProbe as remote-controlled actor" in {
      implicit val system = ActorSystem("TestSys")
      val toggle = system.actorOf(Props[Toggle])
      val p = TestProbe()
      p.send(toggle, "How are you?")
      p.expectMsg("happy")
      p.send(toggle, "How are you?")
      p.expectMsg("sad")
      p.send(toggle, "unknown")
      p.expectNoMsg(1.second)
      system.shutdown()
    }
    
    "be testable by running a test within a TestProbe" in {
      new TestKit(ActorSystem("TestSys")) with ImplicitSender {
        val toggle = system.actorOf(Props[Toggle])
        toggle ! "How are you?"
        expectMsg("happy")
        toggle ! "How are you?"
        expectMsg("sad")
        toggle ! "unknown"
        expectNoMsg(1.second)
        system.shutdown()
      }
      success
    }
  }
}
