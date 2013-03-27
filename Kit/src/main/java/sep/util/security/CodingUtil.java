package sep.util.security;

import javax.xml.bind.DatatypeConverter;

public final class CodingUtil {
	/**
	 * Base64 解码
	 */
	public static byte[] base64Decode(final String data) {
		return DatatypeConverter.parseBase64Binary(data);
	}
	
	/**
	 * Base64 编码
	 */
	public static String base64Encode(final byte[] data) {
		return DatatypeConverter.printBase64Binary(data);
	}

	private CodingUtil() {
	}
}
