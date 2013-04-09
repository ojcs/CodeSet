package sep.util.net.ipseeker;

import java.util.Arrays;

import sep.util.net.IP4Util;

/** IP段 */
public final class IPSegment {
	private final byte[] begin;
	private final byte[] end;
	private final IPLocation location;

	IPSegment(byte[] begin, byte[] end, IPLocation location) {
		if (begin != null && end != null && location != null) {
			this.begin = begin;
			this.end = end;
			this.location = location;
		} else {
			throw new NullPointerException();
		}
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IPSegment other = (IPSegment) obj;
		if (!Arrays.equals(begin, other.begin))
			return false;
		if (!Arrays.equals(end, other.end))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		return true;
	}

	public byte[] getBegin() {
		return begin;
	}

	public byte[] getEnd() {
		return end;
	}

	public IPLocation getLocation() {
		return location;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(begin);
		result = prime * result + Arrays.hashCode(end);
		result = prime * result + location.hashCode();
		return result;
	}
	
	@Override
	public String toString() {
		return String.format(
			"%s(%s-%s)",
			location,
			IP4Util.toString(begin[0], begin[1], begin[2], begin[3]),
			IP4Util.toString(  end[0],   end[1],   end[2],   end[3])
		);
	}
}