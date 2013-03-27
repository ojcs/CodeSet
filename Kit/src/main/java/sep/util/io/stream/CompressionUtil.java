package sep.util.io.stream;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarInputStream;
import java.util.jar.JarOutputStream;
import java.util.jar.Pack200;
import java.util.zip.DeflaterInputStream;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.InflaterInputStream;
import java.util.zip.InflaterOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;


public final class CompressionUtil {
	private CompressionUtil() {
	}
	
	public static void compression(final InputStream inputStream, final GZIPOutputStream outputStream) throws IOException {
		compression(inputStream, (DeflaterOutputStream) outputStream);
	}
	
	public static void decompression(final GZIPInputStream inputStream, final OutputStream outputStream) throws IOException {
		decompression((InflaterInputStream) inputStream, outputStream);
	}
	
	public static void compression(final InputStream inputStream, final DeflaterOutputStream outputStream) throws IOException {
		StreamConvert.convert(inputStream, outputStream, true, StreamUtil.BUFFER_SIZE);
	}
	
	public static void decompression(final DeflaterInputStream inputStream, final OutputStream outputStream) throws IOException {
		StreamConvert.convert(inputStream, outputStream, true, StreamUtil.BUFFER_SIZE);
	}
	
	public static void compression(final InputStream inputStream, final InflaterOutputStream outputStream) throws IOException {
		StreamConvert.convert(inputStream, outputStream, true, StreamUtil.BUFFER_SIZE);
	}
	
	public static void decompression(final InflaterInputStream inputStream, final OutputStream outputStream) throws IOException {
		StreamConvert.convert(inputStream, outputStream, true, StreamUtil.BUFFER_SIZE);
	}
	
	public static void compression(final ZipOutputStream outputStream, final Map<ZipEntry, File> entries) throws IOException {
		for (final Map.Entry<ZipEntry, File> entry : entries.entrySet()) {
			outputStream.putNextEntry(entry.getKey());
			try (final InputStream inputStream = new FileInputStream(entry.getValue())) {
				StreamConvert.convert(inputStream, outputStream, false, StreamUtil.BUFFER_SIZE);
			}
			outputStream.finish();
			outputStream.flush();
		}
		outputStream.close();
	}
	
	@SuppressWarnings("unchecked")
	public static Enumeration<ZipEntry> decompression(final ZipFile file) throws IOException {
		return (Enumeration<ZipEntry>) (Object) file.entries();
	}
	
	@SuppressWarnings("unchecked")
	public static void compression(final JarOutputStream outputStream,  final Map<JarEntry, File> entries) throws IOException {
		compression((ZipOutputStream) outputStream, (Map<ZipEntry, File>) (Object) entries);
	}
	
	public static Enumeration<JarEntry> decompression(final JarFile file) throws IOException {
		return file.entries();
	}

	/**
	 * Pack200 compression JarInputStream
	 */
	public static void compression(final JarInputStream inputStream, final OutputStream outputStream) throws IOException {
		Pack200.newPacker().pack(inputStream, outputStream);
	}
	
	/**
	 * Pack200 decompression InputStream
	 */
	public static void decompression(final InputStream inputStream, final JarOutputStream outputStream) throws IOException {
		Pack200.newUnpacker().unpack(inputStream, outputStream);
	}
	
	/**
	 * Pack200 compression JarFile
	 */
	public static void compression(final JarFile inputFile, final OutputStream outputStream) throws IOException {
		Pack200.newPacker().pack(inputFile, outputStream);
	}
	
	/**
	 * Pack200 decompression JarFile
	 */
	public static void decompression(final JarFile inputFile, final JarOutputStream outputStream) throws IOException {
		Pack200.newUnpacker().unpack(new File(inputFile.getName()), outputStream);
	}
}
