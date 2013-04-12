package sep.util.security.check;

import java.nio.ByteBuffer;

import sep.util.security.Update;

public class Checksum implements Update<Long> {
	private final java.util.zip.Checksum checksum;
	
	public Checksum(java.util.zip.Checksum checksum) {
		this.checksum = checksum;
	}
	
	@Override
	public void update(byte input) {
		checksum.update(input);
	}

	@Override
	public void update(byte[] input, int offset, int len) {
		checksum.update(input, offset, len);
	}

	@Override
	public void update(byte[] input) {
		checksum.update(input, 0, input.length);
	}

	@Override
	public void update(ByteBuffer input) {
		update(input.array());
	}

	@Override
	public Long doFinal() {
		return checksum.getValue();
	}
}
