package sep.framework.text.similarity;

/** 二元语法 */
final class StringBigramsComparator implements StringSimilarity {
	private int bigram1Size, bigram2Size;
	
	@Override
	public int compare(CharSequence o1, CharSequence o2) {
		char[][] br1 = bigrams(o1), br2 = bigrams(o2);
		int matches = 0;
		for (int i = br1.length; --i >= 0;) {
			for (int j = br2.length; --j >= 0;) {
				if (br1[i][0] == br2[j][0] && br1[i][1] == br2[j][1]) {
					matches += 2;
					break;
				}
			}
		}
		this.bigram1Size = br1.length;
		this.bigram2Size = br2.length;
	    return matches;
	}
	
	private static char[][] bigrams(final CharSequence input) {
		char[][] chars = new char[input.length()][2];
		for (int i = 0, len = input.length() - 1; i < len; i++) {
			chars[i][0] = input.charAt(i);
			chars[i][1] = input.charAt(i + 1);
		}
		return chars;
	}

	@Override
	public double similarity(CharSequence o1, CharSequence o2) {
		return (double) compare(o1, o2) / (bigram1Size + bigram2Size);
	}
}
