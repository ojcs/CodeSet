package sep.util.io.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;

public class PathSet extends ArrayList<Path> {
	private static final long serialVersionUID = 1L;

	public PathSet() {
		super();
	}
	
	public PathSet(Collection<? extends Path> c) {
		super(c);
	}
	
	public PathSet(FileSet files) {
		super();
		for (File file : files) {
			add(file.toPath());
		}
	}

	public PathSet(int initialCapacity) {
		super(initialCapacity);
	}

	public boolean add(Path path) {
		return super.add(path);
	}

	public boolean add(String path) {
		return super.add(Paths.get(path));
	}

	public boolean addAll(File... files) {
		for (File file : files) {
			if (!super.add(file.toPath())) {
				return false;
			}
		}
		return true;
	}
	
	public boolean addAll(Path... paths) {
		for (Path path : paths) {
			if (!super.add(path)) {
				return false;
			}
		}
		return true;
	}

	public boolean addAll(String paths) {
		return addAll(paths.split(File.pathSeparator));
	}

	public boolean addAll(String... paths) {
		for (String path : paths) {
			if (!super.add(Paths.get(path))) {
				return false;
			}
		}
		return true;
	}

	public boolean deleteAllFile() throws IOException {
		for (Path path : this) {
			if (!Files.deleteIfExists(path)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public Path[] toArray() {
		return super.toArray(new Path[size()]);
	}
	
	public FileSet toFileSet() {
		return new FileSet(this);
	}
}
