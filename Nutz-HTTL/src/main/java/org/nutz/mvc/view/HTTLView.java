package org.nutz.mvc.view;

import httl.Context;
import httl.web.WebEngine;

import java.io.IOException;
import java.text.ParseException;
import java.util.Enumeration;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nutz.lang.Files;
import org.nutz.lang.Strings;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.View;

public final class HTTLView extends AbstractPathView implements View {
	private final String suffix;

	public HTTLView(String dest, String suffix) {
		super(dest);
		this.suffix = suffix;
	}

	protected void buildContext(final HttpServletRequest req, final ServletResponse resp, final Object obj) {
		final Context context = Context.getContext();
		Enumeration<String> attrNames = req.getAttributeNames();
		for (String name; (name = attrNames.nextElement()) != null;) {
			context.put(name, req.getAttribute(name));
		}
		context.put("obj", obj);
		context.put("request", req);
		context.put("response", resp);
		context.put("ctxPath", req.getContextPath());
		context.put("session", req.getSession());
	}

	protected String getTemplatePath(HttpServletRequest req, String evalPath) {
		// 空路径，采用默认规则
		if (Strings.isBlank(evalPath)) {
			String path = Mvcs.getRequestPath(req);
			path = path.endsWith("/") ? path + "index" : path;
			return (path.startsWith("/") ? "" : "/") + Files.renameSuffix(path, suffix);
		// 绝对路径:以 '/' 开头的路径不增加 '/WEB-INF'
		} else if (evalPath.startsWith("/") && !evalPath.toLowerCase().endsWith(suffix)) {
			return evalPath + suffix;
		// 包名形式的路径
		} else {
			return evalPath.replace('.', '/') + suffix;
		}
	}

	public void render(final HttpServletRequest req, final HttpServletResponse resp, final Object obj) throws ParseException, IOException {
		buildContext(req, resp, obj);
		WebEngine.getEngine().
			getTemplate(getTemplatePath(req, evalPath(req, obj)), resp.getLocale()).
			render(resp);
	}
}