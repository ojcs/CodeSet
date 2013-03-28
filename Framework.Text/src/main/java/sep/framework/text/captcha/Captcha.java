package sep.framework.text.captcha;

import java.awt.Image;

public abstract class Captcha {
	protected final BuildChar buildChar;
	protected final BuildImage buildImage;
	protected final short length;

	public Captcha(short length, BuildChar buildChar, BuildImage buildImage) {
		this.length = length;
		this.buildChar = buildChar;
		this.buildImage = buildImage;
		buildChar();
	}

	public char[] buildChar() {
		return buildChar.build(length);
	}

	public Image buildImage() {
		return buildImage.buildImage(currentCaptcha());
	}

	public char[] currentCaptcha() {
		return buildChar.current();
	}

	public Image currentImage() {
		return buildImage.current();
	}
}
