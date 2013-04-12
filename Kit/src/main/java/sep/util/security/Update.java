package sep.util.security;

import java.nio.ByteBuffer;

public interface Update<R> {
	void update(byte input);

	void update(byte[] input, int offset, int len);

	void update(byte[] input);

	void update(ByteBuffer input);

	R doFinal();
}
