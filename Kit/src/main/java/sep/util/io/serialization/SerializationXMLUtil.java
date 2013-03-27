package sep.util.io.serialization;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;

public final class SerializationXMLUtil {
	public static Serializable deserialization(final InputStream inputStream) {
		final Serializable object;
		try (final XMLDecoder xml = new XMLDecoder(inputStream)) {
			object = (Serializable) xml.readObject();
		}
		return object;
	}

	public static Serializable[] deserializations(final InputStream inputStream) {
		return (Serializable[]) deserialization(inputStream);
	}
	
	public static <T extends Serializable> void serialization(final OutputStream outputStream, final T object) {
		try (final XMLEncoder xml = new XMLEncoder(outputStream)) {
			xml.writeObject(object);
		}
	}

	public static void serializations(final OutputStream outputStream, final Serializable... objects) {
		serialization(outputStream, objects);
	}
	
	private SerializationXMLUtil() {
	}
}
