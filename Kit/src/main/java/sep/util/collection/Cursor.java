package sep.util.collection;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

public final class Cursor<E> implements Iterable<E>, Iterator<E>, Enumeration<E> {
	public final static class EnumerationIterator<E> implements Iterator<E> {
		private final Enumeration<E> enumeration;

		public EnumerationIterator(final Enumeration<E> enumeration) {
			this.enumeration = enumeration;
		}

		@Override
		public boolean hasNext() {
			return enumeration.hasMoreElements();
		}

		@Override
		public E next() {
			return enumeration.nextElement();
		}

		@Deprecated
		@Override
		public void remove() {
			throw new UnsupportedOperationException("The the Iterator<E> agent Enumeration<E>");
		}
	}

	protected final Iterator<E> iterator;

	public Cursor(final Cursor<E> iterable) {
		this(iterable.iterator());
	}

	public Cursor(final Enumeration<E> enumeration) {
		this(new EnumerationIterator<>(enumeration));
	}

	public Cursor(final Iterable<E> iterable) {
		this(iterable.iterator());
	}

	public Cursor(final Iterator<E> iterator) {
		this.iterator = iterator;
	}

	@Override
	public int hashCode() {
		return iterator.hashCode();
	}

	@Override
	public boolean hasMoreElements() {
		return iterator.hasNext();
	}

	@Override
	public boolean hasNext() {
		return iterator.hasNext();
	}

	@Override
	public Iterator<E> iterator() {
		return iterator;
	}

	public ListIterator<E> listIterator() {
		if (iterator instanceof ListIterator) {
			return (ListIterator<E>) iterator;
		} else {
			throw new UnsupportedOperationException(
					"This Iterator<E> not is ListIterator<E> Type");
		}
	}

	@Override
	public E next() {
		return iterator.next();
	}

	@Override
	public E nextElement() {
		return iterator.next();
	}

	@Override
	public void remove() {
		iterator.remove();
	}

	public E[] toArray() {
		return CollectionUtil.asArray(toList());
	}

	public List<E> toList() {
		final List<E> list = new ArrayList<>();
		for (E element : this) {
			list.add(element);
		}
		return list;
	}

	public Set<E> toSet() {
		final Set<E> set = new HashSet<>();
		for (E element : this) {
			set.add(element);
		}
		return set;
	}

	@Override
	public String toString() {
		Iterator<E> iterator = iterator();
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
}
