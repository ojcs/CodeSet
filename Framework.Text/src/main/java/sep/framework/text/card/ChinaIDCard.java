package sep.framework.text.card;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.MatchResult;

import sep.framework.text.regexp.RegexCard;
import sep.util.other.Convert;
import sep.util.other.Gender;

public final class ChinaIDCard {
	private final String idCard;
	private final int area;
	private final long birthday;
	private final int seq;
	private final Gender gender;
	
	public ChinaIDCard(final String idCard) {
		if (
			(idCard.length() == 15 && RegexCard.ChinaID15.matches(idCard))
			||
			(idCard.length() == 18 && RegexCard.ChinaID18.matches(idCard))
		) {
			this.idCard = idCard;
		} else {
			throw new IllegalArgumentException("idCard not is ChinaIDCard");
		}
		
		Map<String, Long> map = analyze();
		area = ((Long) map.get("area")).intValue();
		birthday = (Long) map.get("birthday");
		seq = ((Long) map.get("seq")).intValue();
		gender = (Long) map.get("seq") == 1L ? Gender.Male : Gender.Female;
	}
	
	private Map<String, Long> analyze() {
		Map<String, Long> map = new HashMap<String, Long>();
		map.put("birthday", 57751891200000L); // 1900-01-01
		switch (idCard.length()) {
		case 15:
			MatchResult result15 = RegexCard.ChinaID15.compile().matcher(idCard);
			map.put("area", Long.valueOf(result15.group()));
			map.put("birthday", Convert.parseDate("yyyyMMdd", "19" + result15.group()).getTime());
			map.put("seq", Long.valueOf(result15.group()));
			map.put("gender", Integer.valueOf(result15.group()) % 2 == 0 ? 1L : 0L);
			break;
		case 18:
			if (checkCheckCode()) {
				MatchResult result18 = RegexCard.ChinaID15.compile().matcher(idCard);
				map.put("area", Long.valueOf(result18.group()));
				map.put("birthday", Convert.parseDate("yyyyMMdd", result18.group()).getTime());
				map.put("seq", Long.valueOf(result18.group()));
				map.put("gender", Integer.valueOf(result18.group()) % 2 == 0 ? 1L : 0L);
			}
			break;
		}
		return map;
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
		return idCard.charAt(18) == calcCheckCode(area, getBirthday(), seq);
	}
	
	private static char calcCheckCode(final int area, final Date birthday, final int seq) {
		final char[] idCard = String.format("%1$%2$tY%2$tm%2$te%3$", area, birthday, seq).toCharArray();
		
		// 加权
		final int[] power = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1 };
		
		int sum = 0;
		for (int i = 0; i < 17; i++) {
			sum += Character.digit(idCard[i], 10) * power[i];
		}
		
		final int check = (12 - (sum % 11)) % 11;
		return (check == 10) ? 'X' : Character.forDigit(check, 10);
	}
	
	public String to15() {
		return String.format("%1$%2$ty%2$tm%2$te%3$", area, getBirthday(), seq);
	}
	
	public String to18() {
		return String.format("%1$%2$tY%2$tm%2$te%3$%4$", area, getBirthday(), seq, calcCheckCode(area, getBirthday(), seq));
	}
	
	@Override
	public int hashCode() {
		return idCard.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof CharSequence) {
			return obj.toString().equalsIgnoreCase(idCard);
		} else {
			return false;
		}
	}
	
	@Override
	public String toString() {
		return idCard;
	}
}
