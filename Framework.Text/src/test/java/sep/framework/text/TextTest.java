package sep.framework.text;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class TextTest {
	@Test
	public void testIsPalindromeTrue() {
		assertThat(true, equalTo(Text.isPalindrome("tsssst")));
		assertThat(true, equalTo(Text.isPalindrome("111222111")));
		assertThat(true, equalTo(Text.isPalindrome("555666555")));
		assertThat(true, equalTo(Text.isPalindrome(111222111)));
		assertThat(true, equalTo(Text.isPalindrome(555666555)));
	}
	
	@Test
	public void testIsPalindromeFalse() {
		assertThat(false, equalTo(Text.isPalindrome("teest")));
	}
}
