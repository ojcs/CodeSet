package sep.util.security.signature;

import static javax.crypto.Cipher.DECRYPT_MODE;
import static javax.crypto.Cipher.ENCRYPT_MODE;

import java.nio.ByteBuffer;
import java.security.AlgorithmParameters;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;

/**
 * <a href=
 * "http://docs.oracle.com/javase/7/docs/technotes/guides/security/StandardNames.html"
 * >Java™ Cryptography Architecture Standard Algorithm Name Documentation</a>
 * 
 * @see Cipher
 */
public class ReciprocalCipher {
	protected final Key key;
	protected final Provider provider;
	protected final String transformation;

	public ReciprocalCipher(Key key, String transformation)
			throws GeneralSecurityException {
		this(key, transformation, null);
	}

	public ReciprocalCipher(Key key, String transformation, Provider provider)
			throws GeneralSecurityException {
		this.key = key;
		this.transformation = transformation;
		this.provider = provider;
	}

	public final Cipher buildCipher(int opmode, Certificate certificate)
			throws GeneralSecurityException {
		Cipher cipher = getCipher();
		cipher.init(opmode, certificate);
		return cipher;
	}

	public final Cipher buildCipher(int opmode, Certificate certificate, SecureRandom random)
			throws GeneralSecurityException {
		Cipher cipher = getCipher();
		cipher.init(opmode, certificate, random);
		return cipher;
	}

	public final Cipher buildCipher(int opmode, Key key)
			throws GeneralSecurityException {
		Cipher cipher = getCipher();
		cipher.init(opmode, key);
		return cipher;
	}

	public final Cipher buildCipher(int opmode, Key key, AlgorithmParameters parameters)
			throws GeneralSecurityException {
		Cipher cipher = getCipher();
		cipher.init(opmode, key, parameters);
		return cipher;
	}

	public final Cipher buildCipher(int opmode, Key key, AlgorithmParameters parameters, SecureRandom random)
			throws GeneralSecurityException {
		Cipher cipher = getCipher();
		cipher.init(opmode, key, parameters, random);
		return cipher;
	}

	public final Cipher buildCipher(int opmode, Key key, AlgorithmParameterSpec spec)
			throws GeneralSecurityException {
		Cipher cipher = getCipher();
		cipher.init(opmode, key, spec);
		return cipher;
	}

	public final Cipher buildCipher(int opmode, Key key, AlgorithmParameterSpec spec, SecureRandom random)
			throws GeneralSecurityException {
		Cipher cipher = getCipher();
		cipher.init(opmode, key, spec, random);
		return cipher;
	}

	/** 解密 */
	public byte[] decrypt(byte... input) throws GeneralSecurityException {
		return buildCipher(DECRYPT_MODE, key).doFinal(input);
	}

	/** 解密 */
	public byte[] decrypt(ByteBuffer input) throws GeneralSecurityException {
		return buildCipher(DECRYPT_MODE, key).doFinal(input.array());
	}

	/** 加密 */
	public byte[] encrypt(byte... input) throws GeneralSecurityException {
		return buildCipher(ENCRYPT_MODE, key).doFinal(input);
	}

	/** 加密 */
	public byte[] encrypt(ByteBuffer input) throws GeneralSecurityException {
		return buildCipher(ENCRYPT_MODE, key).doFinal(input.array());
	}

	private final Cipher getCipher() throws GeneralSecurityException {
		if (provider == null) {
			return Cipher.getInstance(transformation);
		} else {
			return Cipher.getInstance(transformation, provider);
		}
	}

	public final String getTransformation() {
		return transformation;
	}
}
