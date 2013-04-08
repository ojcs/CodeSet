package sep.util.security.signature;

import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.cert.Certificate;

/**
 * @see Signature
 */
public class PublicKeyCipher {
	protected final String algorithm;
	protected final KeyPair keyPair;

	/**
	 * @param algorithm SHA1withDSA | SHA1withRSA | SHA256withRSA
	 */
	public PublicKeyCipher(String algorithm, KeyPair keyPair) {
		this.algorithm = algorithm;
		this.keyPair = keyPair;
	}

	public final Signature buildSign(PrivateKey key) throws GeneralSecurityException {
		Signature signature = Signature.getInstance(algorithm);
		signature.initSign(key);
		return signature;
	}
	
	public final Signature buildSign(PrivateKey key, SecureRandom random) throws GeneralSecurityException {
		Signature signature = Signature.getInstance(algorithm);
		signature.initSign(key, random);
		return signature;
	}

	public final Signature buildVerify(Certificate certificate) throws GeneralSecurityException {
		Signature signature = Signature.getInstance(algorithm);
		signature.initVerify(certificate);
		return signature;
	}
	
	public final Signature buildVerify(PublicKey key) throws GeneralSecurityException {
		Signature signature = Signature.getInstance(algorithm);
		signature.initVerify(key);
		return signature;
	}

	/** 生成 */
	public byte[] sign(final byte[] data) throws GeneralSecurityException {
		Signature signature = buildSign(keyPair.getPrivate());
		signature.update(data);
		return signature.sign();
	}
	
	/** 校验 */
	public boolean verify(byte[] data, byte[] sign)
			throws GeneralSecurityException {
		Signature signature = buildVerify(keyPair.getPublic());
		signature.update(data);
		return signature.verify(sign);
	}
}
