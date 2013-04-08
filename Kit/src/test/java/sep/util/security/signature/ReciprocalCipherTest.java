package sep.util.security.signature;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static sep.util.security.signature.KeyUtil.key;

import java.security.GeneralSecurityException;

import org.junit.Test;

public class ReciprocalCipherTest {
	@Test
	public void test() throws GeneralSecurityException {
		test("AES", 128, "AES/ECB/PKCS5Padding");
		test("DES", 56, "DES/ECB/PKCS5Padding");
		test("DESede", 168, "DESede/ECB/PKCS5Padding");
	}
	
	public void test(String algorithm, int keysize, String transformation) throws GeneralSecurityException {
		ReciprocalCipher cipher = new ReciprocalCipher(key(algorithm, keysize), transformation);
		byte[] ref = "Test".getBytes();
		byte[] encrypt = cipher.encrypt(ref);
		byte[] decrypt = cipher.decrypt(encrypt);
		assertThat(ref, equalTo(decrypt));
	}
}
