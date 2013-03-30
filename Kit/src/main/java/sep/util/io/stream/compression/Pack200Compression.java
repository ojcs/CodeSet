package sep.util.io.stream.compression;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.jar.JarFile;
import java.util.jar.JarInputStream;
import java.util.jar.JarOutputStream;
import java.util.jar.Pack200;

public final class Pack200Compression extends
		CompressionStream<JarInputStream, JarOutputStream> {
	public void compression(final JarFile input, final OutputStream output)
			throws IOException {
		Pack200.newPacker().pack(input, output);
	}

	@Override
	public void compression(JarInputStream input, OutputStream output)
			throws IOException {
		Pack200.newPacker().pack(input, output);
	}

	@Override
	public void decompression(InputStream input, JarOutputStream output)
			throws IOException {
		Pack200.newUnpacker().unpack(input, output);
	}

	public void decompression(final JarFile input, final JarOutputStream output)
			throws IOException {
		Pack200.newUnpacker().unpack(new File(input.getName()), output);
	}
}
