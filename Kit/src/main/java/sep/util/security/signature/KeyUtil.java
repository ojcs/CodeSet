package sep.util.security.signature;

import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.SecureRandom;
import java.security.spec.KeySpec;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.SecretKeySpec;

public final class KeyUtil {
	public static SecretKey genKey(final String algorithm, final byte... rawKey) throws GeneralSecurityException {
		return new SecretKeySpec(rawKey, algorithm);
	}

	public static Key genKey(final String algorithm, KeySpec spec) throws GeneralSecurityException {
		return SecretKeyFactory.getInstance(algorithm).generateSecret(spec);
	}
	
	public static byte[] initKey(final String algorithm, byte... seed) throws GeneralSecurityException {
		KeyGenerator gen = KeyGenerator.getInstance(algorithm);
		gen.init((seed != null) ? new SecureRandom(seed) : new SecureRandom());
		return gen.generateKey().getEncoded();
	}

	private KeyUtil() {
	}
}
