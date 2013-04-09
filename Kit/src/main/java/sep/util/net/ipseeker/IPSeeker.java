package sep.util.net.ipseeker;

import static sep.util.net.ipseeker.Util.RecordLength;
import static sep.util.net.ipseeker.Util.RedirectMode1;
import static sep.util.net.ipseeker.Util.RedirectMode2;
import static sep.util.net.ipseeker.Util.calcMiddleOffset;
import static sep.util.net.ipseeker.Util.compare;
import static sep.util.net.ipseeker.Util.readInt3;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Inet4Address;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

public class IPSeeker {
	private final ByteBuffer buffer;
	private final int ipBegin, ipEnd;

	public IPSeeker(Path path) throws IOException {
		if (Files.exists(path)) {
			buffer = ByteBuffer.wrap(Files.readAllBytes(path));
			buffer.order(ByteOrder.LITTLE_ENDIAN);
			ipBegin = buffer.getInt();
			ipEnd = buffer.getInt();
			if (ipBegin == -1 || ipEnd == -1) {
				throw new IllegalArgumentException("File Format Error");
			}
		} else {
			throw new FileNotFoundException();
		}
	}
	
	public IPLocation getLocation(final byte... address) {
		if (address != null && address.length == 4) {
			return getLocation(locateOffset(address));
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	public IPLocation getLocation(final Inet4Address address) {
		return getLocation(address.getAddress());
	}
	
	private IPLocation getLocation(int offset) {
		if (offset == -1) {
			return IPLocation.Unknown;
		}
		buffer.position(offset + 4);
		switch (buffer.get()) {
		case RedirectMode1:
			// Read CountryOffset & Set Position
			buffer.position(offset = readInt3(buffer));
			final String country;
			switch (buffer.get()) {
			case RedirectMode2:
				country = readString(readInt3(buffer));
				buffer.position(offset + 4);
				break;
			default:
				country = readString(offset);
				break;
			}
			return IPLocation.of(country, readArea(buffer.position()));
		case RedirectMode2:
			return IPLocation.of(readString(readInt3(buffer)), readArea(offset + 8));
		default:
			return IPLocation.of(readString(buffer.position() - 1), readArea(buffer.position()));
		}
	}
	
	// TODO 没有实现完整 读取上存在问题
	public IPSegment[] getSegment(final String keyword) {
		final Set<IPSegment> segments = new HashSet<IPSegment>(10);
		int offset = ipBegin + 4, endOffset = ipEnd + 4;
		for (int end; offset <= endOffset; offset += RecordLength) {
			if ((end = readInt3(buffer, offset)) != -1) {
				IPLocation loc = getLocation(end);
				if (loc != null && loc.getCountry().contains(keyword) && loc.getArea().contains(keyword)) {
					int begin = offset - 4;
					segments.add(new IPSegment(readIP(begin), readIP(end), loc));
				}
			}
		}
		return segments.toArray(new IPSegment[segments.size()]);
	}

	/** 定位IP的绝对偏移 */
	private int locateOffset(final byte[] address) {
		int r = compare(address, readIP(ipBegin));
		if (r == 0) {
			return ipBegin;
		} else if (r < 0) {
			return -1;
		}
		int m = 0;
		for (int i = ipBegin, j = ipEnd; i < j;) {
			m = calcMiddleOffset(i, j);
			r = compare(address, readIP(m));
			if (r > 0) {
				i = m;
			} else if (r < 0) {
				if (m == j) {
					m = (j -= RecordLength);
				} else {
					j = m;
				}
			} else {
				return Util.readInt3(buffer, m + 4);
			}
		}
		r = compare(address, readIP(m = Util.readInt3(buffer, m + 4)));
		if (r <= 0) {
			return m;
		} else {
			return -1;
		}
	}

	private String readArea(final int offset) {
	    buffer.position(offset);
		switch (buffer.get()) {
		case RedirectMode1:
		case RedirectMode2:
			final int off = readInt3(buffer, offset + 1);
			if (off != 0) {
				return readString(off);
			} else {
				return IPLocation.Unknown.getArea();
			}
		default:
			return readString(offset);
		}
	}

	private byte[] readIP(int offset) {
		buffer.position(offset);
		final byte[] ip = { buffer.get(), buffer.get(), buffer.get(), buffer.get() };
		return new byte[] { ip[3], ip[2], ip[1], ip[0] };
	}

	private String readString(int offset) {
		buffer.position(offset);
		final byte[] buf = new byte[1024];
        int i = 0;
        for (buf[i] = buffer.get(); buf[i] != 0; buf[++i] = buffer.get());
		return (i != 0) ? new String(buf, 0, i, Charset.forName("GB2312")) : null;
	}
}