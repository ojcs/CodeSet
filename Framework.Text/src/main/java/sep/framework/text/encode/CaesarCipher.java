package sep.framework.text.encode;

public class CaesarCipher {
	public static CharSequence avocat(CharSequence value) {
		return cipher(value, (short) 10);
	}
	
	protected static CharSequence cipher(CharSequence value, short offset) {
		final char[] c = new char[value.length()];
		for (int i = 0; i < c.length; i++) {
			c[i] = value.charAt(i);
			if ((c[i] >= 'a' && c[i] <= 'm') || (c[i] >= 'A' && c[i] <= 'M')) {
				c[i] += offset;
			} else if ((c[i] >= 'n' && c[i] <= 'z') || (c[i] >= 'N' && c[i] <= 'Z')) {
				c[i] -= offset;
			}
		}
		return String.valueOf(c);
	}
	
	public static CharSequence cassette(CharSequence value) {
		return cipher(value, (short) -6);
	}
	
	public static CharSequence cassis(CharSequence value) {
		return cipher(value, (short) -5);
	}
	
	public static CharSequence rot13(CharSequence value) {
		return cipher(value, (short) 13);
	}
	
	public static CharSequence rot47(CharSequence value) {
		final char[] c = new char[value.length()];
		for (int i = 0; i < c.length; i++) {
			c[i] = value.charAt(i);
			c[i] = (c[i] >= '!' && c[i] <= '~') ? (char) ('!' + ((c[i] + 14) % '^')) : c[i];
		}
		return String.valueOf(c);
	}
	
	private CaesarCipher() {
	}
}
