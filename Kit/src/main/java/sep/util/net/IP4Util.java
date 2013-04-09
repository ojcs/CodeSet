package sep.util.net;

import java.net.Inet4Address;
import java.net.UnknownHostException;

public final class IP4Util {
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
	
	public static boolean endIsBigger(int beginIP, int endIP) {
		return beginIP <= endIP;
	}
	
	public static Inet4Address getBroadcastAddress(Inet4Address subnetMask, Inet4Address address) throws UnknownHostException {
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

	/** 检查address1,address2是不是在subnet里面 */
	public static boolean isRange(final int subnet, final int address1, final int adress2) {
		return (address1 & subnet) == (adress2 & subnet);
	}
	
	/**
	 * 检查IP是否属于某个IP段
	 * @Ref http://www.oschina.net/code/snippet_586186_19315
	 */
	public static boolean isSegment(int address, int begin, int end) {
		return endIsBigger(begin, address) && endIsBigger(address, end);
	}

	public static byte[] toBytes(int address) {
		return new byte[] {
			(byte) ((address >>> 24) & 0xFF),
			(byte) ((address >>> 16) & 0xFF),
			(byte) ((address >>>  8) & 0xFF),
			(byte) ((address       ) & 0xFF)
		};
	}
	
	public static int toNumber(byte ip1, byte ip2, byte ip3, byte ip4) {
		return 
			((ip4      ) & 0xFF      ) |
			((ip3 <<  8) & 0xFF00    ) |
			((ip2 << 16) & 0xFF0000  ) |
			((ip1 << 24) & 0xFF000000);
	}

	public static String toString(byte ip1, byte ip2, byte ip3, byte ip4) {
		return String.format("%s.%s.%s.%s", ip1 & 0xFF, ip2 & 0xFF, ip3 & 0xFF, ip4 & 0xFF);
	}
	
	public static String toString(int address) {
		return String.format("%s.%s.%s.%s",
			(address             ) >>> 24,
			(address & 0x00FFFFFF) >>> 16,
			(address & 0x0000FFFF) >>>  8,
			(address & 0x000000FF)
		);
	}
}
