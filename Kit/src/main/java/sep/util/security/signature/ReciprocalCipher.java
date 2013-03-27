package sep.util.security.signature;

import java.io.IOException;
import java.io.InputStream;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class ReciprocalCipher {
	private final Cipher cipher;
	
	public ReciprocalCipher(final String algorithm) throws NoSuchAlgorithmException, NoSuchPaddingException {
		this.cipher = Cipher.getInstance(algorithm);
	}
	
	public ReciprocalCipher(final String algorithm, final Provider provider) throws NoSuchAlgorithmException, NoSuchPaddingException {
		this.cipher = Cipher.getInstance(algorithm, provider);
	}
	
	public final void decrypt(final Key key) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		cipher.init(Cipher.DECRYPT_MODE, key);
	}
	
	public final void decrypt(final Key key, final AlgorithmParameters algorithmParameters) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
		cipher.init(Cipher.DECRYPT_MODE, key, algorithmParameters);
	}
	
	public final void decrypt(final Key key, final AlgorithmParameterSpec algorithmParameters) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
		cipher.init(Cipher.DECRYPT_MODE, key, algorithmParameters);
	}
	
	public final void decrypt(final Key key, final SecureRandom random) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
		cipher.init(Cipher.DECRYPT_MODE, key, random);
	}
	
	public final void decrypt(final Key key, final AlgorithmParameters parameters, final SecureRandom random) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
		cipher.init(Cipher.DECRYPT_MODE, key, parameters, random);
	}
	
	public final void decrypt(final Key key, final AlgorithmParameterSpec parameterSpec, final SecureRandom random) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
		cipher.init(Cipher.DECRYPT_MODE, key, parameterSpec, random);
	}

	public final void encrypt(final Key key) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException{
		cipher.init(Cipher.ENCRYPT_MODE, key);
	}
	
	public final void encrypt(final Key key, final AlgorithmParameters algorithmParameters) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
		cipher.init(Cipher.ENCRYPT_MODE, key, algorithmParameters);
	}
	
	public final void encrypt(final Key key, final AlgorithmParameterSpec algorithmParameters) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
		cipher.init(Cipher.ENCRYPT_MODE, key, algorithmParameters);
	}
	
	public final void encrypt(final Key key, final SecureRandom random) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
		cipher.init(Cipher.ENCRYPT_MODE, key, random);
	}
	
	public final void encrypt(final Key key, final AlgorithmParameters parameters, final SecureRandom random) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
		cipher.init(Cipher.ENCRYPT_MODE, key, parameters, random);
	}
	
	public final void encrypt(final Key key, final AlgorithmParameterSpec parameterSpec, final SecureRandom random) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
		cipher.init(Cipher.ENCRYPT_MODE, key, parameterSpec, random);
	}
	
	public final byte[] cipherBytes(final byte[] data) throws IllegalBlockSizeException, BadPaddingException {
		return cipher.doFinal(data);
	}
	
	public final byte[] cipherStream(final InputStream stream) throws IOException, IllegalBlockSizeException, BadPaddingException {
		byte[] buffer = new byte[8192];
		int offset = 0;
		try (InputStream input = stream) {
			while ((offset = input.read(buffer)) != -1) {
				cipher.update(buffer, 0, offset);
			}
		}
		return cipher.doFinal();
	}
	
	public final String getAlgorithm() {
		return cipher.getAlgorithm();
	}
	
	public final Key genKey(final byte[] rawKey) throws InvalidKeySpecException, NoSuchAlgorithmException {
		return KeyUtil.genKey(getAlgorithm(), rawKey);
	}
}
