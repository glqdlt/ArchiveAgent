import java.net.InetSocketAddress;

import org.glqdlt.myarchive.socks.client.ClientActor;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.io.Tcp;
import akka.io.TcpMessage;
import akka.testkit.JavaTestKit;
import akka.testkit.TestProbe;
import akka.util.ByteString;

public class TestClientActor {

	static ActorSystem sys;

	@BeforeClass
	public static void setup() {
		sys = ActorSystem.create();
	}

	@AfterClass
	public static void teardown() {
		JavaTestKit.shutdownActorSystem(sys);
		sys = null;
	}

	@Test
	public void testClientActor() {
		InetSocketAddress inetSocketAddress = new InetSocketAddress("localhost", 15000);
		InetSocketAddress inetSocketAddressLocal = new InetSocketAddress("localhost", 15001);

		TestProbe tcpProbe = new TestProbe(sys);

		ActorRef tcpRef = tcpProbe.ref();
		ActorRef clientActor = sys.actorOf(ClientActor.props(inetSocketAddress, tcpRef), "clientActor");

		tcpProbe.expectMsg(TcpMessage.connect(inetSocketAddress));

		tcpProbe.send(clientActor, new Tcp.Connected(inetSocketAddress, inetSocketAddressLocal));

		tcpProbe.expectMsg(TcpMessage.register(clientActor));

		String hello = "hello";
		tcpProbe.expectMsg(TcpMessage.write(ByteString.fromArray(hello.getBytes())));

		tcpProbe.send(clientActor, new Tcp.Received(ByteString.fromArray(("echo " + hello).getBytes())));
	}
}
