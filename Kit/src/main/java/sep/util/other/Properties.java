package sep.util.other;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sep.util.io.serialization.SerializationUtil;

public class Properties extends Hashtable<String, String> {
	private static final long serialVersionUID = 1L;

	protected static String stringToUnicodeString(final String value) {
		final char[] prefix = { '\\', 'u' };
		StringBuilder builder = new StringBuilder(value.length() * 2);
		for (char c : value.toCharArray()) {
			// 汉字范围 \u4E00-\u9FA5 (中文)
			if (c >= 19968 && c <= 171941) {
				builder.append(prefix);
				builder.append(new Bytes((int) c).toString());
			} else {
				builder.append(c);
			}
		}
		return builder.toString();
	}
	
	protected static String unicodeStringToString(final String value) {
		if (value.matches("((\\u([0-9A-Fa-f]{4}))|\\w)+")) {
			StringBuffer buffer = new StringBuffer();
			StringTokenizer tokenizer = new StringTokenizer(value, "\\u");
			while (tokenizer.hasMoreTokens()) {
				buffer.append((char) Integer.parseInt(tokenizer.nextToken(), 16));
			}
			return buffer.toString();
		} else {
			return value;
		}
	}
	
	public Properties() {
		super();
	}
	
	public Properties(final BufferedReader input) throws IOException {
		load(input);
	}
	
	public Properties(final java.util.Properties defaultProperties) {
		load(defaultProperties);
	}
	
	public Properties(final Properties defaultProperties) {
		load(defaultProperties);
	}
	
	@Override
	public final Properties clone() {
		return (Properties) super.clone();
	}
	
	@Override
	@Deprecated
	public final String get(final Object key) {
		throw new UnsupportedOperationException();
	}
	
	public final String get(final String key) {
		return super.get(key);
	}
	
	public final String get(final String key, final String defaultValue) {
		String value = super.get(key);
		return (value == null) ? defaultValue : value;
	}
	
	public void load(final BufferedReader input) throws IOException {
		Pattern pattern = Pattern.compile("(?<key>\\w+)=(?<val>(\\u[0-9A-Fa-f]{4})|[^\\n])+");
		while (!input.ready()) {
			Matcher matcher = pattern.matcher(input.readLine());
			String key = matcher.group("key");
			String val = matcher.group("val");
			put(key, val);
		}
		input.close();
	}
	
	public void load(final java.util.Properties defaultProperties) {
		putAll(defaultProperties);
	}
	
	public void load(final Properties defaultProperties) {
		putAll(defaultProperties);
	}
	
	public String put(final String key, final boolean... values) {
		return put(key, Arrays.toString(values));
	}
	
	public String put(final String key, final byte... values) {
		return put(key, new Bytes(values).toString());
	}
	
	public String put(final String key, final char... values) {
		return put(key, Arrays.toString(values));
	}
	
	public String put(final String key, final double... values) {
		return put(key, Arrays.toString(values));
	}
	
	public String put(final String key, final float... values) {
		return put(key, Arrays.toString(values));
	}
	
	public String put(final String key, final int... values) {
		return put(key, Arrays.toString(values));
	}
	
	public String put(final String key, final long... values) {
		return put(key, Arrays.toString(values));
	}
	
	public String put(final String key, final Object... values) {
		return put(key, Arrays.toString(values));
	}
	
	public String put(final String key, final Serializable serialObject) {
		final ByteArrayOutputStream stream = new ByteArrayOutputStream();
		try {
			SerializationUtil.serialization(stream, serialObject);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return put(key, stream.toByteArray());
	}
	
	public String put(final String key, final short... values) {
		return put(key, Arrays.toString(values));
	}
	
	@Override
	public final String put(final String key, final String value) {
		if (value == null) {
			return super.put(key, "null");
		}
		return super.put(key, value);
	}
	
	public final void putAll(final java.util.Properties properties) {
		for (Map.Entry<Object, Object> entry : properties.entrySet()) {
			put((String) entry.getKey(), (String) entry.getValue());
		}
	}
	
	public final void putAll(final Properties properties) {
		for (Map.Entry<String, String> entry : properties.entrySet()) {
			put(entry.getKey(), entry.getValue());
		}
	}
	
	@Override
	@Deprecated
	public final String remove(final Object key) {
		throw new UnsupportedOperationException();
	}
	
	public final String remove(final String key) {
		return super.remove(key);
	}

	public final void store(final BufferedWriter output) throws IOException {
		for (Map.Entry<String, String> entry : entrySet()) {
			output.append(entry.getKey());
			output.append('=');
			output.append(entry.getValue());
			output.newLine();
			output.flush();
		}
		output.close();
	}
	
	public java.util.Properties toJavaUtilProperties() {
		java.util.Properties properties = new java.util.Properties();
		properties.putAll(this);
		return properties;
	}
}
