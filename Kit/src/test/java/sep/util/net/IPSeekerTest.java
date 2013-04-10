package sep.util.net;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import org.junit.Test;

import sep.util.io.file.FileUtil;
import sep.util.net.ipseeker.IPLocation;
import sep.util.net.ipseeker.IPSeeker;
import sep.util.net.ipseeker.IPSeekerCache;

public class IPSeekerTest {
	private final IPSeeker seeker;
	
	public IPSeekerTest() throws IOException {
		seeker = new IPSeekerCache(FileUtil.classpath("/QQWry.DAT"));
	}
	
	@Test
	public void test$192_168_0_1() {
		IPLocation location = seeker.getLocation((byte) 192, (byte) 168, (byte) 0, (byte) 1);
		assertThat(location.toString(), equalTo("对方和您在同一内部网 局域网"));
	}
	
	@Test
	public void test$192_168_1_1() {
		IPLocation location = seeker.getLocation((byte) 192, (byte) 168, (byte) 1, (byte) 1);
		assertThat(location.toString(), equalTo("对方和您在同一内部网 局域网"));
	}
	
	@Test
	public void test$222_181_111_85() {
		IPLocation location = seeker.getLocation((byte) 220, (byte) 181, (byte) 111, (byte) 85);
		assertThat(location.toString(), equalTo("百度公司 北京市"));
	}
}
