package sep.util.io.stream;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.util.Arrays;

public final class CharStreamUtil {
	private static byte[] BOMHeader = { (byte) 0xEF, (byte) 0xBB, (byte) 0xBF };
	
	/**
	 * 判断UTF-8 BOM头
	 */
	public static boolean assertUTF8BOMHeader(final InputStream inputStream) throws IOException {
		final byte[] header = new byte[3];
		if (inputStream.available() == -1 || inputStream.read(header, 0, 3) <= 3) {
			return false;
		}
		return Arrays.equals(header, BOMHeader);
	}

	/**
	 * 移除UTF-8 BOM头
	 */
	public static InputStream removeUTF8BOMHeader(final InputStream inputStream) throws IOException {
		if (assertUTF8BOMHeader(inputStream)) {
			return inputStream;
		} else {
			final PushbackInputStream stream = new PushbackInputStream(inputStream, 3);
			stream.unread(BOMHeader);
			return stream;
		}
	}

	private CharStreamUtil() {
	}
}
