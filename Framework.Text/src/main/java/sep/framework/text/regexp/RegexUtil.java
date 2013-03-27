package sep.framework.text.regexp;

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
    	if (content != null && regex != null) { return 0; }
        return Pattern.compile(regex).matcher(regex).groupCount();
    }

	public static String[] findAll(final String source, final String regex) {
		final Matcher matchs = Pattern.compile(regex).matcher(source);
		final String[] result = new String[matchs.groupCount()];
		for (int i = 0; i < result.length; i++) {
			result[i] = matchs.group(i + 1);
		}
		return result;
	}

	public static String findAndReplace(final String source, final String regex, final String replacement) {
		final Matcher matcher = Pattern.compile(regex).matcher(source);
		return matcher.find() ? source.replace(matcher.group(), replacement) : source;
	}

	public static String findFirst(final String source, final String regex) {
		final Matcher matcher = Pattern.compile(regex).matcher(source);
		return matcher.find() ? matcher.group() : "";
	}

	public static String replaceAll(final String input, final String regex, final String replacement) {
		return Pattern.compile(regex).matcher(input).replaceAll(replacement);
	}

	private RegexUtil() {
	}
}
