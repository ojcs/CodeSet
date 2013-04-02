package sep.frameworkutil.mybatis.paging;

import java.sql.Statement;
import java.util.List;
import java.util.Properties;

import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;

/**
 * 分页查询时把List放入参数page中并返回
 * 
 * @author dixingxing
 * @date 2012-7-12
 */
@Intercepts(@Signature(type = ResultSetHandler.class, method = "handleResultSets", args = { Statement.class }))
public class ResultSetInterceptor implements Interceptor {
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object intercept(Invocation invocation) throws Throwable {
		Object object = invocation.proceed();
		Paging<?> page = PagingInterceptor.getPage();
		if (page != null) {
			page.setResult((List) object);
			return page;
		}
		return object;
	}

	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	public void setProperties(Properties properties) {
	}
}