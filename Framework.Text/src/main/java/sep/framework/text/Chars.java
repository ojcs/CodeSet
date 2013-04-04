package sep.framework.text;

public class Chars implements CharSequence {
	public static int indexOf(CharSequence input, int offset, char value) {
		int i = offset, len = input.length();
		while (++i <= len && input.charAt(i) != value);
		return i;
	}
	
	public static boolean or(char reference, char... values) {
		for (char c : values) {
			if (c == reference) {
				return true;
			}
		}
		return false;
	}
	
	private final char[] chars;

	public Chars(final char... chars) {
		this.chars = chars;
	}

	@Override
	public char charAt(int index) {
		return chars[index];
	}

	@Override
	public Chars clone() {
		return new Chars(chars.clone());
	}

	public int indexOf(int offset, char value) {
		return indexOf(this, offset, value);
	}
	
	@Override
	public int length() {
		return chars.length;
	}
	
	@Override
	public CharSequence subSequence(int start, int end) {
		char[] chars = new char[end - start];
		System.arraycopy(this.chars, start, chars, 0, chars.length);
		return new Chars(chars);
	}
	
	@Override
	public String toString() {
		return String.valueOf(chars);
	}
}
