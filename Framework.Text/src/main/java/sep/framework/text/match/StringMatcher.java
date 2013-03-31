package sep.framework.text.match;

public abstract class StringMatcher {
	protected final CharSequence pattern;
	protected final int patternLength;
	protected final int radix;
	
	StringMatcher(final CharSequence pattern) {
		this(pattern, 256);
	}
	
	StringMatcher(final CharSequence pattern, final int radix) {
		this.pattern = pattern;
		this.patternLength = pattern.length();
		this.radix = radix;
		init();
	}
	
	protected abstract void init();
	
	@Override
	public abstract StringMatcher clone();

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StringMatcher other = (StringMatcher) obj;
		if (pattern == null) {
			if (other.pattern != null)
				return false;
		} else if (!pattern.equals(other.pattern))
			return false;
		if (patternLength != other.patternLength)
			return false;
		if (radix != other.radix)
			return false;
		return true;
	}
	
	public final CharSequence getPattern() {
		return pattern;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + pattern.hashCode();
		result = prime * result + patternLength;
		result = prime * result + radix;
		return result;
	}

	public abstract int search(CharSequence targetContent);
	
	@Override
	public final String toString() {
		return String.valueOf(getPattern());
	}
}
