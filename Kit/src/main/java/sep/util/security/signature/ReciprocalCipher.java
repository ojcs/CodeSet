package sep.util.security.signature;

import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.Provider;

import javax.crypto.Cipher;

public class ReciprocalCipher {
	protected final Provider provider;
	protected final String transformation;
	protected final Key key;

	protected ReciprocalCipher(Key key, String transformation)
			throws GeneralSecurityException {
		this(key, transformation, null);
	}

	protected ReciprocalCipher(Key key, String transformation, Provider provider)
			throws GeneralSecurityException {
		this.key = key;
		this.transformation = transformation;
		this.provider = provider;
	}
	
	protected final String getAlgorithm() {
		return transformation;
	}

	protected final Cipher getCipher() throws GeneralSecurityException {
		if (provider == null) {
			return Cipher.getInstance(transformation);
		} else {
			return Cipher.getInstance(transformation, provider);
		}
	}

	/** 解密 */
	public byte[] decrypt(byte... input) throws GeneralSecurityException {
		Cipher cipher = getCipher();
		cipher.init(Cipher.DECRYPT_MODE, key);
		return cipher.doFinal(input);
	}

	/** 加密 */
	public byte[] encrypt(byte... input) throws GeneralSecurityException {
		Cipher cipher = getCipher();
		cipher.init(Cipher.ENCRYPT_MODE, key);
		return cipher.doFinal(input);
	}
}
