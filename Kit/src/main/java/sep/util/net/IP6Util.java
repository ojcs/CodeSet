package sep.util.net;

import static java.util.regex.Pattern.compile;
import static java.util.regex.Pattern.matches;

import java.io.IOException;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

import sep.util.collection.Cursor;

public final class IP6Util {
	public static boolean checkIPv6Format(final String ip) {
		String address = ip.trim();
		// in many cases such as URLs, IPv6 addresses are wrapped by []
		if (address.substring(0, 1).equals("[") && address.substring(address.length() - 1).equals("]")) {
			address = address.substring(1, address.length() - 1);
		}
		return
		// a valid IPv6 address should contains no less than 1,
		   (1 < compile(":").split(address).length)
		// and no more than 7 “:” as separators
		&& (compile(":").split(address).length <= 8)
		// the address can be compressed, but “::” can appear only once
		&& (compile("::").split(address).length <= 2)
		// if a compressed address
		&& (compile("::").split(address).length == 2)
		// if starts with “::” – leading zeros are compressed
		? (((address.substring(0, 2).equals("::"))
			? matches("^::([\\da-f]{1,4}(:)){0,4}(([\\da-f]{1,4}(:)[\\da-f]{1,4})|([\\da-f]{1,4})|((\\d{1,3}.){3}\\d{1,3}))",address)
				: matches("^([\\da-f]{1,4}(:|::)){1,5}(([\\da-f]{1,4}(:|::)[\\da-f]{1,4})|([\\da-f]{1,4})|((\\d{1,3}.){3}\\d{1,3}))", address)))
				// if ends with "::" - ending zeros are compressed
				: ((address.substring(address.length() - 2).equals("::"))
						? matches("^([\\da-f]{1,4}(:|::)){1,7}", address) 
								: matches("^([\\da-f]{1,4}:){6}(([\\da-f]{1,4}:[\\da-f]{1,4})|((\\d{1,3}.){3}\\d{1,3}))", address));
	}

	public static Set<Inet6Address> getLocalIPv6Addresses() throws IOException {
		Set<Inet6Address> set = new HashSet<Inet6Address>();
		final Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
		outer: while (interfaces.hasMoreElements()) {
			for (InetAddress address : new Cursor<InetAddress>(interfaces.nextElement().getInetAddresses())) {
				if (address instanceof Inet6Address && !IPUtil.isReservedAddr(address)) {
					set.add((Inet6Address) address);
					break outer;
				}
			}
		}
		return set;
	}
	
	private IP6Util() {
	}
}
