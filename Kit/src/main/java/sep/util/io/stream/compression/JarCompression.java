package sep.util.io.stream.compression;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Enumeration;
import java.util.Map;
import java.util.Map.Entry;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;
import java.util.zip.ZipEntry;

import sep.util.io.stream.StreamConvert;
import sep.util.io.stream.StreamUtil;

public class JarCompression implements CompressionFile<JarOutputStream, Map<JarEntry, Path>, JarEntry, JarFile> {
	@Override
	public void compression(JarOutputStream output, Map<JarEntry, Path> entries)
			throws IOException {
		for (final Entry<? extends ZipEntry, Path> entry : entries.entrySet()) {
			output.putNextEntry(entry.getKey());
			try (final InputStream input = Files.newInputStream(entry.getValue())) {
				StreamConvert.convert(input, output, false, StreamUtil.BUFFER_SIZE);
			}
			output.finish();
			output.flush();
		}
		output.close();
	}

	@Override
	public Enumeration<JarEntry> decompression(JarFile file) throws IOException {
		return file.entries();
	}
}
