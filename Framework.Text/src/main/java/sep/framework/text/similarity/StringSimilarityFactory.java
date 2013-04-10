package sep.framework.text.similarity;

public enum StringSimilarityFactory {
	/** 二元语法 */
	Bigram(new StringBigramComparator()),
	/** 余弦定理 */
	Cosine(new StringCosineComparator()),
	/** 编辑距离 */
	EditDistance(new StringEditDistanceComparator());

	private final StringSimilarity similarity;

	private StringSimilarityFactory(StringSimilarity similarity) {
		this.similarity = similarity;
	}

	public StringSimilarity get() {
		return similarity;
	}
}
