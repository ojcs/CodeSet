package sep.framework.text.similarity;

import java.util.HashMap;
import java.util.Map;

/** 余弦定理 */
final class StringCosineComparator implements StringSimilarity {
	private int sqdoc1 = 0;
	private int sqdoc2 = 0;
	
	private void handler(Map<Character, int[]> map, CharSequence string, int[] initFlag, int flag) {
		char ch;
		for (int i = 0, len = string.length(); i < len; i++) {
			if (map.containsKey(ch = string.charAt(i))) {
				map.get(ch)[flag]++;
			} else {
				map.put(ch, initFlag);
			}
		}
	}
	
	public int compare(final CharSequence o1, final CharSequence o2) {
		Map<Character, int[]> map = new HashMap<>();
		handler(map, o1, new int[] { 1, 0 }, 0);
		handler(map, o2, new int[] { 0, 1 }, 1);
		int denominator = 0; 
		for (int[] flag : map.values()) {
			denominator += flag[0] * flag[1];
			sqdoc1 += flag[0] * flag[0];
			sqdoc2 += flag[1] * flag[1];
		}
		return denominator;
	}

	public double similarity(final CharSequence o1, final CharSequence o2) {
		return 1 - (double) compare(o1, o2) / Math.sqrt(sqdoc1 * sqdoc2);
	}
}
