package sep.web.filter;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public final class ResponseHeaderFilter extends AbstractFilter {
	private final Map<String, String> headers = new HashMap<>();
	
	@Override
	public final void init(FilterConfig config) throws ServletException {
		Enumeration<String> parameterNames = config.getInitParameterNames();
		while (parameterNames.hasMoreElements()) {
			String name = parameterNames.nextElement();
			headers.put(name, config.getInitParameter(name));
		}
	}

	@Override
	public final void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		for (Entry<String, String> entry : headers.entrySet()) {
			response.addHeader(entry.getKey(), entry.getValue());
		}
		chain.doFilter(request, response);
	}
}
