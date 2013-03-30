package org.nutz.mvc.view;

import httl.Context;
import httl.web.WebEngine;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nutz.lang.Files;
import org.nutz.lang.Strings;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.View;
import org.nutz.mvc.view.AbstractPathView;

public final class HTTLView extends AbstractPathView implements View {
	private final String suffix;

	public HTTLView(String dest, String suffix) {
		super(dest);
		this.suffix = suffix;
	}

	public void render(HttpServletRequest req, HttpServletResponse resp, Object obj) throws Throwable {
		Context context = Context.getContext();
		Enumeration<String> reqAttrNames = req.getAttributeNames();
		while (reqAttrNames.hasMoreElements()) {
			final String name = reqAttrNames.nextElement();
			context.put(name, req.getAttribute(name));
		}
		context.put("obj", obj);
		context.put("request", req);
		context.put("response", resp);
		context.put("ctxPath", req.getContextPath());
		context.put("session", req.getSession());
		WebEngine.getEngine().getTemplate(getTemplatePath(req, evalPath(req, obj)), resp.getLocale()).render(resp);
	}

	protected String getTemplatePath(HttpServletRequest req, String path) {
		// 空路径，采用默认规则
		if (Strings.isBlank(path)) {
			path = Mvcs.getRequestPath(req);
			path = path.endsWith("/") ? path + "index" : path;
			return (path.startsWith("/") ? "" : "/") + Files.renameSuffix(path, suffix);
		// 绝对路径:以 '/' 开头的路径不增加 '/WEB-INF'
		} else if (path.startsWith("/") && !path.toLowerCase().endsWith(suffix)) {
			return path + suffix;
		// 包名形式的路径
		} else {
			return path.replace('.', '/') + suffix;
		}
	}

	protected String getRootPath() {
		return "/index.httl";
	}
}