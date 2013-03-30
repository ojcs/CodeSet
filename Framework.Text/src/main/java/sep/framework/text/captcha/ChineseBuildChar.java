package sep.framework.text.captcha;

import sep.util.Maths;

public final class ChineseBuildChar implements BuildChar {
	private static char[] captcha = new char[4];
	
	@Override
	public char[] build(int length) {
		if (captcha.length != length) {
			captcha = new char[length];
		}
		for (int i = 0; i < length; i++) {
			captcha[i] = (char) Maths.random(0x53E3, 0x559D);
		}
		return captcha;
	}

	@Override
	public char[] current() {
		return captcha;
	}
}
