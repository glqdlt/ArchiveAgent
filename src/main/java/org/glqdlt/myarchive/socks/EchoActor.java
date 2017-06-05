package org.glqdlt.myarchive.socks;

import java.net.InetSocketAddress;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.io.Tcp;
import akka.io.TcpMessage;
import akka.util.ByteString;

public class EchoActor extends UntypedActor {

	InetSocketAddress remote;
	ActorRef connection;

	LoggingAdapter log = Logging.getLogger(getContext().system(), this);

	public EchoActor(InetSocketAddress remote, ActorRef connection) {

		this.connection = connection;
		this.remote = remote;

	}

	@Override
	public void onReceive(Object msg) throws Exception {

		if (msg instanceof Tcp.Received) {
			ByteString data = ((Tcp.Received) msg).data();
			log.info("msg..: " + data);
			Tcp.Command cmd = TcpMessage.write(data);
			// echo.. ping 온 것을 그대로 ping 으로 메아리 
			connection.tell(cmd, getSelf());

		} else if (msg instanceof Tcp.ConnectionClosed) {
			log.info("The Connection {}:{} is closed", remote.getHostString(), remote.getPort());
			getContext().stop(getSelf());
		} else {
			unhandled(msg);
		}

	}

}
