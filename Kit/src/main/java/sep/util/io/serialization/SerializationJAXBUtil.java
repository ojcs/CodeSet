package sep.util.io.serialization;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class SerializationJAXBUtil {
	private static JAXBContext context;
	
	static {
		try {
			context = JAXBContext.newInstance();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	public static Serializable deserialization(final InputStream inputStream) throws JAXBException {
		return (Serializable) context.createUnmarshaller().unmarshal(inputStream);
	}
	
	public static Serializable deserialization(final InputStream inputStream, final Map<String, Object> properties) throws JAXBException {
		Unmarshaller unmarshaller = context.createUnmarshaller();
		for (Entry<String, Object> entry : properties.entrySet()) {
			unmarshaller.setProperty(entry.getKey(), entry.getValue());
		}
		return (Serializable) unmarshaller.unmarshal(inputStream);
	}
	
	public static <T extends Serializable> void serialization(final OutputStream outputStream, final T element) throws JAXBException {
		context.createMarshaller().marshal(element, outputStream);
	}
	
	public static <T extends Serializable> void serialization(final OutputStream outputStream, final Map<String, Object> properties, final T element) throws JAXBException {
		Marshaller marshaller = context.createMarshaller();
		for (Entry<String, Object> entry : properties.entrySet()) {
			marshaller.setProperty(entry.getKey(), entry.getValue());
		}
		marshaller.marshal(element, outputStream);
	}
}
