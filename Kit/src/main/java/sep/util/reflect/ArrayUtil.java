package sep.util.reflect;

import java.lang.reflect.Array;

public final class ArrayUtil<T> {
	@SuppressWarnings("unchecked")
	public static <T> T[] newArray(final Class<T> type, final int length) {
		return (T[]) Array.newInstance(type, length);
	}
}
