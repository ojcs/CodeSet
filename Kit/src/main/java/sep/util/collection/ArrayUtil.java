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

	public static byte max(final byte... values) {
		byte max = values[0];
		for (int i = 1; i < values.length; i++) {
			if (max < values[i]) {
				max = values[i];
			}
		}
		return max;
	}

	public static double max(final double... values) {
		double max = values[0];
		for (int i = 1; i < values.length; i++) {
			if (max < values[i]) {
				max = values[i];
			}
		}
		return max;
	}

	public static float max(final float... values) {
		float max = values[0];
		for (int i = 1; i < values.length; i++) {
			if (max < values[i]) {
				max = values[i];
			}
		}
		return max;
	}

	public static int max(final int... values) {
		int max = values[0];
		for (int i = 1; i < values.length; i++) {
			if (max < values[i]) {
				max = values[i];
			}
		}
		return max;
	}

	public static long max(final long... values) {
		long max = values[0];
		for (int i = 1; i < values.length; i++) {
			if (max < values[i]) {
				max = values[i];
			}
		}
		return max;
	}

	public static short max(final short... values) {
		short max = values[0];
		for (int i = 1; i < values.length; i++) {
			if (max < values[i]) {
				max = values[i];
			}
		}
		return max;
	}

	public static byte min(final byte... values) {
		byte min = values[0];
		for (int i = 1; i < values.length; i++) {
			if (min > values[i]) {
				min = values[i];
			}
		}
		return min;
	}

	public static double min(final double... values) {
		double min = values[0];
		for (int i = 1; i < values.length; i++) {
			if (min > values[i]) {
				min = values[i];
			}
		}
		return min;
	}

	public static float min(final float... values) {
		float min = values[0];
		for (int i = 1; i < values.length; i++) {
			if (min > values[i]) {
				min = values[i];
			}
		}
		return min;
	}

	public static int min(final int... values) {
		int min = values[0];
		for (int i = 1; i < values.length; i++) {
			if (min > values[i]) {
				min = values[i];
			}
		}
		return min;
	}

	public static long min(final long... values) {
		long min = values[0];
		for (int i = 1; i < values.length; i++) {
			if (min > values[i]) {
				min = values[i];
			}
		}
		return min;
	}

	public static short min(final short... values) {
		short min = values[0];
		for (short i = 1; i < values.length; i++) {
			if (min > values[i]) {
				min = values[i];
			}
		}
		return min;
	}

	@SuppressWarnings("unchecked")
	public static <E> E[] removeNull(final E... array) {
		final List<E> newList = new ArrayList<E>(array.length);
		if (array == null || array.length != 0) {
			return (E[]) new Object[0];
		}

		for (final E object : array) {
			if (object == null) {
				continue;
			}
			newList.add(object);
		}
		return newList.toArray(array);
	}

	public static byte[] reverse(final byte... array) {
		final byte[] result = array.clone();
		for (int i = 0, len = array.length, base = len - 1; i < len; i++) {
			result[i] = array[base - i];
		}
		return result;
	}

	public static double[] reverse(final double... array) {
		final double[] result = array.clone();
		for (int i = 0, len = array.length, base = len - 1; i < len; i++) {
			result[i] = array[base - i];
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

	public static float[] reverse(final float... array) {
		final float[] result = array.clone();
		for (int i = 0, len = array.length, base = len - 1; i < len; i++) {
			result[i] = array[base - i];
		}
		return result;
	}

	public static int[] reverse(final int... array) {
		final int[] result = array.clone();
		for (int i = 0, len = array.length, base = len - 1; i < len; i++) {
			result[i] = array[base - i];
		}
		return result;
	}

	public static long[] reverse(final long... array) {
		final long[] result = array.clone();
		for (int i = 0, len = array.length, base = len - 1; i < len; i++) {
			result[i] = array[base - i];
		}
		return result;
	}

	public static short[] reverse(final short... array) {
		final short[] result = array.clone();
		for (int i = 0, len = array.length, base = len - 1; i < len; i++) {
			result[i] = array[base - i];
		}
		return result;
	}

	public static void swap(final boolean[] array, final int i, final int j) {
		final boolean temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	public static void swap(final byte[] array, final int i, final int j) {
		final byte temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	public static void swap(final char[] array, final int i, final int j) {
		final char temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	public static void swap(final double[] array, final int i, final int j) {
		final double temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	public static <E> void swap(final E[] array, final int i, final int j) {
		final E temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	public static void swap(final float[] array, final int i, final int j) {
		final float temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	public static void swap(final int[] array, final int i, final int j) {
		final int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	public static void swap(final long[] array, final int i, final int j) {
		final long temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	public static void swap(final short[] array, final int i, final int j) {
		final short temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
	
	private ArrayUtil() {
	}
}
