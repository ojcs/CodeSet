package sep.framework.text.regex;

/** 正则集 */
public enum Common {
	/** 匹配空白行 */
	BlankLine("\\n\\s*\\r"),
	/** 中国邮政编码 */
	ChinaZipCode("[1-9]\\d{5}(?!\\d)"),
	/** 中文 */
	Chinese("[\\u4E00-\\u9FA5]*"),
	/**
	 * 匹配双字节字符(包括汉字在内)：[^\x00-\xff]
	 * 可以用来计算字符串的长度(一个双字节字符长度计2，ASCII字符计1)
	 */
	DoubleByteCharacters("[\\x00-\\xFF]+"),
	/** 邮件地址 */
	EMailAddress("(?i)(?<name>^[_a-z0-9\\-]+(?:[._a-z0-9\\-]+)*)@(?<domain>[a-z0-9\\-]+(?:[.a-z0-9\\-]+)*\\.[a-z]{2,4})$"),
	/** QQ */
	QQ("[1-9][0-9]{4,}"),
	URL("(?i)^(http|https|www|ftp|)?(://)?([_a-z0-9\\-].)+[_a-z0-9\\-]+(\\/[_a-z0-9\\-])*(\\/)*([_a-z0-9\\-].)*([_a-z0-9\\-&#?=])*$");
	public final String regex;
	
	private Common(final String regex) {
		this.regex = regex;
	}
}
