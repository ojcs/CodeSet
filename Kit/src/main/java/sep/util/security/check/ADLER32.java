package sep.util.security.check;

public class ADLER32 extends Checksum {
	public ADLER32() {
		super(new java.util.zip.Adler32());
	}
}
