//package sep.util.reflect;
//
//import java.beans.IntrospectionException;
//import java.beans.Introspector;
//import java.beans.PropertyDescriptor;
//import java.io.Serializable;
//import java.lang.reflect.Method;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Map.Entry;
//
//import sep.framework.Text;
//
//public final class BeanUtil {
//	/**
//     * 将字段名转换为属性名
//     *
//     * @param columnName 字段名，如："name", "parent_node"
//     * @return 属性名，如："name", "parentNode"
//     */
//    public static String columnToProperty(final String columnName) {
//        final String[] words = columnName.toLowerCase().split("_");
//        final StringBuilder field = new StringBuilder(words[0]);
//        for (int i = 1; i < words.length; i++) {
//        	field.append(Text.lowerFirst(words[i]));
//        }
//        return field.toString();
//    }
//
//	public static <Bean extends Serializable> Object getField(final Bean bean, final String name) throws IntrospectionException, ReflectiveOperationException {
//		return new PropertyDescriptor(name, bean.getClass()).getReadMethod().invoke(bean);
//	}
//
//	public static <Bean extends Serializable> void print(final Bean bean) throws ReflectiveOperationException, IntrospectionException {
//    	System.out.printf("Begin Print %s Bean", bean.getClass().getName());
//		for (Entry<String, Object> entry : toMap(bean).entrySet()) {
//			System.out.printf("%s\t\t%s", entry.getKey(), entry.getValue());
//		}
//		System.out.println("End");
//	}
//	
//	/**
//     * 将属性名转换为字段名。属性名应符合 JavaBean 命名规范。
//     *
//     * @param propertyName 属性名，如："name", "parentNode"
//     * @return 字段名，如："name", "parent_node"
//     */
//    public static String propertyToColumn(final String propertyName) {
//        return propertyName.replaceAll("([A-Z])", "_$1").toLowerCase();
//    }
//
//	public static <Bean extends Serializable> void setField(final Bean bean, final String name, final Object value) throws IntrospectionException, ReflectiveOperationException {
//		new PropertyDescriptor(name, bean.getClass()).getWriteMethod().invoke(bean, value);
//	}
//	
//	/**
//	 * 将一个 Map对象转化为一个JavaBean
//	 * 
//	 * @param clazz 要转化的类型
//	 * @param map 包含属性值的Map<String, Object>
//	 * 
//	 * @return 转化出来的JavaBean对象
//	 */
//	public static <Bean extends Serializable> Bean toBean(final Class<Bean> clazz, final Map<String, Object> map) throws ReflectiveOperationException, IntrospectionException {
//		final Bean object = clazz.newInstance();
//		for (final PropertyDescriptor desc : Introspector.getBeanInfo(clazz).getPropertyDescriptors()) {
//			final String name = desc.getName();
//			if (map.containsKey(name)) {
//				desc.getWriteMethod().invoke(object, map.get(name));
//			}
//		}
//
//		return object;
//	}
//	
//	/**
//	 * 将一个 JavaBean对象转化为一个Map
//	 * 
//	 * @param bean 要转化的JavaBean对象
//	 * 
//	 * @return 转化出来的HashMap<String, Object>对象
//	 */
//	public static <Bean extends Serializable> Map<String, Object> toMap(final Bean bean) throws IntrospectionException, ReflectiveOperationException {
//		final Map<String, Object> map = new HashMap<String, Object>();
//
//		for (PropertyDescriptor desc : Introspector.getBeanInfo(bean.getClass()).getPropertyDescriptors()) {
//			final String name = desc.getName();
//			if (!"class".equals(name)) {
//				map.put(name, desc.getReadMethod().invoke(bean));
//			}
//		}
//
//		return map;
//	}
//
//    public static <Bean extends Serializable> Map<String, Method> toMap(final Class<Bean> clazz, final boolean isReadMethod) throws IntrospectionException {
//		final Map<String, Method> map = new HashMap<String, Method>();
//
//		for (PropertyDescriptor desc : Introspector.getBeanInfo(clazz).getPropertyDescriptors()) {
//			final String name = desc.getName();
//			if (!"class".equals(name)) {
//				map.put(name, isReadMethod ? desc.getReadMethod() : desc.getWriteMethod());
//			}
//		}
//
//		return map;
//	}
//
//    private BeanUtil() {
//	}
//}
