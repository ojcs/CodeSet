package sep.util.net.ipseeker;

import static sep.util.net.ipseeker.Helper.RecordLength;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Inet4Address;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

public class IPSeeker {
	final ByteBuffer buffer;
	final Helper h;
	final int offsetBegin, offsetEnd;

	public IPSeeker(Path path) throws IOException {
		if (Files.exists(path)) {
			buffer = ByteBuffer.wrap(Files.readAllBytes(path));
			buffer.order(ByteOrder.LITTLE_ENDIAN);
			offsetBegin = buffer.getInt();
			offsetEnd   = buffer.getInt();
			if (offsetBegin == -1 || offsetEnd == -1) {
				throw new IllegalArgumentException("File Format Error");
			}
			h = new Helper(this);
		} else {
			throw new FileNotFoundException();
		}
	}
	
	public IPLocation getLocation(final byte... address) {
		if (address != null && address.length == 4) {
			return h.getLocation(h.locateOffset(address));
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	public IPLocation getLocation(final Inet4Address address) {
		return getLocation(address.getAddress());
	}
	
	// TODO 没有实现完整 读取上存在问题
	public IPSegment[] getSegment(final String keyword) {
		final Set<IPSegment> segments = new HashSet<IPSegment>(10);
		for (int end, begin, offset = offsetBegin + 4, endOffset = offsetEnd + 4; offset <= endOffset; offset += RecordLength) {
			if ((end = h.readInt3(offset)) != -1) {
				IPLocation loc = h.getLocation(end);
				if (!loc.equals(IPLocation.Unknown) && loc.getCountry().contains(keyword) && loc.getArea().contains(keyword)) {
					begin = offset - 4;
					segments.add(new IPSegment(h.readIP(begin), h.readIP(end), loc));
				}
			}
		}
		return segments.toArray(new IPSegment[segments.size()]);
	}
}