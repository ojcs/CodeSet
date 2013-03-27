package sep.framework.text.match;

final class KnuthMorrisPratt extends StringMatcher implements Cloneable {
	private int[][] dfa; // the KMP automoton

	public KnuthMorrisPratt(final CharSequence pattern) {
		super(pattern);
	}
	
	public KnuthMorrisPratt(final CharSequence pattern, final int radix) {
		super(pattern, radix);
	}

	@Override
	protected void buildPattern() {
		dfa = new int[radix][patternLength];
		dfa[pattern.charAt(0)][0] = 1;
		for (int X = 0, j = 1; j < patternLength; j++) {
			for (int c = 0; c < radix; c++) {
				dfa[c][j] = dfa[c][X]; // Copy mismatch cases.
			}
			dfa[pattern.charAt(j)][j] = j + 1; // Set match case.
			X = dfa[pattern.charAt(j)][X]; // Update restart state.
		}
	}

	@Override
	public int search(final CharSequence targetContent) {
		final int len = targetContent.length();
		int c, p;
		for (c = 0, p = 0; c < len && p < patternLength; c++) {
			p = dfa[targetContent.charAt(c)][p];
		}
		return (p == patternLength) ? c - patternLength : len;
	}
	
	@Override
	public KnuthMorrisPratt clone() {
		return new KnuthMorrisPratt(pattern, radix);
	}
}