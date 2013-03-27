package sep.util.net;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteOrder;

import sep.util.other.Bytes;

public final class IP4Address {
	public static int computeMaskFromNetworkPrefix(int prefix) {
		int subnet = 0;
		for (int i = 0; i < prefix; i++) {
			subnet = (subnet << 1) + 1;
		}
		return subnet;
	}
	
	public static int computeNetworkPrefixFromMask(int mask) {
		int result = 0;
		int tmp = mask;
		while ((tmp & 0x00000001) == 0x00000001) {
			result++;
			tmp = tmp >>> 1;
		}
		if (tmp != 0) {
			return -1;
		}
		return result;
	}
	
	public static boolean isRange(final int address1, final int adress2, final int subnet) {
		return (address1 & subnet) == (adress2 & subnet);
	}
	
	private final Inet4Address address;
	
	public IP4Address(byte ip1, byte ip2, byte ip3, byte ip4) throws UnknownHostException {
		this((Inet4Address) Inet4Address.getByAddress(new byte[] { ip1, ip2, ip3, ip4 }));
	}
	
	public IP4Address(Inet4Address address) {
		this.address = address;
	}
	
	public IP4Address(int ip, final ByteOrder order) throws UnknownHostException {
		if (order.equals(ByteOrder.BIG_ENDIAN)) {// 大端
			this.address = (Inet4Address) InetAddress.getByName(
				String.format(
					"%s.%s.%s.%s",
					(ip      ) & 0x000000FF,
					(ip >> 8 ) & 0x000000FF,
					(ip >> 16) & 0x000000FF,
					(ip >> 24) & 0x000000FF
				)
			);
		} else if (order.equals(ByteOrder.LITTLE_ENDIAN)) {// 小端
			this.address = (Inet4Address) InetAddress.getByName(
				String.format(
					"%s.%s.%s.%s",
					(ip >> 24) & 0x000000FF,
					(ip >> 16) & 0x000000FF,
					(ip >> 8 ) & 0x000000FF,
					(ip      ) & 0x000000FF
				)
			);
		}
		throw new UnknownHostException();
	}
	
	public Inet4Address getBroadcastAddress(Inet4Address subnetMask) throws UnknownHostException {
		//[Sub net Mask
		final byte[] mask = subnetMask.getAddress();
		for (int i = 0; i < 4; i++) {
			mask[i] = (byte) ~mask[i];
		}
		//]
		
		// Address
		final byte[] addr = address.getAddress();
		
		//[Broadcast
		final byte[] broadcast = new byte[4];
		for (int i = 0; i < 4; i++) {
			broadcast[i] = (byte)(addr[i] | mask[i]);
		}
		//]
		return (Inet4Address) Inet4Address.getByAddress(broadcast);
	}

	public int toIPNumber() {
		return Integer.parseInt(Bytes.toString(address.getAddress()), 16);
	}

}
