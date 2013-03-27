package sep.util.io.file;

import java.io.File;
import java.util.Set;
import java.util.regex.Pattern;

public class FileFilter implements java.io.FilenameFilter {
	private final Pattern nameRegex;
	private final Pattern directoryRegex;
	
	public FileFilter(final Set<String> names) {
		StringBuffer buffer = new StringBuffer();
		buffer.append('(');
		for (String name : names) {
			buffer.append(Pattern.quote(name));
			buffer.append('|');
		}
		buffer.deleteCharAt(buffer.length());
		buffer.append(')');
		this.nameRegex = Pattern.compile(buffer.toString());
		this.directoryRegex = Pattern.compile(".*");
	}
	
	public FileFilter(final String prefix, final String suffix) {
		this(Pattern.compile(Pattern.quote(prefix) + "(.*)" + Pattern.quote(suffix), Pattern.CANON_EQ), null);
	}
	
	public FileFilter(final Pattern nameRegex, final Pattern directoryRegex) {
		this.nameRegex = nameRegex;
		this.directoryRegex = directoryRegex;
	}
	
	@Override
	public boolean accept(File dir, String name) {
		return nameRegex.matcher(name).matches() && directoryRegex.matcher(dir.getName()).matches();
	}
}
