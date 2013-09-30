package utils

import akka.testkit.{ImplicitSender, TestKit}
import akka.actor.ActorSystem
import org.specs2.mutable.After
import com.typesafe.config.ConfigFactory

object AkkaTestConfiguration {

  val testConfiguration = ConfigFactory.parseString(
    """
      |akka.log-dead-letters-during-shutdown = false
    """.stripMargin)

}

abstract class Specs2AkkaSupport extends TestKit(ActorSystem("TestSystem", AkkaTestConfiguration.testConfiguration)) with ImplicitSender with After {
  override def after = TestKit.shutdownActorSystem(system)
}
