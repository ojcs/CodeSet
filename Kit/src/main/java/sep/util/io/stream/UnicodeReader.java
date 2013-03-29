package sep.util.io.stream;

/**
 version: 1.1 / 2007-01-25
 - changed BOM recognition ordering (longer boms first)
 Original pseudocode   : Thomas Weidenfeller
 Implementation tweaked: Aki Nieminen
 http://www.unicode.org/unicode/faq/utf_bom.html
 BOMs:
 00 00 FE FF    = UTF-32, big-endian
 FF FE 00 00    = UTF-32, little-endian
 EF BB BF       = UTF-8,
 FE FF          = UTF-16, big-endian
 FF FE          = UTF-16, little-endian
 Win2k Notepad:
 Unicode format = UTF-16LE
 ***/
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PushbackInputStream;
import java.nio.charset.Charset;

/**
 * Generic unicode textreader, which will use BOM mark to identify the encoding
 * to be used. If BOM is not found then use a given default or system encoding.
 */
public class UnicodeReader extends InputStreamReader {
	private static final int BOM_SIZE = 4;
	private static Charset charset;

	public UnicodeReader(InputStream in, Charset charset) throws IOException {
		super(init(in, charset), UnicodeReader.charset);
	}
	
	protected static InputStream init(InputStream in, Charset charset) throws IOException {
		PushbackInputStream internalIn = new PushbackInputStream(in, BOM_SIZE);
		byte[] bom;
		int offset = internalIn.read(bom = new byte[BOM_SIZE]);
		int unread = offset - offset(UnicodeReader.charset = CharStreamUtil.charset(charset, bom));
		if (unread > 0) {
			internalIn.unread(bom, (offset - unread), unread);
		}
		return internalIn;
	}

	protected static int offset(final Charset charset) {
		switch (charset.displayName()) {
		case "UTF-32BE": return 4;
		case "UTF-32LE": return 4;
		case "UTF-16LE": return 2;
		case "UTF-16BE": return 2;
		case "UTF-8"   : return 3;
		default        : return 0;
		}
	}
}