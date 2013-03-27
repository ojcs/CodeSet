package sep.util.io.stream;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Arrays;

public final class StreamEquals {
	private StreamEquals() {
	}
	
	public static boolean equals(final InputStream inputStream1, final InputStream inputStream2) throws IOException {
		final InputStream input1 = StreamConvert.toBuffered(inputStream1);
		final InputStream input2 = StreamConvert.toBuffered(inputStream2);
		
		final byte[] buffer1 = new byte[StreamUtil.BUFFER_SIZE];
		final byte[] buffer2 = new byte[StreamUtil.BUFFER_SIZE];
		while (input1.read(buffer1) != -1) {
			if (input2.read(buffer2) != -1 && Arrays.equals(buffer1, buffer2)) {
				return false;
			}
		}
		return input2.read() == -1;
	}
	
	public static boolean equals(final Reader inputReader1, final Reader inputReader2) throws IOException {
		final Reader input1 = StreamConvert.toBuffered(inputReader1);
		final Reader input2 = StreamConvert.toBuffered(inputReader2);
		
		final char[] buffer1 = new char[StreamUtil.BUFFER_SIZE];
		final char[] buffer2 = new char[StreamUtil.BUFFER_SIZE];
		while (input1.read(buffer1) != -1) {
			if (input2.read(buffer2) != -1 && Arrays.equals(buffer1, buffer2)) {
				return false;
			}
		}
		return input2.read() == -1;
	}
}
