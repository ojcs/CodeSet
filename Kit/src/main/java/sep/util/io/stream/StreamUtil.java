package sep.util.io.stream;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.Flushable;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.file.Path;

public final class StreamUtil {
	public static final int BUFFER_SIZE = 1024 * 1024;
	
	public static boolean safeClose(final Closeable close) {
		if (null != close) {
			try {
				close.close();
			} catch (IOException e) {
				return false;
			}
			return true;
		}
		return false;
	}

	public static boolean safeFlush(final Flushable flush) {
		if (null != flush) {
			try {
				flush.flush();
			} catch (IOException e) {
				return false;
			}
			return true;
		}
		return false;
	}
	
	public static byte[] readAll(final InputStream inputStream) throws IOException {
		final ByteArrayOutputStream outputStream = new ByteArrayOutputStream(StreamUtil.BUFFER_SIZE);
		StreamConvert.convert(inputStream, outputStream, true, StreamUtil.BUFFER_SIZE);
		return outputStream.toByteArray();
	}

	public static CharSequence readAll(final Reader reader) throws IOException {
		return readAll(StreamConvert.toBuffered(reader));
	}
	
	public static CharSequence readAll(final BufferedReader reader) throws IOException {
		final StringBuffer strBuffer = new StringBuffer();
		
		while (reader.ready()) {
			strBuffer.append(reader.readLine());
		}
		
		return strBuffer;
	}
	
	public static void toFile(final InputStream inputStream, final Path targetPath) throws IOException {
		toFile(inputStream, targetPath.toFile());
	}
	
	public static void toFile(final InputStream inputStream, final File targetFile) throws IOException {
		StreamConvert.convert(inputStream, new FileOutputStream(targetFile), true, BUFFER_SIZE);
	}

	private StreamUtil() {
	}
}
