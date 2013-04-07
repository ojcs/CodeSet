package sep.util.security;

import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.MessageDigest;

import javax.crypto.Mac;

public final class DigitalUtil {
	public static Mac mac(String algorithm) throws GeneralSecurityException {
		return Mac.getInstance(algorithm);
	}

	public static Mac mac(Key key) throws GeneralSecurityException {
		Mac mac = Mac.getInstance(key.getAlgorithm());
		mac.init(key);
		return mac;
	}

	public static MessageDigest message(final String algorithm)
			throws GeneralSecurityException {
		return MessageDigest.getInstance(algorithm);
	}

	private DigitalUtil() {
	}
}
