package sep.util.security.hash;

import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.Provider;

import sep.util.security.Update;

public final class HashMessageDigest implements Update<byte[]> {
	private final MessageDigest digest;
	
	public HashMessageDigest(String algorithm)
			throws GeneralSecurityException {
		digest = MessageDigest.getInstance(algorithm);
	}
	
	public HashMessageDigest(String algorithm, Provider provider)
			throws GeneralSecurityException {
		digest = MessageDigest.getInstance(algorithm, provider);
	}
	
	@Override
	public byte[] doFinal() {
		return digest.digest();
	}

	@Override
	public void update(byte input) {
		digest.update(input);
	}

	@Override
	public void update(byte[] input) {
		digest.update(input);
	}

	@Override
	public void update(byte[] input, int offset, int len) {
		digest.update(input, offset, len);
	}

	@Override
	public void update(ByteBuffer input) {
		digest.update(input);
	}
}
