package sep.frameworkutil.orm.mybatis.paging;

import java.lang.reflect.Field;

/**
 * 反射工具类
 * 
 * @author dixingxing	
 * @date 2012-7-12
 */
class ReflectHelper {
	/**
	 * 获取object对象fieldName的Field
	 */
	public static Field getFieldByFieldName(Object object, String fieldName) {
		for (Class<?> superClass = object.getClass(); superClass != Object.class; superClass = superClass.getSuperclass()) {
			try {
				return superClass.getDeclaredField(fieldName);
			} catch (NoSuchFieldException e) {
			}
		}
		return null;
	}

	/**
	 * 获取object对象fieldName的属性
	 */
	public static Object getValueByFieldName(Object object, String fieldName) throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
		Field field = getFieldByFieldName(object, fieldName);
		Object value = null;
		if (field != null) {
			if (field.isAccessible()) {
				value = field.get(object);
			} else {
				field.setAccessible(true);
				value = field.get(object);
				field.setAccessible(false);
			}
		}
		return value;
	}

	/**
	 * 设置object对象fieldName的属性
	 */
	public static void setValueByFieldName(Object obj, String fieldName, Object value) throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
		Field field = obj.getClass().getDeclaredField(fieldName);
		if (field.isAccessible()) {
			field.set(obj, value);
		} else {
			field.setAccessible(true);
			field.set(obj, value);
			field.setAccessible(false);
		}
	}
}