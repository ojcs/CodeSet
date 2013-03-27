package sep.framework.text;

import java.util.regex.Pattern;

public enum RegexCard implements RegexEnum {
	/**
	 * 
	 */
	Bank("^[3-69][23589][1-35-8][015-8][02-6][0-24-9](?:\\d{13,16}|\\d{18,19})$"),
	/**
	 * 中国公民身份证(15位)
	 */
	ChinaID15("^(?<Area>[1-8][1-2]\\d{4})(?<Birthday>\\d{6})(?<Seq>\\d{2}(?<Gender>\\d))$"),
	/**
	 * 中国公民身份证(18位)
	 */
	ChinaID18("^(?<Area>[1-8][1-2]\\d{4})(?<Birthday>\\d{8})(?<Seq>\\d{2}(?<Gender>\\d))(?<Check>[0-9X])$");
	private final String regex;

	private RegexCard(final String regex) {
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
