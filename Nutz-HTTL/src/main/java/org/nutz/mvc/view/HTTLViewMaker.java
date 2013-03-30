package org.nutz.mvc.view;

import org.nutz.ioc.Ioc;
import org.nutz.mvc.View;
import org.nutz.mvc.ViewMaker;

public final class HTTLViewMaker implements ViewMaker {
	public View make(Ioc ioc, String type, String value) {
		if ("HTTL".equals(type)) {
			return new HTTLView(value, ".html");
		}
		return null;
	}
}
