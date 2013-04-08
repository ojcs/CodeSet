package sep.util.security.signature;

import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.Signature;

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

	public final Signature buildSign() throws GeneralSecurityException {
		Signature signature = Signature.getInstance(algorithm);
		signature.initSign(keyPair.getPrivate());
		return signature;
	}

	public final Signature buildVerify() throws GeneralSecurityException {
		Signature signature = Signature.getInstance(algorithm);
		signature.initVerify(keyPair.getPublic());
		return signature;
	}

	/** 生成签名 */
	public byte[] sign(final byte... data) throws GeneralSecurityException {
		Signature signature = buildSign();
		signature.update(data);
		return signature.sign();
	}
	
	/** 校验签名 */
	public boolean verify(byte[] data, byte[] sign)
			throws GeneralSecurityException {
		Signature signature = buildVerify();
		signature.update(data);
		return signature.verify(sign);
	}
}
