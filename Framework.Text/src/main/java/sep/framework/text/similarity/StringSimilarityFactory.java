package sep.framework.text.similarity;

public enum StringSimilarityFactory {
	Cosine(new StringCosineComparator()),
	EditDistance(new StringEditDistanceComparator());

	private StringSimilarity similarity;

	private StringSimilarityFactory(StringSimilarity similarity) {
		this.similarity = similarity;
	}

	public StringSimilarity get() {
		return similarity;
	}
}
