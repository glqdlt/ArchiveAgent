import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

import org.junit.Test;

public class TestClientSimpleJavaIO {

	@Test
	public void connectServerActor() throws IOException {
		try (Socket client = new Socket()) {
			InetSocketAddress ipep = new InetSocketAddress("127.0.0.1", 15000);
			client.connect(ipep);
			try (OutputStream sender = client.getOutputStream(); InputStream reveiver = client.getInputStream();) {
				sender.write("StartComeonBaby".getBytes());

			}

		}
	}

}
