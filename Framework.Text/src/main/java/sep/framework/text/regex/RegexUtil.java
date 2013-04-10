package sep.framework.text.regex;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class RegexUtil {
	/**
	 * 查找指定字符串中包含匹配指定正则表达式的次数
	 * 
	 * @param content 要查找的字符串
	 * @param regex 正则表达式
	 * 
	 * @return 字符串中包含匹配指定正则表达式的次数
	 */
	public static int count(final String content, final String regex) {
		if (content != null && regex != null) {
			return Pattern.compile(regex).matcher(regex).groupCount();
		} else {
			return 0;
		}
	}

	public static String[] findAll(final String source, final String regex) {
		final Matcher matchs = Pattern.compile(regex).matcher(source);
		final String[] result = new String[matchs.groupCount()];
		for (int i = 0; i < result.length;) {
			result[i] = matchs.group(i++);
		}
		return result;
	}

	public static String findAndReplace(final String source, final String regex, final String replacement) {
		final String result = find(source, regex);
		return result.isEmpty() ? source : source.replace(result, replacement);
	}

	public static String find(final String source, final String regex) {
		final Matcher matcher = Pattern.compile(regex).matcher(source);
		return matcher.find() ? matcher.group() : "";
	}

	public static String replaceAll(final CharSequence input, final String regex, final String replacement) {
		return Pattern.compile(regex).matcher(input).replaceAll(replacement);
	}

	public static Map<Pattern, String> replaceAllCompile(final Map<String, String> map) {
		Map<Pattern, String> compile = new HashMap<>(map.size());
		for (Entry<String, String> entry : map.entrySet()) {
			compile.put(Pattern.compile(entry.getKey()), entry.getValue());
		}
		return compile;
	}
	
	public static String replaceAll(final CharSequence input, final Map<Pattern, String> map) {
		String result = input.toString();
		for (Entry<Pattern, String> entry : map.entrySet()) {
			result = entry.getKey().matcher(result).replaceAll(entry.getValue());
		}
		return result;
	}

	private RegexUtil() {
	}
}
