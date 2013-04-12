package sep.util.io.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public final class FileEquals {
	public static boolean equalsPath(final File file1, final File file2) {
		try {
			return file1.getCanonicalFile().equals(file2.getCanonicalFile());
		} catch (IOException e) {
			return false;
		}
	}
	
	public static boolean equalsPath(final Path path1, final Path path2) {
		return equalsPath(path1.toFile(), path2.toFile());
	}
	
	private FileEquals() {
	}
}
