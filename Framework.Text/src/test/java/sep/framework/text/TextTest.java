package sep.framework.text;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static sep.framework.text.Text.isPalindrome;
import static sep.framework.text.Text.limit;

import org.junit.Test;

public class TextTest {
	@Test
	public void testLimit() {
		assertThat("Hello World!", equalTo(limit("Hello World!", 12, "...")));
		assertThat("Hello...", equalTo(limit("Hello World!", 5, "...")));
		
		assertThat("哎，巴扎嘿！", equalTo(limit("哎，巴扎嘿！", 12, "...")));
		assertThat("哎...", equalTo(limit("哎，巴扎嘿！", 1, "...")));		
		assertThat("哎，巴...", equalTo(limit("哎，巴扎嘿！", 3, "...")));
	}
	
	@Test
	public void testIsPalindromeTrue() {
		assertThat(true, equalTo(isPalindrome("tsssst")));
		assertThat(true, equalTo(isPalindrome("111222111")));
		assertThat(true, equalTo(isPalindrome("555666555")));
		assertThat(true, equalTo(isPalindrome(111222111)));
		assertThat(true, equalTo(isPalindrome(555666555)));
	}
	
	@Test
	public void testIsPalindromeFalse() {
		assertThat(false, equalTo(isPalindrome("teest")));
	}
}
