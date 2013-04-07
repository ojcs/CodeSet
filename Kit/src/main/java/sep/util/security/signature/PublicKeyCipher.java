package sep.util.security.signature;

import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;

public class PublicKeyCipher {
	protected final String algorithm;
	protected final PrivateKey privateKey;
	protected final PublicKey publicKey;

	public PublicKeyCipher(String algorithm, PublicKey publicKey, PrivateKey privateKey) throws GeneralSecurityException {
		this.algorithm = algorithm;
		this.publicKey = publicKey;
		this.privateKey = privateKey;
	}

	public final Signature buildSign() throws GeneralSecurityException {
		Signature signature = Signature.getInstance(algorithm);
		signature.initSign(privateKey);
		return signature;
	}

	public final Signature buildVerify() throws GeneralSecurityException {
		Signature signature = Signature.getInstance(algorithm);
		signature.initVerify(publicKey);
		return signature;
	}

	/** 生成签名 */
	public byte[] sign(final byte... data) throws GeneralSecurityException {
		Signature signature = Signature.getInstance(algorithm);
		signature.initSign(privateKey);
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

	/** 校验签名 */
	public boolean verify(ByteBuffer data, byte[] sign)
			throws GeneralSecurityException {
		Signature signature = buildVerify();
		signature.update(data);
		return signature.verify(sign);
	}
}
