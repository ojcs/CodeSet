package sep.util.security.signature;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

public class CertificateCipher extends PublicKeyCipher {
	public CertificateCipher(String keyAlgorithm, Path keyStorePath,
			String keyStore, char[] keyPassword, String keyAlias,
			final File certificatePath, final String certificateType)
			throws GeneralSecurityException, IOException {
		super(keyAlgorithm, keyAlgorithm, getCertificate(certificatePath,
				certificateType).getPublicKey(), (PrivateKey) getKeyStore(
				keyStorePath, keyStore, keyPassword).getKey(keyAlias,
				keyPassword));
	}

	private static KeyStore getKeyStore(final Path keyStorePath, final String keyStore, final char[] password) throws IOException, GeneralSecurityException  {
		KeyStore store;
		try (InputStream stream = new FileInputStream(keyStorePath.toFile())) {
			store = KeyStore.getInstance(keyStore);
			store.load(stream, password);
		}
		return store;
	}
	
	private static Certificate getCertificate(final File certificatePath, final String factoryType) throws IOException, CertificateException {
		Certificate certificate;
		try (InputStream in = new FileInputStream(certificatePath)) {
			certificate = CertificateFactory.getInstance(factoryType).generateCertificate(in);
		}
		return certificate;
    }  
}
