package sep.util.net;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.nio.ByteOrder;

import sep.util.other.Convert;

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
		boolean big = order.equals(ByteOrder.BIG_ENDIAN);
		byte[] buffer = new byte[4];
		for (int i = 0, offset = big ? 0 : 1; i < 4; i++, offset += big ? 8 : -8) {
			buffer[i] = (byte) ((ip >> offset) & 0x000000FF);
		}
		this.address = (Inet4Address) Inet4Address.getByAddress(buffer);
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
		return Integer.parseInt(Convert.toHexString(address.getAddress()), 16);
	}
	
	/**
	 * 验证IP是否属于某个IP段
	 * @Ref http://www.oschina.net/code/snippet_586186_19315
	 */
	public boolean ipIsValid(IP4Address begin, IP4Address end) {
		return endIsBigger(begin, this) && endIsBigger(this, end);
	}

	public boolean endIsBigger(IP4Address beginIP, IP4Address endIP) {
		return beginIP.toIPNumber() <= endIP.toIPNumber();
	}
}
