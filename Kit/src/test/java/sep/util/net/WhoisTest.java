package sep.util.net;

import java.io.IOException;
import java.net.InetAddress;

import org.junit.Test;

public class WhoisTest {
	@Test
	public void testQuery() throws IOException {
		Whois whois = new Whois(InetAddress.getByName("whois.verisign-grs.com"));
		System.out.println(whois.query("help"));
	}
}
