package sep.util.security.signature;

import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;

public class PublicKeyCipher extends ReciprocalCipher {
	private final PublicKey publicKey;
	private final PrivateKey privateKey;
	private final String signatureAlgorithm;
	
	public PublicKeyCipher(final String keyAlgorithm, final String signatureAlgorithm, final PublicKey publicKey, final PrivateKey privateKey) throws GeneralSecurityException {
		super(keyAlgorithm);
		this.signatureAlgorithm = signatureAlgorithm;
		this.publicKey = publicKey;
		this.privateKey = privateKey;
	}
	
	/**
	 * 用私钥对信息生成数字签名
	 */
	public byte[] sign(final byte[] data) throws GeneralSecurityException {
		Signature signature = Signature.getInstance(signatureAlgorithm);
		signature.initSign(privateKey);
		signature.update(data);
		return signature.sign();
	}
	
	public byte[] sign(final InputStream stream) throws IOException, GeneralSecurityException {
		Signature signature = Signature.getInstance(signatureAlgorithm);
		signature.initSign(privateKey);
		signatureStream(signature, stream);
		return signature.sign();
	}
	
	/**
	 * 校验数字签名
	 */
    public boolean verify(byte[] data, byte[] sign) throws GeneralSecurityException {
		Signature signature = Signature.getInstance(signatureAlgorithm);
		signature.initVerify(publicKey);
		signature.update(data);
		return signature.verify(sign);
    }
    
    public boolean verify(final InputStream stream, byte[] sign) throws IOException, GeneralSecurityException {
		Signature signature = Signature.getInstance(signatureAlgorithm);
		signature.initVerify(publicKey);
		signatureStream(signature, stream);
		return signature.verify(sign);
    }
    
    private void signatureStream(final Signature signature, final InputStream stream) throws SignatureException, IOException   {
		byte[] buffer = new byte[8192];
		int offset = 0;
		try (InputStream input = stream) {
			while ((offset = input.read(buffer)) != -1) {
				signature.update(buffer, 0, offset);
			}
		}
	}
    
    public PrivateKey getPrivateKey() {
		return privateKey;
	}
    
    public PublicKey getPublicKey() {
		return publicKey;
	}
    
    public String getSignatureAlgorithm() {
		return signatureAlgorithm;
	}
}
