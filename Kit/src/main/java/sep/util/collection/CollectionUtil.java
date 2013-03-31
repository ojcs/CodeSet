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
	
	public static double max(double[] values) {
		double result = values[0];
		for (double value : values) {
			if (value > result) {
				result = value;
			}
		}
		return result;
	}
	
	public static float max(float[] values) {
		float result = values[0];
		for (float value : values) {
			if (value > result) {
				result = value;
			}
		}
		return result;
	}
	
	public static int max(int[] values) {
		int result = values[0];
		for (int value : values) {
			if (value > result) {
				result = value;
			}
		}
		return result;
	}
	
	public static long max(long[] values) {
		long result = values[0];
		for (long value : values) {
			if (value > result) {
				result = value;
			}
		}
		return result;
	}
	
	public static short max(short[] values) {
		short result = values[0];
		for (short value : values) {
			if (value > result) {
				result = value;
			}
		}
		return result;
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

	public static double min(double[] values) {
		double result = values[0];
		for (double value : values) {
			if (value < result) {
				result = value;
			}
		}
		return result;
	}

	public static float min(float[] values) {
		float result = values[0];
		for (float value : values) {
			if (value < result) {
				result = value;
			}
		}
		return result;
	}
	
	public static int min(int[] values) {
		int result = values[0];
		for (int value : values) {
			if (value < result) {
				result = value;
			}
		}
		return result;
	}
	
	public static long min(long[] values) {
		long result = values[0];
		for (long value : values) {
			if (value < result) {
				result = value;
			}
		}
		return result;
	}
	
	public static short min(short[] values) {
		short result = values[0];
		for (short value : values) {
			if (value < result) {
				result = value;
			}
		}
		return result;
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
	
	public static <E> List<E> removeRepeat(final List<E> list) {
		List<E> newList = new ArrayList<>();
		for (E element : list) {
			if (!list.contains(element)) {
				newList.add(element);
			}
		}
		return newList;
	}
	
	public static <E> E[] reverse(final E[] array) {
		final E[] result = array.clone();
		for (int i = 0, len = array.length, base = len - 1; i < len; i++) {
			result[i] = array[base - i];
		}
		return result;
	}
	
	public static void swap(boolean[] array, int i, int j) {
		boolean temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
	
	public static void swap(char[] array, int i, int j) {
		char temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
	
	public static void swap(double[] array, int i, int j) {
		double temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
	
	public static <E> void swap(E[] array, int i, int j) {
		final E temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
	
	public static void swap(float[] array, int i, int j) {
		float temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
	
	public static void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
	
	public static void swap(short[] array, int i, int j) {
		short temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
	
	@SuppressWarnings("unchecked")
	public static <E> E[] toArray(final Collection<? extends E> collection) {
		return collection.toArray((E[]) new Object[collection.size()]);
	}
	
	public static <E> E[] toArray(final Iterable<? extends E> iterable) {
		return toArray(toList(iterable));
	}
	
	public static <E> E[] toArray(final Iterator<? extends E> iterator) {
		return toArray(toList(iterator));
	}
	
	@SuppressWarnings("unchecked")
	public static <E> E[] toArray(final Object... array) {
		return CollectionUtil.<E> toArray((Collection<E>) toList(array));
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
