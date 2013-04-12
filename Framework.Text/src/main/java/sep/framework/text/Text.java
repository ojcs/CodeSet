package sep.framework.text;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.DatatypeConverter;

public final class Text {
	public static String format(final String pattern, final Object... arguments) {
		return MessageFormat.format(pattern, arguments);
	}
	
	public static <T extends CharSequence> T handlerEmpty(T value, T defaultValue) {
		return (value == null || value.length() == 0) ? defaultValue : value;
	}

	public static int indexOf(CharSequence input, int offset, char value) {
		int i = offset, length = input.length();
		while (++i <= length && input.charAt(i) != value);
		return i;
	}

	/** 回文 */
	public static boolean isPalindrome(final CharSequence value) {
		final int len = value.length();
		if (len <= 1) {
			return false;
		}
		for (int count = len / 2, i = 0, j = len - 1; i < count; i++, j--) {
			if (value.charAt(i) != value.charAt(j)) {
				return false;
			}
		}
		return true;
	}

	/** 回文 */
	public static boolean isPalindrome(final long number) {
		long i = number, s = 0;
		while (i != 0) {
			s *= 10;
			s += i % 10;
			i /= 10;
		}
		return s == number;
	}

	public static String leftPad(final CharSequence value, final int length, final CharSequence padContent) {
		if (value != null && value.length() == 0) {
			return value.toString();
		} else {
			final CharSequence content = handlerEmpty(padContent, "");
			final StringBuffer buffer = new StringBuffer(length * content.length());
			buffer.append(value);
			for (int i = 0; i < length; i++) {
				buffer.append(content);
			}
			return buffer.toString();
		}
	}

	public static String leftPad(final long value, final short length) {
		return String.format("%0" + length + "d", value);
	}
	
	public static String leftPad(final String value, final int length) {
		return leftPad(value, length, " ");
	}

	/**
	 * 将字符串的第一个字母改为大写
	 * 
	 * @param content 要修改的字符串
	 * @return 修改后的字符串
	 */
	public static String lowerFirst(final String content) {
		if (content.length() == 1) {
			return content.toLowerCase();
		}

		final char[] chars = content.toCharArray();
		chars[0] = Character.toLowerCase(chars[0]);
		return String.valueOf(chars);
	}
	
	public static String rightPad(final String value, final int length) {
		return rightPad(value, length, " ");
	}
	
	public static String rightPad(final String value, final int length, final String padContent) {
		if (value != null && value.isEmpty() && value.length() <= length) {
			return value;
		} else {
			final String padChars = (padContent == null || padContent.isEmpty()) ? " " : padContent;
			final StringBuffer buffer = new StringBuffer(length);
			buffer.append(value);
			for (int i = 0; i < length; i++) {
				buffer.append(padChars);
			}
			buffer.append(value);
			return buffer.toString();
		}
	}
	
	public static String[] split(final String value, final int offset, final char seperator) {
		List<String> list = new ArrayList<>();
		for (int i = offset, len = value.length(), flag = i; i < len; i++) {
			if (value.charAt(i) == seperator) {
				list.add(value.substring(flag, i));
				flag = i;
			}
		}
		return list.toArray(new String[list.size()]);
	}
	
	public static String[] split(final String value, final int offset, final CharSequence seperators) {
		int sepLen = seperators.length();
		char seperator0 = seperators.charAt(0);
		List<String> list = new ArrayList<>();
		for (int i = offset, len = value.length(), flag = i; i < len; i++) {
			if (value.charAt(i) == seperator0 && value.substring(i + 1, sepLen + 1).equals(seperators)) {
				list.add(value.substring(flag, i));
				flag = i;
			}
		}
		return list.toArray(new String[list.size()]);
	}

	public static String toBinaryString(final int value) {
		return Integer.toBinaryString(value);
	}
	
	public static String toBinaryString(final long value) {
		return Long.toBinaryString(value);
	}
	
	public static String toHexString(final byte... values) {
		return DatatypeConverter.printHexBinary(values);
	}
	
	public static String toHexString(final double value) {
		return Double.toHexString(value);
	}
	
	public static String toHexString(final float value) {
		return Float.toHexString(value);
	}
	
	public static String toHexString(final int value) {
		return Integer.toHexString(value);
	}
	
	public static String toHexString(final long value) {
		return Long.toHexString(value);
	}
	
	public static String toOctalString(final int value) {
		return Integer.toOctalString(value);
	}
	
	public static String toOctalString(final long value) {
		return Long.toOctalString(value);
	}
	
	public static String toString(final BigDecimal value) {
		return value.toString();
	}
	
	public static String toString(final BigInteger value) {
		return value.toString();
	}
	
	public static String toString(final BigInteger value, final int radix) {
		return value.toString(radix);
	}
	
	public static String toString(final char... value) {
		return String.valueOf(value);
	}
	
	public static String toString(final double value) {
		return Double.toString(value);
	}
	
	public static String toString(final float value) {
		return Float.toString(value);
	}
	
	public static String toString(final int value) {
		return Integer.toString(value);
	}
	
	public static String toString(final int value, final int radix) {
		return Integer.toString(value, radix);
	}
	
	public static String toString(final long value) {
		return Long.toString(value);
	}
	
	public static String toString(final long value, final int radix) {
		return Long.toString(value, radix);
	}
	
	public static String toString(final short value) {
		return Short.toString(value);
	}
	
	private Text() {
	}
}
