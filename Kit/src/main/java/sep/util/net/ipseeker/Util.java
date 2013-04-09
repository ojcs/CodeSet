package sep.util.net.ipseeker;

import java.nio.ByteBuffer;

class Util {
	static final byte RedirectMode1 = 0x01;
	static final byte RedirectMode2 = 0x02;
	static final int RecordLength = 7;

	/** 计算中间位置的偏移 */
	static int calcMiddleOffset(final int begin, final int end) {
		final int records = ((end - begin) / RecordLength) >> 1;
		return begin + ((records == 0) ? 1 : records) * RecordLength;
	}

	static int compare(byte b1, byte b2) {
		if ((b1 & 0xFF) > (b2 & 0xFF)) {
			return 1;
		} else if ((b1 ^ b2) == 0) {
			return 0;
		} else {
			return -1;
		}
	}

	static int compare(byte[] ip, byte[] begin) {
		for (int i = 0, r; i < 4; i++) {
			if ((r = compare(ip[i], begin[i])) != 0) {
				return r;
			}
		}
		return 0;
	}

	static int readInt3(ByteBuffer buffer) {
		return buffer.getInt() & 0x00FFFFFF;
	}

	static int readInt3(ByteBuffer buffer, int offset) {
		buffer.position(offset);
		return readInt3(buffer);
	}
}
