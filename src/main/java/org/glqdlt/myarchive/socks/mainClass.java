package org.glqdlt.myarchive.socks;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class mainClass {

	public static void main(String[] args) {
		
		ActorSystem actorSystem = ActorSystem.create("EchoServer");
		ActorRef server = actorSystem.actorOf(Props.create(Listener.class), "listener");
		server.tell("start", null);

	}
}
