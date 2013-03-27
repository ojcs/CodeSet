package sep.framework.text;

import java.util.regex.Pattern;

public enum RegexNumber implements RegexEnum {
	/**
	 * 二进制
	 */
	Binary("[0-1]+"),
	/**
	 * 八进制
	 */
	Octal("[0-7]+"),
	/**
	 * 十进制(整数)
	 */
	Decimal_Integer("[+-]?(?<Integer>\\d+)"),
	/**
	 * 十进制(小数)
	 */
	Decimal_Decimal(Decimal_Integer + "(?<Decimal>\\.\\d+)"),
	/**
	 * 十进制(科学计数法)
	 */
	Decimal_ScientificNotation("[+-]?(?<Integer>(?:\\d+,(?:\\d{3,4},|\\d{3,4})+)|\\d+)(?<Decimal>\\.\\d+|)(?:(?:[Ee]?[+-]?(?<Power>\\d+))|)"),
	/**
	 * 十进制(允许小数)
	 */
	Decimal("[+-]?" + Decimal_Integer + "(?<Decimal>\\.\\d+|)"),
	/**
	 * 十六进制
	 */
	Hexadecimal("[0-9A-Fa-f]{2}+"),
	/**
	 * 三十六进制
	 */
	ThreeHexadecimal("[0-9A-Za-z]{2}+");
	private final String regex;
	
	private RegexNumber(final String regex) {
		this.regex = regex;
	}
	
	@Override
	public String pattern() {
		return regex;
	}
	
	@Override
	public Pattern compile() {
		return Pattern.compile(regex);
	}
	
	@Override
	public Pattern compile(final int flags) {
		return Pattern.compile(regex, flags);
	}

	@Override
	public boolean matches(final CharSequence input) {
		return Pattern.matches(regex, input);
	}
}
