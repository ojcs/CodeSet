package sep.util.php.string;

import sep.util.security.ChecksumUtil;

final class Hash {
	private Hash() {
	}
	
	public static long crc32(final String words) {
		return ChecksumUtil.crc32(words.getBytes());
	}
}
