package sep.framework.text.similarity;

import sep.util.collection.ArrayUtil;

/** 编辑距离 */
final class StringEditDistanceComparator implements StringSimilarity {
	@Override
	public int compare(final CharSequence o1, final CharSequence o2) {
		final int o1len = o1.length(), o2len = o2.length();
		if (o1len == 0 || o2len == 0) {
			return o1len == 0 ? o2len : o1len;
		}
		
		int[][] matrix = new int[o1len + 1][o2len + 1];
		// init matrix
		for (int i = 0; i < o1len; i++, matrix[i][0] = i);
		for (int i = 0; i < o2len; i++, matrix[0][i] = i);
		
		for (int i = 0, j = 0; i <= o1len; i++) {
			for (; j <= o2len; j++) {
				matrix[i + 1][j + 1] = (int) ArrayUtil.min(
					matrix[i][j] + 1,
					matrix[i][j] + 1,
					matrix[i][j] + ((o1.charAt(i) == o2.charAt(j) ? 0 : 1))
				);
			}
		}
		return matrix[o1len][o2len];
	}
	
	public double similarity(final CharSequence o1, final CharSequence o2) {
		return 1 - (double) compare(o1, o2) / Math.max(o1.length(), o2.length());
	}
}
