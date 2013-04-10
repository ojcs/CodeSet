package sep.framework.text.similarity;

import java.util.HashMap;
import java.util.Map;

/** 余弦定理 */
final class StringCosineComparator implements StringSimilarity {
	private int sqdoc1 = 0;
	private int sqdoc2 = 0;
	
	public int compare(final CharSequence o1, final CharSequence o2) {
		Map<Character, int[]> map = new HashMap<Character, int[]>();
		
		for (int i = 0; i < o1.length(); i++) {
			final char ch = o1.charAt(i);
			
			int[] flag = map.get(ch);
			if (flag != null && flag.length == 2) {
				flag[0]++;
			} else {
				map.put(ch, new int[] { 1, 0 });
			}
		}

		for (int i = 0; i < o2.length(); i++) {
			final char ch = o2.charAt(i);
			
			int[] flag = map.get(ch);
			if(flag != null && flag.length == 2){
				flag[1]++;
			} else {
				map.put(ch, new int[] { 0, 1 });
			}
		}
		
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
