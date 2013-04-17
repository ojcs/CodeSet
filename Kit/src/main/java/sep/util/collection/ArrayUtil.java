package sep.util.collection;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

public final class ArrayUtil {
	public static boolean contains(final byte[] elements, byte value) {
		for (byte element : elements) {
			if (element == value) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean contains(final double[] elements, double value) {
		for (double element : elements) {
			if (element == value) {
				return true;
			}
		}
		return false;
	}

	public static <E> boolean contains(final E[] elements, E object) {
		int hash = object.hashCode();
		for (E element : elements) {
			if (element == object || element.hashCode() == hash || object.equals(element)) {
				return true;
			}
		}
		return false;
	}

	public static boolean contains(final float[] elements, float value) {
		for (float element : elements) {
			if (element == value) {
				return true;
			}
		}
		return false;
	}

	public static boolean contains(final int[] elements, int value) {
		for (int element : elements) {
			if (element == value) {
				return true;
			}
		}
		return false;
	}

	public static boolean contains(final long[] elements, long value) {
		for (long element : elements) {
			if (element == value) {
				return true;
			}
		}
		return false;
	}

	public static boolean contains(final short[] elements, short value) {
		for (short element : elements) {
			if (element == value) {
				return true;
			}
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public static <T> T[] createInstance(final Class<? extends T> type, final int length) {
		return (T[]) Array.newInstance(type, length);
	}

	public static <E> Enumeration<E> enumeration(final E[] objects) {
		return new Enumeration<E>() {
			private int hash = 0;
			private int index = -1;
			private final int length = objects.length;
			
			@SuppressWarnings("unchecked")
			@Override
			public boolean equals(Object obj) {
				if (this == obj)
					return true;
				if (obj == null || getClass() != obj.getClass())
					return false;
				return Arrays.equals(objects, (E[]) obj);
			}

			@Override
			public int hashCode() {
				if (hash == 0) {
					hash = Arrays.hashCode(objects);
				}
				return hash;
			}
			
			@Override
			public boolean hasMoreElements() {
				return index > length;
			}
			
			@Override
			public E nextElement() {
				return objects[++index];
			}
		};
	}
	
	public static int indexOf(final byte[] elements, byte value) {
		for (int i = 0; i < elements.length; i++) {
			if (elements[i] == value) {
				return i;
			}
		}
		return -1;
	}
	
	public static int indexOf(final double[] elements, double value) {
		for (int i = 0; i < elements.length; i++) {
			if (elements[i] == value) {
				return i;
			}
		}
		return -1;
	}
	
	public static <E> int indexOf(final E[] elements, E object) {
		int hash = object.hashCode();
		for (int i = 0; i < elements.length; i++) {
			if (elements[i] == object || elements[i].hashCode() == hash || object.equals(elements[i])) {
				return i;
			}
		}
		return -1;
	}
	
	public static int indexOf(final float[] elements, float value) {
		for (int i = 0; i < elements.length; i++) {
			if (elements[i] == value) {
				return i;
			}
		}
		return -1;
	}
	
	public static int indexOf(final int[] elements, int value) {
		for (int i = 0; i < elements.length; i++) {
			if (elements[i] == value) {
				return i;
			}
		}
		return -1;
	}
	
	public static int indexOf(final long[] elements, long value) {
		for (int i = 0; i < elements.length; i++) {
			if (elements[i] == value) {
				return i;
			}
		}
		return -1;
	}
	
	public static int indexOf(final short[] elements, short value) {
		for (int i = 0; i < elements.length; i++) {
			if (elements[i] == value) {
				return i;
			}
		}
		return -1;
	}
	
	public static int lastIndexOf(final byte[] elements, byte value) {
		for (int i = elements.length; i > 0; i--) {
			if (elements[i] == value) {
				return i;
			}
		}
		return -1;
	}
	
	public static int lastIndexOf(final double[] elements, double value) {
		for (int i = elements.length; i > 0; i--) {
			if (elements[i] == value) {
				return i;
			}
		}
		return -1;
	}
	
	public static <E> int lastIndexOf(final E[] elements, E object) {
		int hash = object.hashCode();
		for (int i = elements.length; i > 0; i--) {
			if (elements[i] == object || elements[i].hashCode() == hash || object.equals(elements[i])) {
				return i;
			}
		}
		return -1;
	}
	
	public static int lastIndexOf(final float[] elements, float value) {
		for (int i = elements.length; i > 0; i--) {
			if (elements[i] == value) {
				return i;
			}
		}
		return -1;
	}
	
	public static int lastIndexOf(final int[] elements, int value) {
		for (int i = elements.length; i > 0; i--) {
			if (elements[i] == value) {
				return i;
			}
		}
		return -1;
	}
	
	public static int lastIndexOf(final long[] elements, long value) {
		for (int i = elements.length; i > 0; i--) {
			if (elements[i] == value) {
				return i;
			}
		}
		return -1;
	}
	
	public static int lastIndexOf(final short[] elements, short value) {
		for (int i = elements.length; i > 0; i--) {
			if (elements[i] == value) {
				return i;
			}
		}
		return -1;
	}

	public static byte max(final byte... elements) {
		byte max = elements[0];
		for (int i = 1; i < elements.length; i++) {
			if (max < elements[i]) {
				max = elements[i];
			}
		}
		return max;
	}

	public static double max(final double... elements) {
		double max = elements[0];
		for (int i = 1; i < elements.length; i++) {
			if (max < elements[i]) {
				max = elements[i];
			}
		}
		return max;
	}

	public static float max(final float... elements) {
		float max = elements[0];
		for (int i = 1; i < elements.length; i++) {
			if (max < elements[i]) {
				max = elements[i];
			}
		}
		return max;
	}

	public static int max(final int... elements) {
		int max = elements[0];
		for (int i = 1; i < elements.length; i++) {
			if (max < elements[i]) {
				max = elements[i];
			}
		}
		return max;
	}

	public static long max(final long... elements) {
		long max = elements[0];
		for (int i = 1; i < elements.length; i++) {
			if (max < elements[i]) {
				max = elements[i];
			}
		}
		return max;
	}

	public static short max(final short... elements) {
		short max = elements[0];
		for (int i = 1; i < elements.length; i++) {
			if (max < elements[i]) {
				max = elements[i];
			}
		}
		return max;
	}

	public static byte min(final byte... elements) {
		byte min = elements[0];
		for (int i = 1; i < elements.length; i++) {
			if (min > elements[i]) {
				min = elements[i];
			}
		}
		return min;
	}

	public static double min(final double... elements) {
		double min = elements[0];
		for (int i = 1; i < elements.length; i++) {
			if (min > elements[i]) {
				min = elements[i];
			}
		}
		return min;
	}

	public static float min(final float... elements) {
		float min = elements[0];
		for (int i = 1; i < elements.length; i++) {
			if (min > elements[i]) {
				min = elements[i];
			}
		}
		return min;
	}

	public static int min(final int... elements) {
		int min = elements[0];
		for (int i = 1; i < elements.length; i++) {
			if (min > elements[i]) {
				min = elements[i];
			}
		}
		return min;
	}

	public static long min(final long... elements) {
		long min = elements[0];
		for (int i = 1; i < elements.length; i++) {
			if (min > elements[i]) {
				min = elements[i];
			}
		}
		return min;
	}

	public static short min(final short... elements) {
		short min = elements[0];
		for (short i = 1; i < elements.length; i++) {
			if (min > elements[i]) {
				min = elements[i];
			}
		}
		return min;
	}

	@SuppressWarnings("unchecked")
	public static <E> E[] removeNull(final E... elements) {
		final List<E> newList = new ArrayList<E>(elements.length);
		if (elements == null || elements.length != 0) {
			return (E[]) new Object[0];
		}

		for (final E object : elements) {
			if (object == null) {
				continue;
			}
			newList.add(object);
		}
		return newList.toArray(elements);
	}

	public static byte[] reverse(final byte... elements) {
		final byte[] result = elements.clone();
		for (int i = 0, len = elements.length, base = len - 1; i < len; i++) {
			result[i] = elements[base - i];
		}
		return result;
	}

	public static double[] reverse(final double... elements) {
		final double[] result = elements.clone();
		for (int i = 0, len = elements.length, base = len - 1; i < len; i++) {
			result[i] = elements[base - i];
		}
		return result;
	}

	public static <E> E[] reverse(@SuppressWarnings("unchecked") final E... elements) {
		final E[] result = elements.clone();
		for (int i = 0, len = elements.length, base = len - 1; i < len; i++) {
			result[i] = elements[base - i];
		}
		return result;
	}

	public static float[] reverse(final float... elements) {
		final float[] result = elements.clone();
		for (int i = 0, len = elements.length, base = len - 1; i < len; i++) {
			result[i] = elements[base - i];
		}
		return result;
	}

	public static int[] reverse(final int... elements) {
		final int[] result = elements.clone();
		for (int i = 0, len = elements.length, base = len - 1; i < len; i++) {
			result[i] = elements[base - i];
		}
		return result;
	}

	public static long[] reverse(final long... elements) {
		final long[] result = elements.clone();
		for (int i = 0, len = elements.length, base = len - 1; i < len; i++) {
			result[i] = elements[base - i];
		}
		return result;
	}

	public static short[] reverse(final short... elements) {
		final short[] result = elements.clone();
		for (int i = 0, len = elements.length, base = len - 1; i < len; i++) {
			result[i] = elements[base - i];
		}
		return result;
	}

	public static void swap(final boolean[] elements, final int i, final int j) {
		final boolean temp = elements[i];
		elements[i] = elements[j];
		elements[j] = temp;
	}

	public static void swap(final byte[] elements, final int i, final int j) {
		final byte temp = elements[i];
		elements[i] = elements[j];
		elements[j] = temp;
	}

	public static void swap(final char[] elements, final int i, final int j) {
		final char temp = elements[i];
		elements[i] = elements[j];
		elements[j] = temp;
	}

	public static void swap(final double[] elements, final int i, final int j) {
		final double temp = elements[i];
		elements[i] = elements[j];
		elements[j] = temp;
	}

	public static <E> void swap(final E[] elements, final int i, final int j) {
		final E temp = elements[i];
		elements[i] = elements[j];
		elements[j] = temp;
	}

	public static void swap(final float[] elements, final int i, final int j) {
		final float temp = elements[i];
		elements[i] = elements[j];
		elements[j] = temp;
	}

	public static void swap(final int[] elements, final int i, final int j) {
		final int temp = elements[i];
		elements[i] = elements[j];
		elements[j] = temp;
	}

	public static void swap(final long[] elements, final int i, final int j) {
		final long temp = elements[i];
		elements[i] = elements[j];
		elements[j] = temp;
	}

	public static void swap(final short[] elements, final int i, final int j) {
		final short temp = elements[i];
		elements[i] = elements[j];
		elements[j] = temp;
	}
	
	private ArrayUtil() {
	}
}
