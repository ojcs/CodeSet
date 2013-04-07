package sep.util.security.signature;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

public class CertificateCipherBuilder {
	public static Certificate buildCertificate(Path certificatePath, String factoryType) throws CertificateException, IOException {
		try (InputStream in = Files.newInputStream(certificatePath)) {
			return CertificateFactory.getInstance(factoryType).generateCertificate(in);
		}
	}

	public static KeyStore buildKeyStore(Path keyStorePath, String keyStore, char[] password) throws GeneralSecurityException, IOException {
		try (InputStream stream = Files.newInputStream(keyStorePath)) {
			KeyStore store = KeyStore.getInstance(keyStore);
			store.load(stream, password);
			return store;
		}
	}

	private PrivateKey privateKey;

	private PublicKey publicKey;

	private final String signatureAlgorithm;

	public CertificateCipherBuilder(String algorithm) {
		this.signatureAlgorithm = algorithm;
	}

	public PublicKeyCipher buildCipher() throws GeneralSecurityException {
		return new PublicKeyCipher(signatureAlgorithm, publicKey, privateKey);
	}

	public void setPrivateKey(final KeyStore store, char[] keyPassword, String keyAlias) throws GeneralSecurityException, IOException {
		this.privateKey = (PrivateKey) store.getKey(keyAlias, keyPassword);
	}

	public void setPublicKey(final Certificate certificate) throws CertificateException, IOException {
		this.publicKey = certificate.getPublicKey();
	}
}
