package sep.util.security.signature;

import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.KeyAgreement;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public final class KeyUtil {
	public static KeyPair keyPair(String algorithm, AlgorithmParameterSpec spec) throws GeneralSecurityException {
		KeyPairGenerator generator = KeyPairGenerator.getInstance(algorithm);
		generator.initialize(spec);
		return generator.generateKeyPair();
	}

	public static KeyPair keyPair(String algorithm, int keysize) throws GeneralSecurityException {
		KeyPairGenerator generator = KeyPairGenerator.getInstance(algorithm);
		generator.initialize(keysize);
		return generator.generateKeyPair();
	}
	
	public static SecretKey pbe(String algorithm, char... password) throws GeneralSecurityException {
		if (algorithm.contains("PBE")) {
			return secret(algorithm, new PBEKeySpec(password));
		} else {
			throw new IllegalArgumentException("not is PBE algorithm");
		}
	}
	
	public static SecretKey pbe(String algorithm, CharSequence password) throws GeneralSecurityException {
		return pbe(algorithm, password.toString().toCharArray());
	}
	
	public static SecretKey secret(String algorithm, byte... seed) throws GeneralSecurityException {
		KeyGenerator gen = KeyGenerator.getInstance(algorithm);
		gen.init((seed != null) ? new SecureRandom(seed) : new SecureRandom());
		return gen.generateKey();
	}
	
	public static SecretKey secret(String algorithm, KeySpec spec) throws GeneralSecurityException {
		return SecretKeyFactory.getInstance(algorithm).generateSecret(spec);
	}
	
	public static SecretKey secret(String algorithm, String secretAlgorithm, boolean lastPhase, KeySpec publicSpec, KeySpec privateSpec) throws GeneralSecurityException {
		KeyFactory factory = KeyFactory.getInstance(algorithm);
		KeyAgreement agreement = KeyAgreement.getInstance(algorithm);
		agreement.init(factory.generatePrivate(privateSpec));
		agreement.doPhase(factory.generatePublic(publicSpec), lastPhase);
		return agreement.generateSecret(secretAlgorithm);
	}
	
	/**
	 * @Ref http://snowolf.iteye.com/blog/382422
	 */
	public static SecretKey secretDH(String algorithm, String secretAlgorithm, KeyPair keyPair) throws GeneralSecurityException {
		final KeySpec publicSpec = new X509EncodedKeySpec(keyPair.getPublic().getEncoded());
		final KeySpec privateSpec = new PKCS8EncodedKeySpec(keyPair.getPrivate().getEncoded());
		return secret(algorithm, secretAlgorithm, true, publicSpec, privateSpec);
	}
	
	public static SecretKey spec(String algorithm, byte... rawKey) throws GeneralSecurityException {
		return new SecretKeySpec(rawKey, algorithm);
	}

	private KeyUtil() {
	}
}
