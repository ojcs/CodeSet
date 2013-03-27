package sep.util.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;

public final class ProxyUtil {
	private static Object getProxy(final InvocationHandler handler, final Class<?> realRole, final Class<?>... abstractRoles) {
		final boolean isRealRoleIsClass = ((Modifier.classModifiers() & realRole.getModifiers()) != 0);
		if (isRealRoleIsClass && abstractRoles.length > 0) {
			for (final Class<?> role : abstractRoles) {
				final int modifiers = role.getModifiers();
				if (Modifier.isInterface(modifiers) || Modifier.isAbstract(modifiers)) {
					throw new IllegalArgumentException("abstractRoles not is abstract class / interface");
				}
			}
			return Proxy.newProxyInstance(realRole.getClassLoader(), abstractRoles, handler);
		} else {
			throw new IllegalArgumentException("abstractRoles not is abstract class / interface");
		}
	}
	
	@SuppressWarnings("unchecked")
	public static <T, R extends T> R getProxy(final InvocationHandler handler, final Class<T> clazz) {
		return (R) getProxy(handler, clazz, clazz.getInterfaces());
	}
	
	@SuppressWarnings("unchecked")
	public static <T, R extends T> R getProxy(final InvocationHandler handler, final T realRole, final Class<? super T>... abstractRoles) {
		return (R) getProxy(handler, realRole.getClass(), abstractRoles);
	}
	
	private ProxyUtil() {
	}
}
