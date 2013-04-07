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
	
	public final byte[] cipher(final byte... data) throws IllegalBlockSizeException, BadPaddingException {
		return cipher.doFinal(data);
	}
	
	public final byte[] cipher(final InputStream stream) throws IOException, IllegalBlockSizeException, BadPaddingException {
		byte[] buffer = new byte[8192];
		int offset = 0;
		try (InputStream input = stream) {
			while ((offset = input.read(buffer)) != -1) {
				cipher.update(buffer, 0, offset);
			}
		}
		return cipher.doFinal();
	}
	
	public final Key genKey(final byte... rawKey) throws InvalidKeySpecException, NoSuchAlgorithmException {
		return KeyUtil.genKey(getAlgorithm(), rawKey);
	}
	
	public final String getAlgorithm() {
		return cipher.getAlgorithm();
	}
	
	public final void init(final CipherMode mode, final Key key) throws InvalidKeyException {
		cipher.init(mode.getFlag(), key);
	}
	
	public final void init(final CipherMode mode, final Key key, final AlgorithmParameters algorithmParameters) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
		cipher.init(mode.getFlag(), key, algorithmParameters);
	}

	public final void init(final CipherMode mode, final Key key, final AlgorithmParameters parameters, final SecureRandom random) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
		cipher.init(mode.getFlag(), key, parameters, random);
	}
	
	public final void init(final CipherMode mode, final Key key, final AlgorithmParameterSpec algorithmParameters) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
		cipher.init(mode.getFlag(), key, algorithmParameters);
	}
	
	public final void init(final CipherMode mode, final Key key, final AlgorithmParameterSpec parameterSpec, final SecureRandom random) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
		cipher.init(mode.getFlag(), key, parameterSpec, random);
	}
	
	public final void init(final CipherMode mode, final Key key, final SecureRandom random) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
		cipher.init(mode.getFlag(), key, random);
	}
}
