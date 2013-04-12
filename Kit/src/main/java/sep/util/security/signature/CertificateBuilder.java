package sep.util.security.signature;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Date;

public final class CertificateBuilder {
	public static Certificate buildCertificate(Path path, String type)
			throws CertificateException, IOException {
		try (InputStream in = Files.newInputStream(path)) {
			return CertificateFactory.getInstance(type).generateCertificate(in);
		}
	}

	public static KeyStore buildKeyStore(Path path, String type, char[] password)
			throws GeneralSecurityException, IOException {
		KeyStore store;
		try (InputStream stream = Files.newInputStream(path)) {
			store = KeyStore.getInstance(type);
			store.load(stream, password);
		}
		return store;
	}

	public static boolean checkValidity(Certificate certificate, Date date)
			throws CertificateException {
		if (certificate instanceof X509Certificate) {
			try {
				((X509Certificate) certificate).checkValidity(date);
			} catch (CertificateExpiredException e) {
				return false;
			}
			return true;
		}
		return false;
	}

	private final String algorithm;
	private PrivateKey privateKey;
	private PublicKey publicKey;

	public CertificateBuilder(String algorithm) {
		this.algorithm = algorithm;
	}

	public PublicKeyCipher build() throws GeneralSecurityException {
		return new PublicKeyCipher(algorithm, new KeyPair(publicKey, privateKey));
	}

	public void setPrivateKey(KeyStore store, char[] password, String alias) throws GeneralSecurityException, IOException {
		this.privateKey = (PrivateKey) store.getKey(alias, password);
	}
	
	public void setPublicKey(Certificate certificate)
			throws CertificateException, IOException {
		this.publicKey = certificate.getPublicKey();
	}
}
