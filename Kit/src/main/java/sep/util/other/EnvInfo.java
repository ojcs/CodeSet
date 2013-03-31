package sep.util.other;

import java.io.File;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.TimeZone;

import sep.util.io.file.PathSet;

public final class EnvInfo {
	public static void clear(final String key) {
		System.clearProperty(key);
	}

	public static String get(final String key) {
		return System.getProperty(key);
	}

	public static String get(final String key, final String defaultValue) {
		return System.getProperty(key, defaultValue);
	}

	/**
	 * 文件编码
	 */
	public static Charset getFileEncoding() {
		return Charset.forName(get("file.encoding", Charset.defaultCharset().displayName()));
	}

	/**
	 * 文件分隔符(在Unix系统中是"/")
	 */
	public static String getFileSeparator() {
		return get("file.separator", File.separator);
	}

	/**
	 * 类路径
	 */
	public static PathSet getJavaClassPath() {
		PathSet paths = new PathSet();
		paths.addAll(get("java.class.path"));
		return paths;
	}

	/**
	 * 类格式版本号
	 */
	public static String getJavaClassVersion() {
		return get("java.class.version");
	}

	/**
	 * 要使用的JIT编译器的名称
	 */
	public static String getJavaCompiler() {
		return get("java.compiler");
	}

	/**
	 * 一个或多个扩展目录的路径
	 */
	public static PathSet getJavaExtDirs() {
		PathSet paths = new PathSet();
		paths.addAll(get("java.ext.dirs"));
		return paths;
	}

	/**
	 * 安装目录
	 */
	public static Path getJavaHome() {
		return Paths.get(get("java.home"));
	}

	/**
	 * 默认的临时文件路径
	 */
	public static Path getJavaIOTempDir() {
		return Paths.get(get("java.io.tmpdir"));
	}

	/**
	 * 默认的临时文件路径
	 */
	public static Path getJavaIOTmpdir() {
		return Paths.get(get("java.io.tmpdir"));
	}

	/**
	 * 加载库时搜索的路径列表
	 */
	public static PathSet getJavaLibraryPath() {
		PathSet paths = new PathSet();
		paths.addAll(get("java.library.path"));
		return paths;
	}

	/**
	 * 运行时环境规范名称
	 */
	public static String getJavaSpecificationName() {
		return get("java.specification.name");
	}

	/**
	 * 运行时环境规范供应商
	 */
	public static String getJavaSpecificationVendor() {
		return get("java.specification.vendor");
	}

	/**
	 * 运行时环境规范版本
	 */
	public static String getJavaSpecificationVersion() {
		return get("java.specification.version");
	}

	/**
	 * 运行时环境供应商
	 */
	public static String getJavaVendor() {
		return get("java.vendor");
	}

	/**
	 * 供应商的URL
	 */
	public static URI getJavaVendorURL() {
		return URI.create(get("java.vendor.url"));
	}

	/**
	 * 运行时环境版本
	 */
	public static String getJavaVersion() {
		return get("java.version");
	}

	/**
	 * 虚拟机实现名称
	 */
	public static String getJavaVMName() {
		return get("java.vm.name");
	}

	/**
	 * 虚拟机规范名称
	 */
	public static String getJavaVMSpecificationName() {
		return get("java.vm.specification.name");
	}

	/**
	 * 虚拟机规范供应商
	 */
	public static String getJavaVMSpecificationVendor() {
		return get("java.vm.specification.vendor");
	}

	/**
	 * 虚拟机规范版本
	 */
	public static String getJavaVMSpecificationVersion() {
		return get("java.vm.specification.version");
	}

	/**
	 * 虚拟机实现供应商
	 */
	public static String getJavaVMVendor() {
		return get("java.vm.vendor");
	}

	/**
	 * 虚拟机实现版本
	 */
	public static String getJavaVMVersion() {
		return get("java.vm.version");
	}

	/**
	 * 用户语言代码
	 */
	public static Locale getLanguage() {
		return new Locale(get("user.language", Locale.getDefault().toLanguageTag()));
	}

	/**
	 * 行分隔符(在Unix系统中是"\n")
	 */
	public static String getLineSeparator() {
		return get("line.separator");
	}

	/**
	 * 操作系统的架构
	 */
	public static String getOSArch() {
		return get("os.arch");
	}

	/**
	 * 操作系统的名称
	 */
	public static String getOSName() {
		return get("os.name");
	}

	/**
	 * 操作系统的版本
	 */
	public static String getOSVersion() {
		return get("os.version");
	}

	/**
	 * 路径分隔符(在Unix系统中是":")
	 */
	public static String getPathSeparator() {
		return get("path.separator", File.separator);
	}

	/**
	 * 用户的账户名称
	 */
	public static String getUserCurrentLoginName() {
		return get("user.name");
	}
	
	/**
	 * 用户的当前工作目录
	 */
	public static Path getUserDir() {
		return Paths.get(get("user.dir"));
	}
	
	/**
	 * 用户的主目录
	 */
	public static Path getUserHome() {
		return Paths.get(get("user.home"));
	}
	
	/**
	 * 用户的时区
	 */
	public static TimeZone getUserTimeZone() {
		return TimeZone.getTimeZone(get("user.timezone", TimeZone.getDefault().getID()));
	}
	
	public static String put(final String key, final String value) {
		return System.setProperty(key, value);
	}
	
	public static void putAll(final Map<String, String> propertyMap) {
		final Properties properties = new Properties();
		properties.putAll(propertyMap);
		System.setProperties(properties);
	}
	
	public static void putAll(final Properties properties) {
		System.setProperties(properties);
	}

	private EnvInfo() {
	}
}
