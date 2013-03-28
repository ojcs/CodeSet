package sep.framework.text.captcha;

import java.awt.Image;

public interface BuildImage {
	public Image buildImage(char... chars);
	
	public Image current();
}
