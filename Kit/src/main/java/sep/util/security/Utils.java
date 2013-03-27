package sep.util.security;

public class Utils {
	public static CharSequence rot13(CharSequence value) {
		final char[] chars = new char[value.length()];
		for (int i = 0, len = value.length(); i < len; i++) {
			chars[i] = value.charAt(i);
			if ((chars[i] >= 'a' && chars[i] <= 'm') || (chars[i] >= 'A' && chars[i] <= 'M')) {
				chars[i] += 13;
			} else if ((chars[i] >= 'n' && chars[i] <= 'z') || (chars[i] >= 'N' && chars[i] <= 'Z')) {
				chars[i] -= 13;
			}
		}
		return String.valueOf(chars);
	}
	
	public static CharSequence rot47(CharSequence value) {
		final char[] chars = new char[value.length()];
		for (int i = 0, len = value.length(); i < len; i++) {
			chars[i] = value.charAt(i);
			chars[i] = (char) ((chars[i] >= 33 && chars[i] <= 126) ? 33 + ((chars[i] + 14) % 94) : chars[i]);
		}
		return String.valueOf(chars);
	}
}
