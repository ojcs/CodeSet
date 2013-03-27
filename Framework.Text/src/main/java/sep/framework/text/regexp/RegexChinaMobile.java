package sep.framework.text.regexp;

import java.util.regex.Pattern;

public enum RegexChinaMobile implements RegexEnum {
	/**
	 * 手机号码
	 * 
	 * <pre>
	 * 中国移动(China Mobile)
	 * 		GSM					[134[0-8],135,136,137,138,139,150,151,152,157]
	 * 		TD-SCDMA(3G)		[158,159,182,187,188]
	 * 		TD-SCDMA(3G)上网卡	[147]
	 * 中国电信(China Telecom)
	 * 		GSM					[130,131,132,155,156,145]
	 * 		WCDMA				[185,186]
	 * 中国联通(China Unicom)
	 * 		CDMA　　　　　　　	[133,153,1349]
	 * 		CDMA2000(天翼3G)		[180,181,189]
	 * </pre>
	 */
	All("^0?(13\\d|15[0-35-9]|18[0236-9]|14[57])(\\d{8})"),
	/**
	 * IP电话
	 */
	AllIP("^(17951|17950|17909|17908|17910|17911|)"),
	/**
	 * 中国移动(China Mobile)
	 * 
	 * <pre>
	 * GSM					[134[0-8],135,136,137,138,139,150,151,152,157]
	 * TD-SCDMA(3G)			[158,159,182,187,188]
	 * 上网卡				[147]
	 * </pre>
	 */
	Mobile("^0?1(34[0-8]|(3[5-9]|5[0-27-9]|8[12578])\\d)\\d{7}$"),
	/**
	 * 中国移动 IP电话
	 */
	MobileIP("^(17951|17950|)$"),
	/**
	 * 中国移动 上网卡
	 */
	MobileNetworkCard("147\\d{8}"),
	/**
	 * 大陆地区固话及小灵通
	 * 
	 * <pre>
	 * 区号：[010,020,021,022,023,024,025,027,028,029]
	 * 号码：七位/八位
	 * </pre>
	 */
	PHS("^0(10|2[0-5789]|\\d{3})\\d{7,8}$"),
	/**
	 * 中国电信(China Telecom)
	 * 
	 * <pre>
	 * CDMA　　　　　　　[133,153,1349]
	 * CDMA2000(天翼3G)	[180,181,189]
	 * 
	 * 1349(原中国卫通卫星电话)
	 * </pre>
	 */
	Telecom("^0?1((33|53|8[019]|1349)[0-9])\\d{7}$"),
	/**
	 * 中国电信 IP电话
	 */
	TelecomIP("^(17909|17908|)$"),
	/**
	 * 中国联通(China Unicom)
	 * 
	 * <pre>
	 * GSM				[130,131,132,155,156]
	 * WCDMA			[185,186]
	 * 
	 * 上网卡			[145]
	 * </pre>
	 */
	Unicom("^0?1(3[0-2]|5[256]|8[56]|145)\\d{8}$"),
	/**
	 * 中国联通 IP电话
	 */
	UnicomIP("^(17910|17911|)$"),
	/**
	 * 中国联通 上网卡
	 */
	UnicomNetworkCard("145\\d{8}");
	private final String regex;

	private RegexChinaMobile(final String regex) {
		this.regex = regex;
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
	public String pattern() {
		return regex;
	}

	@Override
	public boolean matches(final CharSequence input) {
		return Pattern.matches(regex, input);
	}
}
