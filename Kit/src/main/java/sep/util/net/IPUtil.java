package sep.util.net;

import java.net.InetAddress;

public final class IPUtil {
	public static boolean isReservedAddr(InetAddress address) {
		return address.isAnyLocalAddress() || address.isLinkLocalAddress() || address.isLoopbackAddress();
	}
	
	private IPUtil() {
	}
}
