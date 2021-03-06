package sep.util.io.file;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public final class FileUtil {
	public static Path classpath(String name) {
		try {
			final URL url = Class.class.getResource(name);
			return (url != null) ? Paths.get(url.toURI()) : null;
		} catch (URISyntaxException e) {
			return null;
		}
	}
	
	public static boolean deleteDirectory(final File file) {
		if (file.isDirectory())
			for (final File down : file.listFiles())
				if (!deleteDirectory(down))
					return false;
		return file.delete();
	}
	
	public static boolean deleteDirectory(final Path path) throws IOException {
		if (Files.isDirectory(path))
			for (Path down : Files.newDirectoryStream(path))
				if (!deleteDirectory(down))
					return false;
		return Files.deleteIfExists(path);
	}
	
	public static Path[] listPaths(final File... files) {
		List<Path> paths = new ArrayList<Path>(files.length);
		for (File file : files) {
			if (file == null) {
				continue;
			}
			paths.add(file.toPath());
		}
		return paths.toArray(new Path[paths.size()]);
	}

	public static Path[] listPaths(final String paths) {
		return listPaths(paths.split(File.pathSeparator));
	}
	
	public static Path[] listPaths(final String... paths) {
		List<Path> list = new ArrayList<>();
		for (String path : paths) {
			if (path == null) {
				continue;
			}
			list.add(Paths.get(path));
		}
		return list.toArray(new Path[list.size()]);
	}
	
	public static ByteBuffer load(Path path, ByteOrder order) throws IOException {
		ByteBuffer buffer = ByteBuffer.wrap(Files.readAllBytes(path));
		buffer.order(order);
		return buffer;
	}

	private FileUtil() {
	}
}
