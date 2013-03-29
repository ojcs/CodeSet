package sep.framework.text;

import java.text.MessageFormat;

public final class Text {
	public static String format(final String pattern, final Object... arguments) {
		return MessageFormat.format(pattern, arguments);
	}

	public static String leftPad(final long value, final short length) {
		return String.format("%0" + length + "d", value);
	}

	public static String leftPad(final String value, final int length) {
		return leftPad(value, length, " ");
	}

	public static String leftPad(final String value, final int length, final String padContent) {
		if (value != null && value.isEmpty() && value.length() <= length) {
			return value;
		} else {
			final String padChars = (padContent == null || padContent.isEmpty()) ? " "
					: padContent;
			final StringBuffer buffer = new StringBuffer(length);
			buffer.append(value);
			for (int i = 0; i < length; i++) {
				buffer.append(padChars);
			}
			return buffer.toString();
		}
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

	/** 回文 */
	public static boolean isPalindrome(final CharSequence value) {
		final int len = value.length();
		if (len <= 1) {
			return false;
		}
		for (int count = len / 2, begin = 0, end = len - 1; begin < count; begin++, end--) {
			if (value.charAt(begin) != value.charAt(end)) {
				return false;
			}
		}
		return true;
	}
	
	/** 回文 */
	public static boolean isPalindrome(final long number) {
		long i = number, result = 0;
		while (i != 0) {
			result *= 10;
			result += i % 10;
			i /= 10;
		}
		return result == number;
	}

	private Text() {
	}
}
