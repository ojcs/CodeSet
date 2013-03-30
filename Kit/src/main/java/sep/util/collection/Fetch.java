package sep.util.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

public final class Fetch<E> implements Iterable<E>, Iterator<E>, Enumeration<E> {
	public static <E> Iterable<E> of(final Enumeration<E> enumeration) {
		return new Fetch<E>(enumeration);
	}
	
	public static <E> Iterable<E> of(final Iterator<E> iterator) {
		return new Fetch<E>(iterator);
	}
	
	protected final Iterator<E> iterator;

	public Fetch(final Enumeration<E> enumeration) {
		this(new Iterator<E>() {
			@Override
			public boolean hasNext() {
				return enumeration.hasMoreElements();
			}

			@Override
			public E next() {
				return enumeration.nextElement();
			}

			@Override
			@Deprecated
			public void remove() {
				throw new UnsupportedOperationException("The the Iterator<E> agent Enumeration<E>");
			}
		});
	}
	
	public Fetch(final Iterable<E> iterable) {
		this(iterable.iterator());
	}
	
	public Fetch(final Iterator<E> iterator) {
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
			throw new UnsupportedOperationException("This Iterator<E> not is ListIterator<E> Type");
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
		return CollectionUtil.toArray(toList());
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
		return Arrays.toString(toArray());
	}
}
