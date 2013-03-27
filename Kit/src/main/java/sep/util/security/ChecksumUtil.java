package sep.util.security;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.Adler32;
import java.util.zip.CRC32;
import java.util.zip.Checksum;

public final class ChecksumUtil {
	public static long adler32(final byte[] data) {
		return checksum(data, new Adler32());
	}
	
	public static long adler32Stream(final InputStream stream) throws IOException {
		return checksumStream(stream, new Adler32());
	}
	
	public static long crc32(final byte[] data) {
		return checksum(data, new CRC32());
	}
	
	public static long crc32Stream(final InputStream stream) throws IOException {
		return checksumStream(stream, new CRC32());
	}
	
	private static long checksum(final byte[] data, final Checksum checksum) {
		checksum.update(data, 0, data.length);
		return checksum.getValue();
	}
	
	private static long checksumStream(final InputStream stream, final Checksum checksum) throws IOException {
		byte[] buffer = new byte[8192];
		int offset = 0;
		try (InputStream input = stream) {
			while ((offset = input.read(buffer)) != -1) {
				checksum.update(buffer, 0, offset);
			}
		}
		return checksum.getValue();
	}
	
	private ChecksumUtil() {
	}
}
