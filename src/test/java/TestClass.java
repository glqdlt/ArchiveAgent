import java.net.InetSocketAddress;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.testkit.JavaTestKit;
import akka.testkit.TestProbe;

public class TestClass {
	
	static ActorSystem sys;
	
	@BeforeClass
	public static void setup(){
		sys = ActorSystem.create();
	}

	
	@AfterClass
	public static void teardown(){
		JavaTestKit.shutdownActorSystem(sys);
		sys = null;
	}
}
