package sep.util.other;

import java.nio.ByteBuffer;

import javax.xml.bind.DatatypeConverter;

public class Bytes {
    public static int getInt(int index, byte... buffer) {
		int result = 0;
		for (int i = 0; i < 4; i++) {
			result |= (buffer[index + i] & 0xFF) << 8 * i;
		}
		return result;
	}
    
    public static long getLong(int index, byte... buffer) {
		long result = 0;
		for (int i = 0; i < 8; i++) {
			result |= (buffer[index + i] & 0xFF) << 8 * i;
		}
		return result;
	}
    
    public static short getShort(int index, byte... buffer) {
		short result = 0;
		for (int i = 0; i < 2; i++) {
			result |= (buffer[index + i] & 0xFF) << 8 * i;
		}
		return result;
	}
    
    public static void put(int index, byte[] buffer, int value) {
		for (int i = 0; i < 4; i++) {
			buffer[index + i] = (byte) ((0xFF & value) >> 8 * i);
		}
	}
	
	public static void put(int index, byte[] buffer, long value) {
		for (int i = 0; i < 8; i++) {
			buffer[index + i] = (byte) ((0xFF & value) >> 8 * i);
		}
	}
	
	public static void put(int index, byte[] buffer, short value) {
		for (int i = 0; i < 2; i++) {
			buffer[index + i] = (byte) ((0xFF & value) >> 8 * i);
		}
	}
	
	public static byte[] toByteArray(final String hexadecimalString) {
		return DatatypeConverter.parseHexBinary(hexadecimalString);
	}
	
	public static String toHexString(final byte... values) {
		return Convert.toHexString(values);
	}
	
	protected final byte[] buffer;
	
	public Bytes(final byte... values) {
		buffer = values;
	}

	public Bytes(final int... values) {
		buffer = new byte[values.length * 4];
		for (int i = 0; i < values.length; i++) {
			put(i * 4, buffer, values[i]);
		}
	}
	
	public Bytes(final long... values) {
		buffer = new byte[values.length * 8];
		for (int i = 0; i < values.length; i++) {
			put(i * 8, buffer, values[i]);
		}
	}

	public Bytes(final short... values) {
		buffer = new byte[values.length * 2];
		for (int i = 0; i < values.length; i++) {
			put(i * 2, buffer, values[i]);
		}
	}

	public Bytes(final String hexadecimalString) {
		this(toByteArray(hexadecimalString));
	}

	public byte byteAt(final int index) {
		return buffer[index];
	}

	public int length() {
		return buffer.length;
	}
	
	public byte set(final int index, final byte value) {
		return buffer[index] = value;
	}
	
	public ByteBuffer toBuffer() {
		return ByteBuffer.wrap(buffer);
	}
	
	public byte[] toByteArray() {
		return buffer;
	}
	
	@Override
	public String toString() {
		return toHexString(buffer);
	}
}
