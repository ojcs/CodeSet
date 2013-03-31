package sep.util.io.stream;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

public final class StreamConvert {
	private StreamConvert() {
	}
	
	public static ByteArrayInputStream copy(final InputStream inputStream) throws IOException {
		final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		convert(inputStream, outputStream, true, StreamUtil.BUFFER_SIZE);
		return new ByteArrayInputStream(outputStream.toByteArray());
	}
	
	public static void convert(final InputStream inputStream, final OutputStream outputStream, final boolean isClose, final int bufferSize) throws IOException {
		if (inputStream == null && outputStream == null) {
			throw new IllegalArgumentException("InputStream/OutputStream is null!");
		} else {
			final byte[] buffer = new byte[bufferSize];
			int offset;
			while ((offset = inputStream.read(buffer, 0, bufferSize)) != -1) { 
				outputStream.write(buffer, 0, offset);
			}
			if (isClose) {
				inputStream.close();
				outputStream.close();
			}
		}
	}
	
	public static void convert(final Reader inputStream, final Writer outputStream, final boolean isClose, final int bufferSize) throws IOException {
		if (inputStream == null && outputStream == null) {
			throw new IllegalArgumentException("InputStream/OutputStream is null!");
		} else {
			final char[] buffer = new char[bufferSize];
			int offset;
			while ((offset = inputStream.read(buffer, 0, bufferSize)) != -1) { 
				outputStream.write(buffer, 0, offset);
			}
			if (isClose) {
				inputStream.close();
				outputStream.close();
			}
		}
	}
	
	public static BufferedInputStream toBuffered(final InputStream stream) {
		if (stream == null) {
			throw new NullPointerException("InputStream is null!");
		} else {
			if (stream instanceof BufferedInputStream) {
				return (BufferedInputStream) stream;
			} else {
				return new BufferedInputStream(stream);
			}
		}
	}
	
	public static BufferedOutputStream toBuffered(final OutputStream stream) {
		if (stream == null) {
			throw new NullPointerException("OutputStream is null!");
		} else {
			if (stream instanceof BufferedOutputStream) {
				return (BufferedOutputStream) stream;
			} else {
				return new BufferedOutputStream(stream);
			}
		}
	}
		
	public static BufferedReader toBuffered(final Reader reader) {
		if (reader == null) {
			throw new NullPointerException("Reader is null!");
		} else {
			if (reader instanceof BufferedReader) {
				return (BufferedReader) reader;
			} else {
				return new BufferedReader(reader);
			}
		}
	}
	
	public static BufferedWriter toBuffered(final Writer writer) {
		if (writer == null) {
			throw new NullPointerException("Writer is null!");
		} else {
			if (writer instanceof BufferedWriter) {
				return (BufferedWriter) writer;
			} else {
				return new BufferedWriter(writer);
			}
		}
	}
}
