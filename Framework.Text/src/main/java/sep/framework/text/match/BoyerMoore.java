package sep.framework.text.match;

final class BoyerMoore extends StringMatcher implements Cloneable {
	private final int[] right = new int[radix]; // the bad-character skip array

	public BoyerMoore(final CharSequence pattern) {
		super(pattern);
	}
	
	public BoyerMoore(final CharSequence pattern, final int radix) {
		super(pattern, radix);
	}

	@Override
	protected void buildPattern() {
		// position of rightmost occurrence of c in the pattern
		for (int i = 0; i < radix; i++) {
			right[i] = -1;
		}
		for (int i = 0; i < patternLength; i++) {
			right[pattern.charAt(i)] = i;
		}
	}

	@Override
	public int search(final CharSequence targetContent) {
		final int contentLength = targetContent.length();
		int skip;
		for (int i = 0; i <= contentLength - patternLength; i += skip) {
			skip = 0;
			for (int j = patternLength - 1; j >= 0; j--) {
				if (pattern.charAt(j) != pattern.charAt(i + j)) {
					skip = Math.max(1, j - right[pattern.charAt(i + j)]);
					break;
				}
			}
			if (skip == 0) {
				return i; // found
			}
		}
		return contentLength; // not found
	}
	
	@Override
	public BoyerMoore clone() {
		return new BoyerMoore(pattern, radix);
	}
}
