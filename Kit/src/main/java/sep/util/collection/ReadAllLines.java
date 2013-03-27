package sep.util.collection;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Enumeration;

import sep.util.io.stream.StreamConvert;

public class ReadAllLines implements Enumeration<String>, Closeable {
	private final BufferedReader reader;

	public ReadAllLines(final File file, final Charset charset) throws IOException {
		this(file.toPath(), charset);
	}
	
	public ReadAllLines(final Path path, final Charset charset) throws IOException {
		this(Files.newBufferedReader(path, charset));
	}
	
	public ReadAllLines(final Reader reader) {
		this.reader = StreamConvert.toBuffered(reader);
	}

	@Override
	public void close() throws IOException {
		reader.close();
	}

	@Override
	public boolean hasMoreElements() {
		try {
			return reader.ready();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public String nextElement() {
		try {
			return reader.readLine();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
