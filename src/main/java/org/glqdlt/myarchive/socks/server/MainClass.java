package org.glqdlt.myarchive.socks.server;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class MainClass {

	public static void main(String[] args) {
		
		ActorSystem actorSystem = ActorSystem.create("EchoServer");
		ActorRef server = actorSystem.actorOf(Props.create(ServerActor.class), "listener");
		server.tell("start", null);

	}
}
