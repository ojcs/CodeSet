package sep.framework.text;

public final class Wildcard {
	public static boolean matches(final CharSequence pattern, final CharSequence input) {
		final int length = pattern.length();
		char current, next;
		for (int p = 0, i = 0; p < length; p++, i++) {
			switch (current = pattern.charAt(p)) {
			case '?' : continue;
			case '\\': p++; continue;
			case '*':
				if (length == 1 || p == length - 1 || p == length) { continue; }
				next = pattern.charAt(++p);
				i = Chars.indexOf(input, i, next);
				continue;
			default:
				if (input.charAt(i) == current) { continue; }
				return false;
			}
		}
		return true;
	}

	private final CharSequence pattern;
	
	public Wildcard(final CharSequence pattern) {
		this.pattern = pattern;
	}
	
	public final CharSequence getPattern() {
		return pattern;
	}
	
	public boolean matches(final CharSequence input) {
		return matches(pattern, input);
	}
}
