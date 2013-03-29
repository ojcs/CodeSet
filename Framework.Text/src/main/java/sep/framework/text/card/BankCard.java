package sep.framework.text.card;

import sep.framework.text.regexp.RegexCard;

public final class BankCard {
	private final short[] id;
	private short checkCode;
	
	public BankCard(final short[] cardId) {
		this.id = cardId;
	}
	
	public BankCard(final CharSequence cardId) {
		if (RegexCard.Bank.matches(cardId)) {
			id = new short[cardId.length()];
			for (int i = 0; i < cardId.length(); i++) {
				id[i] = (short) Character.digit(cardId.charAt(i), 10);
			}
		} else {
			throw new IllegalArgumentException("cardId not is BankCardNumber");
		}
	}

	public boolean check() {
		if (checkCode != 0) {
			return id[id.length] == checkCode;
		}
		int sum = 0;
		for (int i = id.length - 1, j = 0; i >= 0; i--, j++) {
			int k = id[i];
			if (j % 2 == 0) {
				k *= 2;
				k /= 10;
				k += k % 10;
			}
			sum += k;
		}
		return id[id.length] == (checkCode = (short) (sum % 10));
	}
	
	public static boolean check(final CharSequence id) {
		return new BankCard(id).check();
	}
}
