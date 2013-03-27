package sep.util.other;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import sep.util.io.serialization.SerializationUtil;

public final class OtherUtil {
	@SuppressWarnings("unchecked")
	public static <T extends Serializable> T clone(final T object) {
		try {
			try (final ByteArrayOutputStream output = new ByteArrayOutputStream(1024)) {
				SerializationUtil.serialization(output, object);
				try (final InputStream input = new ByteArrayInputStream(output.toByteArray())) {
					return (T) SerializationUtil.deserialization(input);
				}
			}
		} catch (ClassNotFoundException | IOException e) {
			return null;
		}
	} 
	
	public static ScriptEngine getScriptEngine(final String engineName) {
		return new ScriptEngineManager().getEngineByName(engineName);
	}
	
	private OtherUtil() {
	}
}
