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

public final class CertificateCipherBuilder {
	public static Certificate buildCertificate(Path certificatePath,
			String factoryType) throws CertificateException, IOException {
		try (InputStream in = Files.newInputStream(certificatePath)) {
			return CertificateFactory.getInstance(factoryType)
					.generateCertificate(in);
		}
	}

	public static KeyStore buildKeyStore(Path keyStorePath, String keyStore,
			char[] password) throws GeneralSecurityException, IOException {
		try (InputStream stream = Files.newInputStream(keyStorePath)) {
			KeyStore store = KeyStore.getInstance(keyStore);
			store.load(stream, password);
			return store;
		}
	}

	private final String algorithm;

	private PrivateKey privateKey;

	private PublicKey publicKey;

	public CertificateCipherBuilder(String algorithm) {
		this.algorithm = algorithm;
	}

	public PublicKeyCipher build() throws GeneralSecurityException {
		return new PublicKeyCipher(algorithm, publicKey, privateKey);
	}

	public void setPrivateKey(KeyStore store, char[] password, String alias) throws GeneralSecurityException, IOException {
		this.privateKey = (PrivateKey) store.getKey(alias, password);
	}

	public void setPublicKey(Certificate certificate)
			throws CertificateException, IOException {
		this.publicKey = certificate.getPublicKey();
	}
}
