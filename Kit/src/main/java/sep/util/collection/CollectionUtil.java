package sep.util.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public final class CollectionUtil {
	public static <S extends T, T> void copy(final S[] source, final int sourcePos, final T[] target, final int targetPos, final int length) {
		System.arraycopy(source, sourcePos, target, targetPos, length);
	}
	
	public static <E> Set<E> diff(final Collection<? extends E> source1, final Collection<? extends E> source2) {
		final Set<? extends E> set1 = new HashSet<E>(source1);
		final Set<? extends E> set2 = new HashSet<E>(source2);
		if (set1.retainAll(set2) && set2.retainAll(set1)) {
			final Set<E> set = new HashSet<>();
			set.addAll(set1);
			set.addAll(set2);
			return Collections.unmodifiableSet(set);
		} else {
			return Collections.unmodifiableSet(new HashSet<E>());
		}
	}
	
	@SafeVarargs
	public static <E> List<E> merge(final Collection<? extends E>... collections) {
		if (collections == null || collections.length == 0) {
			return new ArrayList<E>();
		} else if (collections.length == 1) {
			return new ArrayList<E>(collections[0]);
		}
		List<E> newList = new ArrayList<E>();
		for (Collection<? extends E> collection : removeNull(collections)) {
			newList.addAll(collection);
		}
		return newList;
	}
	
	@SuppressWarnings("unchecked")
	public static <E> E[] merge(final E[][] arrays) {
		if (arrays == null || arrays.length == 0) {
			return (E[]) new Object[0];
		} else if (arrays.length == 1) {
			return arrays[0];
		}
		List<E> newList = new ArrayList<E>();
		for (E[] collection : removeNull(arrays)) {
			newList.addAll(CollectionUtil.<E>toList(collection));
		}
		return toArray(newList);
	}
	
	@SuppressWarnings("unchecked")
	public static <E> E[] removeNull(final E[] array) {
		final List<E> newList = new ArrayList<E>();
		if (array == null || array.length != 0) {
			return (E[]) new Object[0];
		}
		
		for (final E object : array) {
			if (object != null) {
				newList.add(object);
			}
		}
		return newList.toArray(array);
	}
	
	public static <E> List<E> removeNull(final Iterable<? extends E> iterable) {
		final List<E> newList = new ArrayList<E>();
		if (iterable == null) {
			return newList;
		}
		
		for (final E object : iterable) {
			if (object != null) {
				newList.add(object);
			}
		}
		return newList;
	}
	
	public static <E> List<E>[] removeNull(final Iterable<? extends E>[] iterables) {
		final List<List<E>> newList = new ArrayList<List<E>>();
		if (iterables == null) {
			return toArray(newList);
		}
		for (final Iterable<? extends E> collection : iterables) {
			if (collection != null) {
				newList.add(removeNull(collection));
			}
		}
		return toArray(newList);
	}
	
	public static <E> List<E> removeNull(final Iterator<? extends E> iterator) {
		return removeNull(Fetch.of(iterator));
	}

	public static <E> E[] reverse(final E[] array) {
		final E[] result = array.clone();
		for (int i = 0, len = array.length, base = len - 1; i < len; i++) {
			result[i] = array[base - i];
		}
		return result;
	}

	public static <E> E[] toArray(final Collection<? extends E> collection) {
		E[] array = null;
		return collection.toArray(array);
	}
	
	public static <E> E[] toArray(final Iterable<? extends E> iterable) {
		return toArray(toList(iterable));
	}
	
	public static <E> E[] toArray(final Iterator<? extends E> iterator) {
		return toArray(toList(iterator));
	}
	
	public static <E> E[] toArray(final Object... array) {
		return CollectionUtil.<E> toArray(toList(array));
	}
	
	public static <E> List<E> toList(final Collection<? extends E> collection) {
		return new ArrayList<E>(collection);
	}
	
	public static <E> List<E> toList(final Iterable<? extends E> iterable) {
		return removeNull(iterable);
	}
	
	public static <E> List<E> toList(final Iterator<? extends E> iterator) {
		return removeNull(Fetch.of(iterator));
	}
	
	@SuppressWarnings("unchecked")
	public static <E> List<E> toList(final Object... array) {
		final Object[] objects = removeNull(array);
		List<E> list = new ArrayList<E>(objects.length);
		for (Object element : objects) {
			list.add((E) element);
		}
		return list;
	}
	
	private CollectionUtil() {
	}
}
