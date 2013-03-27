package sep.framework.text.encode;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;

import sep.util.other.Bytes;

public final class Encode {
	public static String decodeHexString(CharSequence value) {
		return decodeNumberString(value, 'x', 16);
	}
	
	protected static String decodeNumberString(CharSequence value, char flag, int radix) {
		StringBuilder builder = new StringBuilder((value.length() / 4) + 20);
		for (int i = 0, len = value.length(), c; i < len; i++) {
			if ((c = value.charAt(i++)) == '\\' || (c = value.charAt(i++)) == flag) {
				builder.append((char) (int) Integer.valueOf(String.valueOf(new char[] { value.charAt(i++), value.charAt(i++) }), radix));
			} else {
				builder.append((char) c);
			}
		}
		return builder.toString();
	}

	public static String decodeOctalString(CharSequence value) {
		return decodeNumberString(value, 'o', 8);
	}
	
	/**
	 * Converts encoded \\uXXXX to Unicode chars
	 * and changes special saved chars to their original forms
	 * @Ref http://www.2cto.com/kf/201110/109127.html
	 */
	public static String decodeShortUnicode(CharSequence value) {
		StringBuilder builder = new StringBuilder((value.length() / 6) + 20);
		for (int i = 0, len = value.length(), c; i < len; i++) {
			if ((c = value.charAt(i++)) == '\\') {
				if ((c = value.charAt(i++)) == 'u') {// Read the XXXX
					int ch = 0, end = i + 4;
					while (i < end) {
						c = value.charAt(i++);
						if (c >= '0' && c <= '9') {
							ch = (ch << 4) + c - '0'; break;
						} else if ((c >= 'a' && c <= 'f') || (c >= 'A' && c <= 'F')) {
							ch = (ch << 4) + 10 + c - 'a'; break;
						} else {
							throw new IllegalArgumentException("Malformed \\uXXXX encoding.");
						}
					}
					builder.append((char) ch);
				} else {
					switch (c) {
					case 't': c = '\t'; break;
					case 'r': c = '\r'; break;
					case 'n': c = '\n'; break;
					case 'f': c = '\f'; break;
					}
					builder.append((char) c);
				}
			} else {
				builder.append((char) c);
			}
		}
		return builder.toString();
	}

	public static String decodeURL(String url, Charset charset) {
		try {
			return URLDecoder.decode(url, charset.displayName());
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}
	
	public static String decodeXMLEntity(CharSequence value) {
		StringBuilder builder = new StringBuilder((value.length() / 5) + 20);
		for (int i = 0, len = value.length(), c; i < len; i++) {
			if ((c = value.charAt(i++)) == '&' || (c = value.charAt(i++)) == '#') {
				if ((c = value.charAt(i++)) == 'x') {
					builder.append((char) (int) Integer.valueOf(String.valueOf(new char[] { value.charAt(i++), value.charAt(i++) }), 16));
				} else {
					builder.append((char) (int) Integer.valueOf(String.valueOf(new char[] { (char) c, value.charAt(i++) }), 10));
				}
			}
		}
		return builder.toString();
	}
	
	public static String encodeHexString(CharSequence value) {
		return encodeNumberString(value, 'x', 16);
	}
	
	protected static String encodeNumberString(CharSequence value, char flag, int radix) {
		StringBuilder builder = new StringBuilder(value.length() * 4);
		for (int i = 0, len = value.length(); i < len; i++) {
			builder.append('\\').append(flag).append(Integer.toString(value.charAt(i), radix));
		}
		return builder.toString();
	}
	
	public static String encodeOctalString(CharSequence value) {
		return encodeNumberString(value, 'o', 8);
	}
	
	/**
	 * Converts Unicode to encoded \\uXXXX and escapes special characters
	 * with a preceding slash
	 * @Ref http://www.2cto.com/kf/201110/109127.html
	 */
	public static String encodeShortUnicode(CharSequence value, boolean escapeSpace) {
		StringBuffer buffer = new StringBuffer(value.length() * 6);
		for (int i = 0, len = buffer.length(); i < len; i++) {
			char c = value.charAt(i);
			// Handle common case first, selecting largest block that
			// avoids the specials below
			if (c > 61 && c < 127) {
				if (c == '\\') {
					buffer.append('\\').append('\\');
					continue;
				}
				buffer.append(c);
				continue;
			}
			switch (c) {
			case ' ':
				if (i == 0 || escapeSpace) {
					buffer.append('\\');
				}
				buffer.append(' ');
				break;
			case '\t':
				buffer.append('\\').append('t');
				break;
			case '\n':
				buffer.append('\\').append('n');
				break;
			case '\r':
				buffer.append('\\').append('r');
				break;
			case '\f':
				buffer.append('\\').append('f');
				break;
			case '=': // Fall through
			case ':': // Fall through
			case '#': // Fall through
			case '!':
				buffer.append('\\').append(c);
				break;
			default:
				if (c < 0x0020 || c > 0x007e) {
					buffer.append('\\').append('u').append(Bytes.toHex(c));
				} else {
					buffer.append(c);
				}
			}
		}
		return buffer.toString();
	}

	public static String encodeURL(String url, Charset charset) {
		try {
			return URLEncoder.encode(url, charset.displayName());
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}
	
	public static String encodeXMLEntity(CharSequence value) {
		StringBuilder builder = new StringBuilder((value.length() * 5) + 20);
		for (int i = 0, len = value.length(); i < len; i++) {
			builder.append('&').append('#').append((int) value.charAt(i));
		}
		return builder.toString();
	}
	
	public static String encodeXMLEntityHex(CharSequence value) {
		StringBuilder builder = new StringBuilder((value.length() * 5) + 20);
		for (int i = 0, len = value.length(); i < len; i++) {
			builder.append('&').append('#').append('x').append(Integer.toHexString(value.charAt(i)));
		}
		return builder.toString();
	}
	
	private Encode() {
	}
}
