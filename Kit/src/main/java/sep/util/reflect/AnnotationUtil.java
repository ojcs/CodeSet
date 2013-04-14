package sep.util.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;

import sep.util.collection.ArrayUtil;

public class AnnotationUtil {
	public static boolean has(final AnnotatedElement element, final Class<? extends Annotation> annotatationClass) {
		return element.isAnnotationPresent(annotatationClass);
	}
	
	public static boolean hasDeprecated(final AnnotatedElement element) {
		return has(element, Deprecated.class);
	}
	
	public static boolean hasSuppressWarnings(final AnnotatedElement element, final String value) {
		final String[] values = get(element, SuppressWarnings.class).value();
		return has(element, SuppressWarnings.class) && ArrayUtil.contains(values, value);
	}
	
	public static boolean hasOverride(final AnnotatedElement element) {
		return has(element, Override.class);
	}
	
	public static boolean hasOverrideMethod(final Class<?> clazz, final String methodName, final Class<?>... parameterTypes) throws NoSuchMethodException, SecurityException {
		return hasOverride(clazz.getMethod(methodName, parameterTypes));
	}
	
	public static boolean hasOverrideEquals(final Class<?> clazz) throws NoSuchMethodException, SecurityException {
		return hasOverrideMethod(clazz, "equals", Object.class);
	}
	
	public static boolean hasOverrideHashCode(final Class<?> clazz) throws NoSuchMethodException, SecurityException {
		return hasOverrideMethod(clazz, "hashCode");
	}
	
	public static boolean hasOverrideToString(final Class<?> clazz) throws NoSuchMethodException, SecurityException {
		return hasOverrideMethod(clazz, "toString");
	}
	
	public static boolean hasOverrideClone(final Class<? super Cloneable> clazz) throws NoSuchMethodException, SecurityException {
		return hasOverrideMethod(clazz, "clone");
	}
	
	public static boolean hasOverrideClose(final Class<? super AutoCloseable> clazz) throws NoSuchMethodException, SecurityException {
		return hasOverrideMethod(clazz, "close");
	}
	
	public static boolean hasOverrideFinalize(final Class<?> clazz) throws NoSuchMethodException, SecurityException {
		return hasOverrideMethod(clazz, "finalize");
	}
	
	public static <T extends Annotation> T get(final AnnotatedElement element, final Class<T> clazz) {
		return element.getAnnotation(clazz);
	}
	
	private AnnotationUtil() {
	}
}
