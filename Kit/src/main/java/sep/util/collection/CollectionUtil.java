package sep.util.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public final class CollectionUtil {
	@SuppressWarnings("unchecked")
	public static <E> E[] asArray(final Collection<? extends E> collection) {
		return collection.toArray((E[]) new Object[collection.size()]);
	}
	
	@SuppressWarnings("unchecked")
	public static <E> E[] asArray(final Object... array) {
		final E[] elements = (E[]) new Object[array.length];
		for (int i = 0; i < array.length; i++) {
			elements[i] = (E) array[i];
		}
		return elements;
	}
	
	public static <E> List<E> asList(@SuppressWarnings("unchecked") final E... array) {
		List<E> list = new ArrayList<>(array.length);
		for (E element : array) {
			list.add(element);
		}
		return list;
	}
	
	public static <E> List<E> asList(final Iterator<? extends E> iterator) {
		List<E> list = new ArrayList<>();
		while (iterator.hasNext()) {
			list.add(iterator.next());
		}
		return list;
	}

	public static <S extends T, T> void copy(final S[] source, final int sourcePos, final T[] target, final int targetPos, final int length) {
		System.arraycopy(source, sourcePos, target, targetPos, length);
	}
	
	public static void removeAll(final Iterator<?> iterator) {
		while (iterator.hasNext()) {
			iterator.remove();
		}
	}
	
	public static void removeEmpty(final Iterator<? extends CharSequence> iterator) {
		CharSequence cache;
		while (iterator.hasNext()) {
			if ((cache = iterator.next()) == null || cache.length() == 0) {
				iterator.remove();
			}
		}
	}
	
	public static void removeNull(final Iterator<?> iterator) {
		while (iterator.hasNext()) {
			if (iterator.next() == null) {
				iterator.remove();
			}
		}
	}
	
	public static void removeRepeat(final Collection<?> collection) {
		Iterator<?> iterator = collection.iterator();
		while (iterator.hasNext()) {
			if (collection.contains(iterator.next())) {
				iterator.remove();
			}
		}
	}
	
	public static <E> String toString(Iterator<E> iterator) {
		if (!iterator.hasNext()) {
			return "[]";
		}
		StringBuilder builder = new StringBuilder();
		builder.append('[');
		while (iterator.hasNext()) {
			builder.append(iterator.next()).append(',').append(' ');
		}
		return builder.deleteCharAt(builder.length()).append(']').toString();
	}

	private CollectionUtil() {
	}
}
