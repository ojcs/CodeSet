package sep.framework.text.format;

import java.text.FieldPosition;
import java.text.ParsePosition;
import java.util.HashMap;
import java.util.Map;

import sep.framework.text.regexp.RegexUtil;

/**
 * 人民币大写转换
 * @Ref:http://www.oschina.net/code/snippet_32903_18900
 */
public class RMBFormat extends java.text.NumberFormat {
	private static final long serialVersionUID = 1L;

	private static final char[] fraction = { '角', '分' };
	private static final char[] digit = { '零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖' };
	private static final String[][] unit = { { "元", "万", "亿" }, { "", "拾", "佰", "仟" } };
	
	@Override
	public StringBuffer format(double number, StringBuffer toAppendTo, FieldPosition pos) {
		double value = Math.abs(number);
		String result = "";
		for (int i = 0; i < fraction.length; i++) {
			result += String.valueOf(new char[] { digit[(int) (Math.floor(value * 10 * Math.pow(10, i)) % 10)], fraction[i] }).replaceAll("(零.)+", "");
		}
		toAppendTo.append(result.isEmpty() ? '整' : result);
		int integerPart = (int) Math.floor(value);
		for (int i = 0; i < unit[0].length && integerPart > 0; i++) {
			String part = "";
			for (int j = 0; j < unit[1].length && value > 0; j++) {
				part = digit[integerPart % 10] + unit[1][j] + part;
				integerPart = integerPart / 10;
			}
			toAppendTo.insert(0, part.replaceAll("(零.)*零$", "").replaceAll("^$", "零") + unit[0][i]);
		}
		if (number < 0) {
			toAppendTo.append('负');
		}
		Map<String, String> map = new HashMap<>(4);
		map.put("(零.)*零元", "元");
		map.put("(零.)+", "");
		map.put("(零.)+", "零");
		map.put("^整$", "零元整");
		return toAppendTo.append(RegexUtil.replaceAll(toAppendTo, RegexUtil.replaceAllCompile(map)));
	}

	@Override
	public StringBuffer format(long number, StringBuffer toAppendTo, FieldPosition pos) {
		return format((double) number, toAppendTo, pos);
	}

	@Override
	public Number parse(String source, ParsePosition parsePosition) {
		throw new UnsupportedOperationException();
	}
}