package sep.util.other;

import javax.xml.bind.DatatypeConverter;

public class Bytes {
	private static final char[] hexDigit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
   
    public static char toHex(int value) {
        return hexDigit[value & 0xF];
    }
    
    public static char[] toHex(byte value) {
		return new char[] { toHex((value & 0x0F) >> 4), toHex(value & 0x0F) };
    }
    
    public static char[] toHex(char value) {
		return new char[] { toHex(value & 0xF), toHex((value >> 4) & 0xF), toHex((value >> 8) & 0xF), toHex((value >> 12) & 0xF) };
    }
	
	public static byte[] toByteArray(final String hexadecimalString) {
		return DatatypeConverter.parseHexBinary(hexadecimalString);
	}
	
	public static String toString(final byte... buffer) {
		StringBuilder builder = new StringBuilder(buffer.length * 2);
		for (byte b : buffer) {
			builder.append(toHex(b));
		}
		return builder.toString();
	}
	
	protected final byte[] buffer;
	
	public Bytes(final Number value) {
		if (value instanceof Integer) {
			this.buffer = toByteArray(Integer.toHexString((Integer) value));
		} else if (value instanceof Long) {
			this.buffer = toByteArray(Long.toHexString((Long) value));
		} else if (value instanceof Float) {
			this.buffer = toByteArray(Float.toHexString((Float) value));
		} else if (value instanceof Double) {
			this.buffer = toByteArray(Double.toHexString((Double) value));
		} else {
			throw new IllegalArgumentException("value not is Primitive Type");
		}
	}
	
	public Bytes(final byte... buffer) {
		this.buffer = buffer;
	}
	
	public Bytes(final String hexadecimalString) {
		this(toByteArray(hexadecimalString));
	}
	
	public byte byteAt(final int index) {
		return buffer[index];
	}

	public byte byteAt(final int index, final byte value) {
		return buffer[index] = value;
	}
	
	public int length() {
		return buffer.length;
	}
	
	public byte[] toByteArray() {
		return buffer;
	}

	@Override
	public String toString() {
		return toString(buffer);
	}
}
