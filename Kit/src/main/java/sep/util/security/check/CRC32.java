package sep.util.security.check;

public class CRC32 extends Checksum {
	public CRC32() {
		super(new java.util.zip.CRC32());
	}
}
