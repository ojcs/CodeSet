package sep.util.security;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.DigestInputStream;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;

import javax.crypto.Mac;

import sep.util.io.stream.StreamConvert;
import sep.util.io.stream.StreamUtil;
import sep.util.security.algorithm.MacAlgorithm;
import sep.util.security.algorithm.MessageDigestAlgorithm;
import sep.util.security.algorithm.SignatureAlgorithm;

public final class DigitalUtil {
	public static OutputStream digest(final DigestInputStream inputStream) throws IOException {
		final OutputStream outputStream = new ByteArrayOutputStream();
		StreamConvert.convert(inputStream, outputStream, true, StreamUtil.BUFFER_SIZE);
		return outputStream;
	}
	
	public static Mac hash(final MacAlgorithm algorithm) throws NoSuchAlgorithmException {
		return Mac.getInstance(algorithm.name());
	}
	
	public static Mac hash(final Key key) throws NoSuchAlgorithmException, InvalidKeyException {
		Mac mac = hash(MacAlgorithm.valueOf(key.getAlgorithm()));
		mac.init(key);
		return mac;
	}
	
	public static MessageDigest hash(final MessageDigestAlgorithm algorithm) throws NoSuchAlgorithmException {
		return MessageDigest.getInstance(algorithm.name());
	}
	
	public static byte[] hashStream(final InputStream stream, final Mac mac) throws IOException {
		byte[] buffer = new byte[8192];
		int offset = 0;
		try (final InputStream input = stream) {
			while ((offset = input.read(buffer)) != -1) {
				mac.update(buffer, 0, offset);
			}
		}
		return mac.doFinal();
	}
	
	public static byte[] hashStream(final InputStream stream, final MessageDigest digest) throws IOException {
		byte[] buffer = new byte[8192];
		int offset = 0;
		try (final InputStream input = stream) {
			while ((offset = input.read(buffer)) != -1) {
			    digest.update(buffer, 0, offset);
			}
		}
		return digest.digest();
	}
	
	public static Signature signature(final SignatureAlgorithm algorithm) throws NoSuchAlgorithmException {
		return Signature.getInstance(algorithm.name());
	}
	
	private DigitalUtil() {
	}
}
