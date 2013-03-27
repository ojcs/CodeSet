package sep.framework.text.match;

public final class StringMatcherFactory {
	public static StringMatcher getBoyerMoore(final CharSequence pattern) {
		return new BoyerMoore(pattern);
	}
	
	public static StringMatcher getKMP(final CharSequence pattern) {
		return new KnuthMorrisPratt(pattern);
	}
	
	public static StringMatcher getRabinKarp(final CharSequence pattern) {
		return new RabinKarp(pattern);
	}
	
	private StringMatcherFactory() {
	}
}
