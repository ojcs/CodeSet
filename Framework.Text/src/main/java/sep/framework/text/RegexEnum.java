package sep.framework.text;

import java.util.regex.Pattern;

public interface RegexEnum {
	public String pattern();
	
	public Pattern compile();
	
	public Pattern compile(int flags);
	
	public boolean matches(final CharSequence string);
}
