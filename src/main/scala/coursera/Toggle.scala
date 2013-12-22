package coursera

import akka.actor.Actor

/**
 * Coursera Reactive Programming Week 5 Lecture 5
 */
class Toggle extends Actor {
  def happy: Receive = {
    case "How are you?" =>
    sender ! "happy"
    context become sad
  }
  def sad: Receive = {
    case "How are you?" =>
    sender ! "sad"
    context become happy
  }
  def receive = happy
}