package sep.util.io.stream;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.nio.charset.Charset;
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
	
	/**
	 *  BOMs:
	 *  00 00 FE FF    = UTF-32, big-endian
	 *  FF FE 00 00    = UTF-32, little-endian
	 *  EF BB BF       = UTF-8,
	 *  FE FF          = UTF-16, big-endian
	 *  FF FE          = UTF-16, little-endian
	 *  Win2k NotePad:
	 *  Unicode format = UTF-16LE
	 */
	public static Charset charset(Charset defaultCharset, final byte[] bom) {
		switch (bom[0]) {
		case 000:
			if (bom[1] == (byte) 000 && bom[2] == (byte) 254 && bom[3] == (byte) 255) {
				return Charset.forName("UTF-32BE");
			}
		case (byte) 255:
			if (bom[1] == (byte) 254 && bom[2] == (byte) 254 && bom[3] == (byte) 255) {
				return Charset.forName("UTF-32LE");
			} else if (bom[1] == (byte) 254) {
				return Charset.forName("UTF-16LE");
			}
		case (byte) 239:
			if (bom[1] == (byte) 187 && bom[2] == (byte) 191) {
				return Charset.forName("UTF-8");
			} else if (bom[1] == (byte) 255) {
				return Charset.forName("UTF-16BE");
			}
		default:
			return defaultCharset;
		}
	}

	private CharStreamUtil() {
	}
}
