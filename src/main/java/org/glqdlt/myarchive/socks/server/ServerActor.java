package org.glqdlt.myarchive.socks.server;

import java.net.InetSocketAddress;

import com.typesafe.config.Config;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.io.Tcp;
import akka.io.TcpMessage;

public class ServerActor extends UntypedActor {

	final ActorRef tcpManager = Tcp.get(getContext().system()).getManager();

	Config config;
	LoggingAdapter log = Logging.getLogger(getContext().system(), this);

	public ServerActor() {
		// this.config = config;
	}

	@Override
	public void onReceive(Object msg) throws Exception {
		if (msg == "start") {
			String host = "127.0.0.1";
			int port = 15000;
			InetSocketAddress endpoint = new InetSocketAddress(host, port);
			Tcp.Command cmd = TcpMessage.bind(getSelf(), endpoint, 10);
			tcpManager.tell(cmd, getSelf());

		} else if (msg instanceof Tcp.Bound) {
			InetSocketAddress addr = ((Tcp.Bound) msg).localAddress();
			log.info("Bound to {}:{}", addr.getHostString(), addr.getPort());

		} else if (msg instanceof Tcp.Connected) {
			InetSocketAddress remote = ((Tcp.Connected) msg).remoteAddress();
			log.info("a new connection from {}:{}", remote.getHostString(), remote.getPort());

			ActorRef handler = getContext().actorOf(Props.create(EchoActor.class, remote, getSender()));
			Tcp.Command cmd = TcpMessage.register(handler);
			getSender().tell(cmd, getSelf());

		} else {
			unhandled(msg);
		}

	}

}
