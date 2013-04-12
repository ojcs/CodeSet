package sep.util.security.hash;

import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.Provider;

import sep.util.security.Update;

public final class HashMessageDigest implements Update<byte[]> {
	private final MessageDigest mac;
	
	public HashMessageDigest(String algorithm)
			throws GeneralSecurityException {
		mac = MessageDigest.getInstance(algorithm);
	}
	
	public HashMessageDigest(String algorithm, Provider provider)
			throws GeneralSecurityException {
		mac = MessageDigest.getInstance(algorithm, provider);
	}
	
	@Override
	public byte[] doFinal() {
		return mac.digest();
	}

	@Override
	public void update(byte input) {
		mac.update(input);
	}

	@Override
	public void update(byte[] input) {
		mac.update(input);
	}

	@Override
	public void update(byte[] input, int offset, int len) {
		mac.update(input, offset, len);
	}

	@Override
	public void update(ByteBuffer input) {
		mac.update(input);
	}
}
