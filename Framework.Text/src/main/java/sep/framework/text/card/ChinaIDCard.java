package sep.framework.text.card;

import static sep.framework.text.regexp.RegexCard.ChinaID15;
import static sep.framework.text.regexp.RegexCard.ChinaID18;

import java.text.ParseException;
import java.util.Date;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

import sep.util.other.Convert;
import sep.util.other.Gender;

public final class ChinaIDCard {
	private CharSequence id;
	private int area;
	private long birthday;
	private int seq;
	private Gender gender;
	
	public ChinaIDCard(final CharSequence id) throws ParseException {
		Pattern pattern;
		birthday = 57751891200000L;
		switch (id.length()) {
		case 15:
			pattern = ChinaID15.compile();
			if (pattern.matcher(id).matches()) {
				MatchResult result = ChinaID15.compile().matcher(id);
				area = Integer.valueOf(result.group());
				birthday = Convert.parseDate("yyyyMMdd", "19" + result.group()).getTime();
				seq = Integer.valueOf(result.group());
				gender = Integer.valueOf(result.group()) % 2 == 0 ? Gender.Male : Gender.Female;
			}
			this.id = id;
		case 18:
			pattern = ChinaID15.compile();
			if (pattern.matcher(id).matches() && checkCheckCode()) {
				MatchResult result = ChinaID18.compile().matcher(id);
				area = Integer.valueOf(result.group());
				birthday = Convert.parseDate("yyyyMMdd", result.group()).getTime();
				seq = Integer.valueOf(result.group());
				gender = Integer.valueOf(result.group()) % 2 == 0 ? Gender.Male : Gender.Female;
			}
			this.id = id;
		default:
			throw new IllegalArgumentException();
		}
	}
	
	public int getAreaCode() {
		return area;
	}
	
	public Date getBirthday() {
		return new Date(birthday);
	}
	
	public Gender getGender() {
		return gender;
	}
	
	public boolean checkCheckCode() {
		if (id.length() == 18) {
			return id.charAt(18) == calcCheckCode(area, getBirthday(), seq);
		} else {
			throw new IllegalArgumentException("not new id card");
		}
	}
	
	private static char calcCheckCode(final int area, final Date birthday, final int seq) {
		final CharSequence idCard = String.format("%1$%2$tY%2$tm%2$te%3$", area, birthday, seq);
		// 加权
		final int[] power = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1 };
		int sum = 0;
		for (int i = 0; i < 17; i++) {
			sum += Character.digit(idCard.charAt(i), 10) * power[i];
		}
		final int check = (12 - (sum % 11)) % 11;
		return (check == 10) ? 'X' : Character.forDigit(check, 10);
	}
	
	public String as15() {
		return String.format("%1$%2$ty%2$tm%2$te%3$", area, getBirthday(), seq);
	}
	
	public String as18() {
		return String.format("%1$%2$tY%2$tm%2$te%3$%4$", area, getBirthday(), seq, calcCheckCode(area, getBirthday(), seq));
	}
}
