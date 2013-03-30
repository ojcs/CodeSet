package sep.util.io.stream.compression;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import sep.util.io.stream.StreamConvert;
import sep.util.io.stream.StreamUtil;

public abstract class CompressionStream<Input extends InputStream, Output extends OutputStream> {
	public void compression(final Input input, final OutputStream output)
			throws IOException {
		StreamConvert.convert(input, output, true, StreamUtil.BUFFER_SIZE);
	}

	public void decompression(final InputStream input, final Output output)
			throws IOException {
		StreamConvert.convert(input, output, true, StreamUtil.BUFFER_SIZE);
	}
}
