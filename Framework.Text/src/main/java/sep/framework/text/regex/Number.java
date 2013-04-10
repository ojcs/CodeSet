package sep.framework.text.regex;


public enum Number {
	/** 二进制 */
	Binary("[0-1]+"),
	/** 八进制 */
	Octal("[0-7]+"),
	/** 十进制(整数) */
	Decimal_Integer("[+-]?(?<Integer>\\d+)"),
	/** 十进制(小数) */
	Decimal_Decimal("[+-]?(?<Integer>\\d+)(?<Decimal>\\.\\d+)"),
	/** 十进制(科学计数法) */
	Decimal_ScientificNotation("[+-]?(?<Integer>(?:\\d+,(?:\\d{3,4},|\\d{3,4})+)|\\d+)(?<Decimal>\\.\\d+|)(?:(?:[Ee]?[+-]?(?<Power>\\d+))|)"),
	/** 十进制(允许小数) */
	Decimal("[+-]?(?<Integer>\\d+)(?<Decimal>\\.\\d+|)"),
	/** 十六进制 */
	Hexadecimal("[0-9A-Fa-f]{2}+"),
	/** 三十六进制 */
	ThreeHexadecimal("[0-9A-Za-z]{2}+");
	public final String regex;
	
	private Number(final String regex) {
		this.regex = regex;
	}
}
