package sep.framework.text.captcha;

import sep.util.Random;

public final class ChineseBuildChar implements BuildChar {
	private char[] captcha = new char[4];
	
	@Override
	public char[] build(int length) {
		Random random = new Random();
		if (captcha.length != length) {
			captcha = new char[length];
		}
		for (int i = 0; i < length; i++) {
			captcha[i] = (char) random.nextInt(0x53E3, 0x559D);
		}
		return captcha;
	}

	@Override
	public char[] current() {
		return captcha;
	}
}
