package sep.util.security.signature;

import javax.crypto.Cipher;

public enum CipherMode {
	/** decryption mode */
	Decrypt(Cipher.ENCRYPT_MODE),
	/** encryption mode */
	Encrypt(Cipher.ENCRYPT_MODE),
	/** key-unwrapping mode */
	UnWrap(Cipher.UNWRAP_MODE),
	/** key-wrapping mode */
	Wrap(Cipher.WRAP_MODE);
	private final int mode;

	private CipherMode(final int mode) {
		this.mode = mode;
	}

	int getFlag() {
		return mode;
	}
}
