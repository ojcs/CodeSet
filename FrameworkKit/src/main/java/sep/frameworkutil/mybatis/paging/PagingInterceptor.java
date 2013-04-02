package sep.frameworkutil.mybatis.paging;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.executor.ExecutorException;
import org.apache.ibatis.executor.statement.BaseStatementHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.apache.ibatis.scripting.xmltags.ForEachSqlNode;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;

/**
 * 判断如果参数中有 {@link Paging} 对象，那么执行分页查询。(1.查询总数并放入page对象中。 2.构造带有limit子句的sql替换原始的sql)
 * 目前只支持把page放到HashMap中(或使用接口时，把page作为方法的参数),并且key为"page"
 * 
 * @author dixingxing
 * @date 2012-7-12
 */
@Intercepts(@Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class }))
public class PagingInterceptor implements Interceptor {
	private static final ThreadLocal<Paging<?>> PAGE_CONTEXT = new ThreadLocal<Paging<?>>();
	
	public static final String PAGE_KEY = "page";

	/** {@link ResultSetInterceptor}获取一次即清空 */
	public static Paging<?> getPage() {
		Paging<?> page = PAGE_CONTEXT.get();
		PAGE_CONTEXT.remove();
		return page;
	}
	
	/** 保存在ThreadLocal中，使 {@link ResultSetInterceptor}能获取到此page对象 */
	private static void set(Paging<?> p) {
		PAGE_CONTEXT.set(p);
	}

	/** HSQLDB | MySQL | Oracle ... */
	private String dialect = "";

	/** 生成特定数据库的分页语句 */
	private String buildSQL(String sql, Paging<?> page) {
		if (page == null || dialect == null || dialect.isEmpty()) {
			return sql;
		}
		StringBuilder builder = new StringBuilder();
		switch (dialect.toLowerCase()) {
		case "hsqldb":
			builder.append("select limit ");
			builder.append(page.getCurrentResult());
			builder.append(" ");
			builder.append(page.getSize());
			builder.append(" ");
			builder.append(sql.substring(6));
			break;
		case "mysql":
			builder.append(sql);
			builder.append(" limit ");
			builder.append(page.getCurrentResult());
			builder.append(',');
			builder.append(page.getSize());
			break;
		case "oracle":
			builder.append("select * from (select tmp_tb.*,ROWNUM row_id from (");
			builder.append(sql);
			builder.append(") tmp_tb where ROWNUM<=");
			builder.append(page.getCurrentResult() + page.getSize());
			builder.append(") where row_id>");
			builder.append(page.getCurrentResult());
			break;
		default:
			throw new IllegalArgumentException("分页插件不支持此数据库：" + dialect);
		}
		return builder.toString();
	}

	public Object intercept(Invocation ivk) throws Throwable {
		if (ivk.getTarget() instanceof RoutingStatementHandler) {
			RoutingStatementHandler statementHandler = (RoutingStatementHandler) ivk.getTarget();
			BaseStatementHandler handler = (BaseStatementHandler) ReflectHelper.getValueByFieldName(statementHandler, "delegate");
			MappedStatement ms = (MappedStatement) ReflectHelper.getValueByFieldName(handler, "mappedStatement");

			BoundSql boundSql = handler.getBoundSql();
			Object param = boundSql.getParameterObject();
			if (param instanceof HashMap) {
				Paging<?> page = (Paging<?>) ((HashMap<?, ?>) param).get(PAGE_KEY);
				if (page != null) {
					final String sql = boundSql.getSql();
					page.setTotal(queryTotal(ivk, ms, boundSql, param, sql));
					set(page);
					ReflectHelper.setValueByFieldName(boundSql, "sql", buildSQL(sql, page));
				}
			}
		}
		return ivk.proceed();
	}

	public Object plugin(Object arg0) {
		return Plugin.wrap(arg0, this);
	}

	/** 查询总数 */
	private int queryTotal(Invocation ivk, MappedStatement ms, BoundSql boundSql, Object param, String sql) throws SQLException {
		String countSql = "select count(0) from (" + sql + ") tmp_count";
		int count = 0;
		try (Connection conn = (Connection) ivk.getArgs()[0]) {
			try (PreparedStatement stmt = conn.prepareStatement(countSql)) {
				BoundSql bs = new BoundSql(ms.getConfiguration(), countSql, boundSql.getParameterMappings(), param);
				setParameters(stmt, ms, bs, param);
				try (ResultSet set = stmt.executeQuery()) {
					if (set.next()) {
						count = set.getInt(1);
					}
				}
			}
		}
		return count;
	}

	/** 为Count语句设置参数. */
	private void setParameters(PreparedStatement ps, MappedStatement ms, BoundSql bs, Object parameterObject) throws SQLException {
		ErrorContext.instance().activity("setting parameters").object(ms.getParameterMap().getId());
		List<ParameterMapping> mappings = bs.getParameterMappings();
		if (mappings == null) {
			return;
		}
		Configuration configuration = ms.getConfiguration();
		TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
		MetaObject metaObject = parameterObject == null ? null : configuration.newMetaObject(parameterObject);
		for (int i = 0; i < mappings.size(); i++) {
			ParameterMapping parameterMapping = mappings.get(i);
			if (parameterMapping.getMode() != ParameterMode.OUT) {
				Object value;
				String propertyName = parameterMapping.getProperty();
				PropertyTokenizer prop = new PropertyTokenizer(propertyName);
				if (parameterObject == null) {
					value = null;
				} else if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
					value = parameterObject;
				} else if (bs.hasAdditionalParameter(propertyName)) {
					value = bs.getAdditionalParameter(propertyName);
				} else if (propertyName.startsWith(ForEachSqlNode.ITEM_PREFIX) && bs.hasAdditionalParameter(prop.getName())) {
					value = bs.getAdditionalParameter(prop.getName());
					if (value != null) {
						value = configuration.newMetaObject(value).getValue(propertyName.substring(prop.getName().length()));
					}
				} else {
					value = metaObject == null ? null : metaObject.getValue(propertyName);
				}
				@SuppressWarnings("unchecked")
				TypeHandler<Object> typeHandler = (TypeHandler<Object>) parameterMapping.getTypeHandler();
				if (typeHandler == null) {
					throw new ExecutorException("There was no TypeHandler found for parameter " + propertyName + " of statement " + ms.getId());
				}
				typeHandler.setParameter(ps, i + 1, value, parameterMapping.getJdbcType());
			}
		}
	}

	public void setProperties(Properties p) {
		dialect = p.getProperty("dialect");
	}
}