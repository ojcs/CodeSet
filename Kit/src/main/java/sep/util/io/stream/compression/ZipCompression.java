package sep.util.io.stream.compression;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Enumeration;
import java.util.Map;
import java.util.Map.Entry;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import sep.util.io.stream.StreamConvert;
import sep.util.io.stream.StreamUtil;

public class ZipCompression implements CompressionFile<ZipOutputStream, Map<ZipEntry, Path>, ZipEntry, ZipFile> {
	@Override
	public void compression(ZipOutputStream output, Map<ZipEntry, Path> entries)
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

	@SuppressWarnings("unchecked")
	@Override
	public Enumeration<ZipEntry> decompression(ZipFile file) throws IOException {
		return (Enumeration<ZipEntry>) file.entries();
	}
}
