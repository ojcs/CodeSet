package sep.util.io.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import sep.util.collection.Fetch;
import sep.util.other.Convert;
import sep.util.other.Convert.Callback;

public final class FileUtil {
	/** 复制文件 */
	public static void copy(final Path sourceFile, final Path targetFile)
			throws IOException {
		try (final InputStream sourceStream = new FileInputStream(sourceFile.toFile())) {
			Files.copy(sourceStream, targetFile);
		}
	}

	public static ListIterator<File> listFiles(final Iterator<Path> iterator) {
		List<File> files = new ArrayList<File>();
		for (Path path : new Fetch<Path>(iterator)) {
			files.add(path.toFile());
		}
		return files.listIterator();
	}
	
	public static ListIterator<Path> listPaths(final File... fileList) {
		return listPaths(Arrays.asList(fileList).listIterator());
	}
	
	public static ListIterator<Path> listPaths(final Iterator<File> iterator) {
		List<Path> paths = new ArrayList<Path>();
		for (File file : new Fetch<File>(iterator)) {
			paths.add(file.toPath());
		}
		return paths.listIterator();
	}
	
	public static ListIterator<Path> listPaths(final String paths) {
		return listPaths(paths.split(File.pathSeparator));
	}
	
	public static ListIterator<Path> listPaths(final String... pathList) {
		return Arrays.asList(Convert.as(pathList, new Callback<String, Path>() {
			@Override
			public Path as(final String old) {
				return Paths.get(old.trim());
			}
		})).listIterator();
	}
	
	public static boolean deletes(final Path path) throws IOException {
		if (Files.isDirectory(path))
			for (Path down : Files.newDirectoryStream(path))
				if (!deletes(down))
					return false;
		return Files.deleteIfExists(path);
	}

	public static boolean deletes(final File file) {
		if (file.isDirectory())
			for (final File down : file.listFiles())
				if (!deletes(down))
					return false;
		return file.delete();
	}

	private FileUtil() {
	}
}
