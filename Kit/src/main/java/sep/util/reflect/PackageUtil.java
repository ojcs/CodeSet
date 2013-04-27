package sep.util.reflect;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import sep.util.collection.Cursor;

public class PackageUtil {
	/**
	 * 取得class所在 包名称
	 * @return 返回值为null表示直接在classpath下
	 */
	public static String getFileName(Class<?> clazz) {
		String file = clazz.getProtectionDomain().getCodeSource().getLocation().getFile();
		if (file.endsWith(File.separator)) {
			return null;
		} else {
			return new File(file).getName();
		}
	}

	/** 取得class所在 包中的资源 */
	public static URL getResource(Class<?> clazz, String resource) throws IOException {
		if (resource.startsWith(File.separator)) {
			resource = resource.substring(1);
		}
		URL location = clazz.getProtectionDomain().getCodeSource().getLocation();
		if (location.getPath().endsWith(File.separator)) {
			return clazz.getResource(resource);// 就在classpath下
		}
		String path = location.toString();
		for (URL url : Cursor.of(clazz.getClassLoader().getResources(resource))) {
			if (url.getPath().startsWith(path)) {
				return url;
			}
		}
		return null;
	}

	/**
	 * 取得class所在 包中的资源
	 * @param resource 所取的资源
	 */
	public static InputStream getResourceAsStream(Class<?> clazz, String resource) throws IOException {
		URL url = getResource(clazz, resource);
		return url != null ? url.openStream() : null;
	}

	private PackageUtil() {
	}
}
