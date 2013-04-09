package sep.util.security.signature;

import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.KeySpec;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;

public final class KeyUtil {
	public static SecretKey key(String algorithm, AlgorithmParameterSpec spec) throws GeneralSecurityException {
		KeyGenerator generator = KeyGenerator.getInstance(algorithm);
		generator.init(spec);
		return generator.generateKey();
	}
	
	public static SecretKey key(String algorithm, AlgorithmParameterSpec spec, SecureRandom random) throws GeneralSecurityException {
		KeyGenerator generator = KeyGenerator.getInstance(algorithm);
		generator.init(spec, random);
		return generator.generateKey();
	}
	
	public static SecretKey key(String algorithm, int keysize) throws GeneralSecurityException {
		KeyGenerator generator = KeyGenerator.getInstance(algorithm);
		generator.init(keysize);
		return generator.generateKey();
	}

	public static SecretKey key(String algorithm, int keysize, SecureRandom random) throws GeneralSecurityException {
		KeyGenerator generator = KeyGenerator.getInstance(algorithm);
		generator.init(keysize, random);
		return generator.generateKey();
	}
	
	public static SecretKey key(String algorithm, SecureRandom random) throws GeneralSecurityException {
		KeyGenerator generator = KeyGenerator.getInstance(algorithm);
		generator.init(random);
		return generator.generateKey();
	}
	
	public static KeyPair keyPair(String algorithm, AlgorithmParameterSpec spec) throws GeneralSecurityException {
		KeyPairGenerator generator = KeyPairGenerator.getInstance(algorithm);
		generator.initialize(spec);
		return generator.generateKeyPair();
	}
	
	public static KeyPair keyPair(String algorithm, AlgorithmParameterSpec spec, SecureRandom random) throws GeneralSecurityException {
		KeyPairGenerator generator = KeyPairGenerator.getInstance(algorithm);
		generator.initialize(spec, random);
		return generator.generateKeyPair();
	}
	
	public static KeyPair keyPair(String algorithm, int keysize) throws GeneralSecurityException {
		KeyPairGenerator generator = KeyPairGenerator.getInstance(algorithm);
		generator.initialize(keysize);
		return generator.generateKeyPair();
	}
	
	public static KeyPair keyPair(String algorithm, int keysize, SecureRandom random) throws GeneralSecurityException {
		KeyPairGenerator generator = KeyPairGenerator.getInstance(algorithm);
		generator.initialize(keysize, random);
		return generator.generateKeyPair();
	}
	
	public static SecretKey secret(String algorithm, KeySpec spec) throws GeneralSecurityException {
		return SecretKeyFactory.getInstance(algorithm).generateSecret(spec);
	}
	
	private KeyUtil() {
	}
}
