package sep.util.other;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.EnumSet;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import sep.util.collection.Fetch;
import sep.util.io.stream.StreamConvert;
import sep.util.time.TimeFormat;

public final class Print {
	private static void outln() {
		System.out.println();
	}

	private static void outln(final Object object, final Object... objects) {
		System.out.printf(object.toString() + "%n", objects);
	}
	
	public static void print(final Calendar calendar) {
		print(calendar.getTime());
	}

	public static void print(final CharSequence sequence) {
		if (sequence != null && sequence.length() > 0) {
			outln(sequence.getClass().getName() + " CharLength:" + sequence.length());
			outln(sequence.toString());
		}
	}
	
	public static void print(final Reader reader) throws IOException {
		if (reader != null) {
			BufferedReader read = StreamConvert.toBuffered(reader);
			while (read.ready()) {
				System.out.println(read.readLine());	
			}
		}
	}
	
	public static void print(final Class<?> clazz) {
		outln(clazz);
		outln();
		outln(clazz.getPackage());
		if (clazz.getSuperclass() != null) {
			outln(clazz.getSuperclass());
		}
		if (clazz.getAnnotations().length > 0) {
			outln("Annotations: --------------------------------");
			for (final Annotation annotation : clazz.getAnnotations()) {
				outln(annotation);
			}
		}
		if (clazz.getFields().length > 0) {
			outln("Fields:      --------------------------------");
			for (Field field : clazz.getFields()) {
				outln(field);
			}
		}
		if (clazz.getMethods().length > 0) {
			outln("Methods:     --------------------------------");
			for (final Method method : clazz.getMethods()) {
				outln(method);
			}
		}
	}
	
	public static void print(final Date date) {
		outln(TimeFormat.DateTime_24h.format(date));
	}
	
	public static void print(final Enum<?> enumObject) {
		if (enumObject != null) {
			outln("Begin Print %s Iterator Enum All", enumObject.getClass().getName());
			outln("{");
			@SuppressWarnings("unchecked")
			Set<Enum<?>> enums = EnumSet.allOf(enumObject.getClass());
			for (Enum<?> element : enums) {
				outln("\"%s\",\"%s\"%n", element.ordinal(), element.name());
			}
			outln("}");
			outln("Current Enum Name:" + enumObject.name());
			outln("End");
		}
	}
	
	public static void print(final Enumeration<?> enumeration) {
		print(Fetch.of(enumeration));
	}
	
	public static void print(final Iterable<?> iterable) {
		if (iterable != null && iterable.iterator().hasNext()) {
			outln("Begin Print %s Iterator", iterable.iterator().getClass().getName());
			outln("[");
			for (final Object object : iterable) {
				outln("\"" + object + "\",");
			}
			outln("]");
			outln("End");
		}
	}

	public static void print(final Iterator<?> iterator) {
		print(Fetch.of(iterator));
	}

	public static void print(final Map<?, ?> map) {
		if (!map.isEmpty()) {
			System.out.printf("Begin Print %s Iterator Map Collection%n", map.getClass().getName());
			System.out.println("{");
			for (final Map.Entry<?, ?> entry : map.entrySet()) {
				System.out.printf("\"%s\",\"%s\"%n", entry.getKey(), entry.getValue());
			}
			System.out.println("}");
			System.out.println("End");
		}
	}
	
	public static void print(final Object object) {
		if (object == null) {
			throw new IllegalArgumentException();
		} else if (object instanceof CharSequence) {
			print((CharSequence) object);
		} else if (object instanceof Iterable) {
			print((Iterable<?>) object);
		} else if (object instanceof Iterator) {
			print((Iterator<?>) object);
		} else if (object instanceof Enumeration) {
			print((Enumeration<?>) object);
		} else if (object instanceof Map) {
			print((Map<?, ?>) object);
		} else if (object instanceof Class) {
			print((Class<?>) object);
		} else if (object.getClass().isArray()) {
			prints(object);
		} else if (object instanceof Calendar) {
			print((Calendar) object);
		} else if (object instanceof Date) {
			print((Date) object);
		} else if (object instanceof Class) {
			print((Class<?>) object);
		} else {
			print(object.getClass());
		}
		System.out.println();
		System.out.printf("{\"hashCode\":\"%s,\"identityHashCode\":\"%s\"}%n", Convert.toString(object.hashCode(), 16), Convert.toString(System.identityHashCode(object), 16));
		System.out.printf("ToString:" + object.toString());
	}
	
	public static void prints(final Object objects) {
		if (objects != null) {
			outln("Begin Iterator Object Array");
			if (objects instanceof Object[]) {
				outln(Arrays.toString((Object[]) objects));
			} else if (objects instanceof double[]) {
				outln(Arrays.toString((double[]) objects));
			} else if (objects instanceof float[]) {
				outln(Arrays.toString((float[]) objects));
			} else if (objects instanceof long[]) {
				outln(Arrays.toString((long[]) objects));
			} else if (objects instanceof int[]) {
				outln(Arrays.toString((int[]) objects));
			} else if (objects instanceof char[]) {
				outln(Arrays.toString((char[]) objects));
			} else if (objects instanceof short[]) {
				outln(Arrays.toString((short[]) objects));
			} else if (objects instanceof byte[]) {
				outln(Arrays.toString((byte[]) objects));
			} else if (objects instanceof boolean[]) {
				outln(Arrays.toString((boolean[]) objects));
			}
		}
	}
	
	public static void prints(final int[][] net) {
		System.out.printf("Rows:%s\tCalls:%s%n", net.length, net[0].length);
		for (int i = 0; i < net.length; i++) {
			System.out.print(net[i][0] + " ");
			for (int j = 0; j < net[i].length; j++) {
				System.out.print(net[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public static void prints(final long[][] net) {
		for (int i = 0; i < net.length; i++) {
			System.out.print(i + " ");
			for (int j = 0; j < net[i].length; j++) {
				System.out.print(j + " ");
			}
			System.out.println();
		}
	}
	
	private Print() {
	}
}
