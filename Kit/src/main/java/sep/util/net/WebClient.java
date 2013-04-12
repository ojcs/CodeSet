package sep.util.net;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import sep.util.io.stream.StreamConvert;
import sep.util.io.stream.StreamUtil;

public final class WebClient {
	public static BufferedReader read(final InputStream stream) {
		return StreamConvert.toBuffered(new InputStreamReader(stream));
	}
	
	public static InputStream DELETE(final URL address, final Map<String, String> data) throws IOException {
		return submitRequest(makeRequest(address, "DELETE", null, data, null));
	}

	public static InputStream GET(final URL address) throws IOException {
		return submitRequest(makeRequest(address, "GET", null, (byte[]) null));
	}

	public static InputStream GET(final URL address, final Map<String, String> data) throws IOException {
		return submitRequest(makeRequest(address, "GET", null, data, null));
	}

	public static InputStream POST(final URL address, final Map<String, String> data) throws IOException {
		return submitRequest(makeRequest(address, "POST", null, data, null));
	}

	public static InputStream POST(final URL address, final Map<String, String> data, final Map<String, Path> dataFile) throws IOException {
		try (final ByteArrayOutputStream stream = new ByteArrayOutputStream()) {
			final String dataSep = "-----------------------------7da2e536604c8";
			try (final OutputStream outputStream = new DataOutputStream(stream)) {
				final String dataHeader = dataSep + "\r\n";
				if (data != null && !data.isEmpty()) {
					for (Entry<String, String> entry : data.entrySet()) {
						final StringBuilder builder = new StringBuilder(dataHeader);
						builder.append("Content-Disposition:form-data;name=\"").append(entry.getKey()).append("\"\r\n\r\n");
						builder.append(entry.getValue());
					}
				}
				if (dataFile != null && !dataFile.isEmpty()) {
					for (Entry<String, Path> entry : dataFile.entrySet()) {
						outputStream.write(new StringBuilder(dataHeader)
						.append("Content-Disposition:form-data;name=\"file").append(entry.getKey()).append("\";filename=\"").append(entry.getValue().getFileName()).append("\"\r\n")
						.append("Content-Type:application/octet-stream\r\n\r\n").toString().getBytes());
						
						StreamConvert.convert(
							Files.newInputStream(entry.getValue()),
							outputStream,
							true,
							StreamUtil.BUFFER_SIZE
						);
		                outputStream.write("\r\n".getBytes()); //多个文件时，二个文件之间加入这个   
					}
				}
				outputStream.write((dataSep + "--").getBytes());
			}
			
			final Map<String, String> headers = new HashMap<>();
			headers.put("Connection", "keep-alive");
			headers.put("Charset", "UTF-8");
			headers.put("Content-Type", "multipart/form-data;boundary=" + dataSep); 
			return submitRequest(makeRequest(address, "POST", headers, stream.toByteArray()));
		}
	}

	public static InputStream PUT(final URL address, final Map<String, String> data) throws IOException {
		return submitRequest(makeRequest(address, "PUT", null, data, null));
	}

	public static HttpURLConnection makeRequest(final URL address, final String method, final Map<String, String> headers, final byte[] data) throws IOException {
		final HttpURLConnection connection = (HttpURLConnection) address.openConnection();
		connection.setDoInput(true);
		connection.setDoOutput(true);
		connection.setRequestMethod(method);
		
		if (headers != null && !headers.isEmpty()) {
			for (Entry<String, String> entry : headers.entrySet()) {
				connection.addRequestProperty(entry.getKey(), entry.getValue());
			}
		}
		
		if (data != null && data.length > 0) { 
			try (final OutputStream stream = connection.getOutputStream()) {
				stream.write(data);
				stream.flush();
			}
		}

		return connection;
	}

	public static HttpURLConnection makeRequest(final URL address, final String method, final Map<String, String> headers, final InputStream data) throws IOException {
		final HttpURLConnection connection = (HttpURLConnection) address.openConnection();
		connection.setDoInput(true);
		connection.setDoOutput(true);
		connection.setRequestMethod(method);
		
		if (headers != null && !headers.isEmpty()) {
			for (Entry<String, String> entry : headers.entrySet()) {
				connection.addRequestProperty(entry.getKey(), entry.getValue());
			}
		}
		
		if (data == null) { 
			throw new NullPointerException("Data is Null");
		} else {
			StreamConvert.convert(data, connection.getOutputStream(), true, StreamUtil.BUFFER_SIZE);
		}

		return connection;
	}

	public static HttpURLConnection makeRequest(final URL address, final String method, final Map<String, String> headers, final Map<String, String> data, final Charset dataCharset) throws IOException {
		if (data == null) {
			throw new IllegalArgumentException();
		}
		final StringBuffer buffer = new StringBuffer();
		for (final Entry<String, String> entry : data.entrySet()) {
			buffer.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
		}
		buffer.deleteCharAt(buffer.length());
		return makeRequest(address, method, null, dataCharset == null ? buffer.toString().getBytes() : buffer.toString().getBytes(dataCharset));
	}

	public static HttpURLConnection makeRequest(final URL address, final String method, final Map<String, String> headers, final String data, final Charset dataCharset) throws IOException {
		return makeRequest(address, method, null, dataCharset == null ? data.getBytes() : data.getBytes(dataCharset));
	}

	public static InputStream submitRequest(final HttpURLConnection connection) throws IOException {
		connection.connect();
		final InputStream stream = StreamConvert.copy(connection.getInputStream());
		connection.disconnect();
		return stream;
	}

	private WebClient() {
	}
}
