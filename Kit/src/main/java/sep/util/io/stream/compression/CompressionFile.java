package sep.util.io.stream.compression;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.util.Enumeration;
import java.util.Map;

public interface CompressionFile<Output extends OutputStream, Entries extends Map<Entry, Path>, Entry, File> {
	void compression(final Output output, Entries entries) throws IOException;

	Enumeration<Entry> decompression(final File file) throws IOException;
}
