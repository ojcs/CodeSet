package sep.util.io.file;

import java.io.File;
import java.nio.file.Path;
import java.util.Collection;
import java.util.HashSet;

public class FileSet extends HashSet<File> {
	private static final long serialVersionUID = 1L;

	public FileSet() {
		super();
	}

	public FileSet(Collection<File> c) {
		super(c);
	}

	public FileSet(File... files) {
		for (File file : files) {
			add(file);
		}
	}
	
	public FileSet(int initialCapacity) {
		super(initialCapacity);
	}

	public FileSet(int initialCapacity, float loadFactor) {
		super(initialCapacity, loadFactor);
	}

	public FileSet(PathSet files) {
		super();
		for (Path path : files) {
			add(path.toFile());
		}
	}
	
	public boolean add(Path path) {
		return super.add(path.toFile());
	}
	
	public boolean add(String path) {
		return super.add(new File(path));
	}
	
	public boolean addAll(File... files) {
		for (File file : files) {
			if (!super.add(file)) {
				return false;
			}
		}
		return true;
	}
	
	public boolean addAll(Path... paths) {
		for (Path path : paths) {
			if (!super.add(path.toFile())) {
				return false;
			}
		}
		return true;
	}
	
	public boolean addAll(String... paths) {
		for (String path : paths) {
			if (!super.add(new File(path))) {
				return false;
			}
		}
		return true;
	}
	
	public boolean deleteAllFile() {
		for (File file : this) {
			if (!file.delete()) {
				return false;
			}
		}
		return true;
	}

	@Override
	public File[] toArray() {
		return super.toArray(new File[size()]);
	}
	
	public PathSet toPathSet() {
		return new PathSet(this);
	}
}
