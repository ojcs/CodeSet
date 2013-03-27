package sep.util.io.file;

import java.io.File;

public final class DirectoryUtil {
	public static void deletes(final File file) {
		if (file != null && file.isDirectory()) {
			for (final File down : file.listFiles()) {
				deletes(down);
			}
		}
		file.delete();
	}
	
	private DirectoryUtil() {
	}
}
