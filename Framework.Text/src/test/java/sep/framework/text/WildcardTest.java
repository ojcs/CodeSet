package sep.framework.text;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class WildcardTest {
	@Test
	public void testMatches() {
		assertThat(true, equalTo(new Wildcard("te\\?t").matches("te?t")));
		assertThat(true, equalTo(new Wildcard("te*t").matches("tessssssst")));
		assertThat(true, equalTo(new Wildcard("text*").matches("textssssss")));
		assertThat(true, equalTo(new Wildcard("*text").matches("sssssssstext")));
	}
}
