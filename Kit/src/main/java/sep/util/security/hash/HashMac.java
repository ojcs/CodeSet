package sep.util.security.hash;

import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.Provider;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Mac;

import sep.util.security.Update;

public final class HashMac implements Update<byte[]> {
	private final Mac mac;
	
	public HashMac(String algorithm)
			throws GeneralSecurityException {
		mac = Mac.getInstance(algorithm);
	}
	
	public HashMac(String algorithm, Key key)
			throws GeneralSecurityException {
		mac = Mac.getInstance(algorithm);
		mac.init(key);
	}
	
	public HashMac(String algorithm, Key key, AlgorithmParameterSpec spec)
			throws GeneralSecurityException {
		mac = Mac.getInstance(algorithm);
		mac.init(key, spec);
	}
	
	public HashMac(String algorithm, Provider provider)
			throws GeneralSecurityException {
		mac = Mac.getInstance(algorithm, provider);
	}
	
	public HashMac(String algorithm, Provider provider, Key key)
			throws GeneralSecurityException {
		mac = Mac.getInstance(algorithm, provider);
		mac.init(key);
	}
	
	public HashMac(String algorithm, Provider provider, Key key, AlgorithmParameterSpec spec)
			throws GeneralSecurityException {
		mac = Mac.getInstance(algorithm, provider);
		mac.init(key, spec);
	}
	
	@Override
	public byte[] doFinal() {
		return mac.doFinal();
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
