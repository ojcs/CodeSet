package sep.util.net;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import sep.util.io.stream.StreamUtil;

public class Whois {
	private final InetAddress server;

	public Whois(final InetAddress server) throws IOException {
		this.server = server;
	}

	public String query(final String command) throws IOException {
		final String result;
		try (Socket socket = new Socket(server, 43)) {
			socket.getOutputStream().write((command + "\r\n").getBytes());
			result = new String(StreamUtil.readAll(socket.getInputStream()));
		}
		return result;
	}
}
