package sep.framework.text.similarity;

import java.util.Comparator;

public interface StringSimilarity extends Comparator<CharSequence> {
	/**
	 * 计算相似度
	 */
	double similarity(CharSequence o1, CharSequence o2);
}
