package sep.util.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public final class Dynamic<T> {
	@SuppressWarnings("unchecked")
	public static <T> Dynamic<T> of(final T object) {
		return new Dynamic<T>((Class<T>) object.getClass(), object);
	}

	private final Class<T> clazz;
	
	private final T object;
	
	private Dynamic(final Class<T> clazz, final T object) {
		this.object = object;
		this.clazz = clazz;
	}
	
	public <A extends Annotation> A annotation(final Class<A> annotationClass) {
		return clazz.getAnnotation(annotationClass);
	}
	
	public Object field(final String name) {
		try {
			Field field = clazz.getDeclaredField(name);
			field.setAccessible(true);
			return field.get(object);
		} catch (ReflectiveOperationException e) {
			throw new RuntimeException(e);
		}
	}

	public void field(final String name, final Object value) {
		try {
			Field field = clazz.getDeclaredField(name);
			field.setAccessible(true);
			field.set(object, value);
		} catch (ReflectiveOperationException e) {
			throw new RuntimeException(e);
		}
	}
	
	public <A extends Annotation> A fieldAnnotation(final String name, final Class<A> annotationClass) {
		try {
			Field field = clazz.getDeclaredField(name);
			field.setAccessible(true);
			return field.getAnnotation(annotationClass);
		} catch (ReflectiveOperationException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Class<T> getClazz() {
		return clazz;
	}
	
	public T getObject() {
		return object;
	}

	public Object method(final String name, final Object... args) {
		try {
			Method method = clazz.getDeclaredMethod(name, parameterTypes(args));
			method.setAccessible(true);
			try {
				return method.invoke(object, args);
			} catch (ReflectiveOperationException e) {
				return method.getDefaultValue();
			}
		} catch (ReflectiveOperationException e) {
			throw new RuntimeException(e);
		}
	}
	
	public <A extends Annotation> A methodAnnotation(final String name, final Class<A> annotationClass, final Class<?>... args) {
		try {
			return clazz.getDeclaredMethod(name, args).getAnnotation(annotationClass);
		} catch (ReflectiveOperationException e) {
			throw new RuntimeException(e);
		}
	}
	
	protected Class<?>[] parameterTypes(final Object... objects) {
		Class<?>[] classes = new Class<?>[objects.length];
		for (int i = 0; i < objects.length; i++) {
			classes[i] = objects[i].getClass();
		}
		return classes;
	}
}
