package sep.framework.text.match;

import java.math.BigInteger;
import java.util.Random;

final class RabinKarp extends StringMatcher implements Cloneable {
	private final long patternHash = hash(pattern); // pattern hash value

	private final long Q = new BigInteger(31, new Random()).longValue(); // a large prime, small enough to avoid long overflow
	private long RM = 1; // R^(M-1) % Q
	
	public RabinKarp(final CharSequence pattern) {
		super(pattern);
	}
	
	public RabinKarp(final CharSequence pattern, final int radix) {
		super(pattern, radix);
	}

	@Override
	protected void buildPattern() {
		// precompute R^(M-1) % Q for use in removing leading digit
		for (int i = 1; i <= patternLength - 1; i++) {
			RM = (radix * RM) % Q;
		}
	}

	/**
	 * Las Vegas version: does pat[] match txt[i..i-M+1] ?
	 */
	protected boolean check(final CharSequence content, final int offset) {
		for (int j = 0; j < patternLength; j++) {
			if (pattern.charAt(j) != content.charAt(offset + j)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Compute hash for key[0..M-1].
	 */
	protected long hash(final CharSequence pattern) {
		long h = 0;
		for (int j = 0; j < patternLength; j++) {
			h = (radix * h + pattern.charAt(j)) % Q;
		}
		return h;
	}

	public int search(final CharSequence targetContent) {
		int N = targetContent.length();
		if (N < patternLength) {
			return N;
		}
		long contentHash = hash(targetContent);

		// check for match at offset 0
		if ((patternHash == contentHash) && check(targetContent, 0)) {
			return 0;
		}
		
		// check for hash match; if hash match, check for exact match
		for (int i = patternLength; i < N; i++) {
			// Remove leading digit, add trailing digit, check for match.
			contentHash = (contentHash + Q - RM * targetContent.charAt(i - patternLength) % Q) % Q;
			contentHash = (contentHash * radix + targetContent.charAt(i)) % Q;

			// match
			int offset = i - patternLength + 1;
			if ((patternHash == contentHash) && check(targetContent, offset)) {
				return offset;
			}
		}
		// no match
		return N;
	}
	
	@Override
	public RabinKarp clone() {
		return new RabinKarp(pattern, radix);
	}
}
