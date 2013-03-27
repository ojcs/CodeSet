package sep.framework.text;

public final class BankCard {
	private final short[] cardId;
	
	public BankCard(final short[] cardId) {
		this.cardId = cardId;
	}
	
	public BankCard(final String cardId) {
		if (cardId.matches(RegexCard.Bank.pattern())) {
			final char[] oldCardId = cardId.toCharArray();
			this.cardId = new short[oldCardId.length];
			for (int i = 0; i < oldCardId.length; i++) {
				this.cardId[i] = (short) Character.digit(oldCardId[i], 10);
			}
		} else {
			throw new IllegalArgumentException("cardId not is BankCardNumber");
		}
	}

	public boolean check() {
		int sum = 0;
		for (int i = cardId.length - 1, j = 0; i >= 0; i--, j++) {
			int k = cardId[i];
			if (j % 2 == 0) {
				k *= 2;
				k = k / 10 + k % 10;
			}
			sum += k;
		}
		final int checkCode = sum % 10;
		return cardId[cardId.length] == checkCode;
	}
	
	public static boolean check(final String cardId) {
		return new BankCard(cardId).check();
	}
	
	@Override
	public int hashCode() {
		return cardId.hashCode();
	}
	
	@Override
	public String toString() {
		return String.valueOf(cardId);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof BankCard) {
			return cardId.equals(((BankCard) obj).cardId);
		} else {
			return false;
		}
	}
}
