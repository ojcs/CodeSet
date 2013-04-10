package sep.framework.text.regex;


public enum Card {
	Bank("^[3-69][23589][1-35-8][015-8][02-6][0-24-9](?:\\d{13,16}|\\d{18,19})$"),
	/** 中国公民身份证(15位) */
	ChinaIDOld("^(?<Area>[1-8][1-2]\\d{4})(?<Birthday>\\d{6})(?<Seq>\\d{2}(?<Gender>\\d))$"),
	/** 中国公民身份证(18位) */
	ChinaIDNew("^(?<Area>[1-8][1-2]\\d{4})(?<Birthday>\\d{8})(?<Seq>\\d{2}(?<Gender>\\d))(?<Check>[0-9X])$");
	public final String regex;

	private Card(final String regex) {
		this.regex = regex;
	}
}
