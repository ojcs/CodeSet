package sep.framework.text.format;

import java.text.FieldPosition;
import java.text.ParsePosition;

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
		double num = Math.abs(number);
		String result = "";
		for (int i = 0; i < fraction.length; i++) {
			result += String.valueOf(new char[] { digit[(int) (Math.floor(num * 10 * Math.pow(10, i)) % 10)], fraction[i] }).replaceAll("(零.)+", "");
		}
		if (result.length() < 1) {
			result = "整";
		}
		int integerPart = (int) Math.floor(num);
		for (int i = 0; i < unit[0].length && integerPart > 0; i++) {
			String part = "";
			for (int j = 0; j < unit[1].length && num > 0; j++) {
				part = digit[integerPart % 10] + unit[1][j] + part;
				integerPart = integerPart / 10;
			}
			result = part.replaceAll("(零.)*零$", "").replaceAll("^$", "零") + unit[0][i] + result;
		}
		return toAppendTo.append(number < 0 ? "负" : "").append(result.replaceAll("(零.)*零元", "元").replaceFirst("(零.)+", "").replaceAll("(零.)+", "零").replaceAll("^整$", "零元整"));
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