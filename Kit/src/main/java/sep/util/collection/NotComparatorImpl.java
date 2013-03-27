package sep.util.collection;

import java.util.Comparator;

/**
 * 不比较比较器实现
 */
public class NotComparatorImpl<T> implements Comparator<T> {
	private static final Comparator<Object> comparator = new NotComparatorImpl<Object>();
	
	private NotComparatorImpl() {
	}
	
	@Override
	public int compare(T o1, T o2) {
		return 0;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> Comparator<T> get() {
		return (Comparator<T>) comparator;
	}
}
