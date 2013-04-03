package sep.web.filter;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.zip.GZIPOutputStream;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public final class GZIPEncodableFilter extends AbstractFilter {
	private final class GZIPEncodableResponse extends HttpServletResponseWrapper {
		private final GZIPServletStream output;
		private final PrintWriter writer;

		public GZIPEncodableResponse(HttpServletResponse response) throws IOException {
			super(response);
			output = new GZIPServletStream(response.getOutputStream());
			writer = new PrintWriter(new OutputStreamWriter(output, getCharacterEncoding()));
		}

		@Override
		public void flushBuffer() throws IOException {
			if (writer != null) {
				writer.flush();
			}
			output.finish();
			super.flushBuffer();
		}

		public ServletOutputStream getOutputStream() throws IOException {
			return output;
		}

		public PrintWriter getWriter() throws IOException {
			return writer;
		}
	}

	private final class GZIPServletStream extends ServletOutputStream {
		private GZIPOutputStream stream;

		public GZIPServletStream(OutputStream source) throws IOException {
			stream = new GZIPOutputStream(source);
		}

		@Override
		public void close() throws IOException {
			stream.close();
		}

		public void finish() throws IOException {
			stream.finish();
		}

		@Override
		public void flush() throws IOException {
			stream.flush();
		}

		@Override
		public boolean isReady() {
			return true;
		}

		@Override
		public void setWriteListener(WriteListener writeListener) {
		}

		public void write(int c) throws IOException {
			stream.write(c);
		}
	}

	private final static String handlerEncoding(String encoding) {
		if (encoding == null) {
			return null;
		} else {
			if (encoding.contains("x-gzip")) {
				return "x-gzip";
			} else if (encoding.contains("gzip")) {
				return "gzip";
			} else {
				return null;
			}
		}
	}
	
	@Override
	public final void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		final String encoding = handlerEncoding(request.getHeader("Accept-Encoding"));
		if (encoding == null) {
			chain.doFilter(request, response);
		} else {
			response.setHeader("Content-Encoding", encoding);
			chain.doFilter(request, new GZIPEncodableResponse(response));
		}
	}
}
