package sep.framework.text.captcha;

public interface BuildChar {
	char[] build(int length);
	
	char[] current();
}
