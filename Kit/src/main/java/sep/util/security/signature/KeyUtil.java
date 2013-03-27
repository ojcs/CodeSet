package sep.util.security.signature;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.KeyAgreement;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class KeyUtil {
	public static final SecretKey genKey(final String algorithm, final byte[] rawKey) throws InvalidKeySpecException, NoSuchAlgorithmException  {
		return new SecretKeySpec(rawKey, algorithm);
	}
	
	public static final SecretKey genKey(final String algorithm, final String secertAlgorithm, final PublicKey publicKey, final PrivateKey privatekey) throws InvalidKeySpecException, NoSuchAlgorithmException, InvalidKeyException, IllegalStateException  {
		KeyAgreement agreement = KeyAgreement.getInstance(algorithm);
		agreement.init(privatekey);
		agreement.doPhase(publicKey, true);
		return agreement.generateSecret(secertAlgorithm);
	}
	
	private KeyUtil() {
	}
}
