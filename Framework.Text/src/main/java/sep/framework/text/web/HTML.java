package sep.framework.text.web;

import java.util.HashMap;
import java.util.Map;

import sep.framework.text.regex.RegexUtil;

public class HTML {
	/**
	 * 压缩
	 * @Ref http://www.oschina.net/code/snippet_258733_18236
	 */
	public static String compress(final CharSequence input) {
		Map<String, String> map = new HashMap<>(9);
		map.put("\r\n", "");// 清除换行符
		map.put("\n", "");// 清除换行符
		map.put("\t", "");// 清除制表符
		map.put("/> *([^ ]*) *</", ">\\1<");// 去掉注释标记
		map.put("/[\\s]+/", " ");
		map.put("/<!--[\\w\\W\r\\n]*?-->/", "");
		map.put("/\" /", "\"");
		map.put("/ \"/", "\"");
		map.put("'/\\*[^*]*\\*/'", "");
		return RegexUtil.replaceAll(input, RegexUtil.replaceAllCompile(map)).toString();
	}
}
